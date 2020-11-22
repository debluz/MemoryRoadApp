package com.example.memoryroadapp.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.memoryroadapp.utils.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.viewmodels.AddEditLocationViewModel
import com.example.memoryroadapp.databinding.ActivityAddEditLocationBinding
import com.example.memoryroadapp.ui.MapsActivity.Companion.EXTRA_LATITUDE
import com.example.memoryroadapp.ui.MapsActivity.Companion.EXTRA_LONGITUDE
import com.example.memoryroadapp.results.LocationResult
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.activity_add_edit_location.*


class AddEditLocationActivity : AppCompatActivity() {

    private val addEditLocationViewModel by lazy {
        ViewModelProvider(this).get(
            AddEditLocationViewModel::class.java
        )
    }
    private lateinit var binding: ActivityAddEditLocationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_location)

        binding = DataBindingUtil
            .setContentView<ActivityAddEditLocationBinding>(
                this,
                R.layout.activity_add_edit_location
            )
        binding.lifecycleOwner = this
        binding.viewmodel = addEditLocationViewModel




        checkIntent()
        observeActionResult()
        initAddImageButton()
        initSelectLocationButton()
    }

    private fun checkIntent() {
        title = if (intent.hasExtra(EXTRA_ID)) {
            informViewModelAboutIntent(false)
            initFields(intent.getStringExtra(EXTRA_ID))
            resources.getString(R.string.title_edit_location)
        } else {
            informViewModelAboutIntent(true)
            resources.getString(R.string.title_add_location)
        }

    }

    private fun informViewModelAboutIntent(flag: Boolean) {
        addEditLocationViewModel.setIsNewLocation(flag)
    }

    private fun initFields(locationId: String) {
        addEditLocationViewModel.getLocationById(locationId)
        addEditLocationViewModel.editedLocation.observe(this, Observer { location ->
            addEditLocationViewModel.initFields()
            loadImage(location.imageUrl)
        })
    }

    private fun loadImage(imageUrl: String?){
        if(!imageUrl.isNullOrEmpty()){
            Glide.with(this)
                .load(imageUrl)
                .into(binding.addEditLocationImageView)
        } else {
            Glide.with(this)
                .clear(binding.addEditLocationImageView)
            addEditLocation_image_view.setImageResource(R.drawable.ic_baseline_photo_size_select_actual_40)
        }
    }


    private fun observeActionResult() {
        addEditLocationViewModel.result.observe(this, Observer {result ->
            when(result){
                is LocationResult.Success.Added -> {
                    addEditLocationViewModel.createdLocation.observe(this,Observer {
                        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                        finish()
                    })
                }
                is LocationResult.Success.Updated -> {
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }
                else -> Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initAddImageButton() {
        binding.addImageFAB.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            ) {
                selectImage()
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                    REQUEST_EXTERNAL_STORAGE_AND_CAMERA
                )
            }
        }
    }

    private fun selectImage() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                //putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                type = "image/*"
            }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val chooserIntent = Intent.createChooser(galleryIntent, "Select from:").apply {
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
            }
        }
        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(chooserIntent, REQUEST_IMAGE_GET)
        }
    }

    private fun initSelectLocationButton(){
        binding.locationSelectButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent, REQUEST_LOCATION_COORDINATES)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            if(data?.extras != null){
                val imageBitmap = data.extras!!.get("data") as Bitmap
                addEditLocation_image_view.setImageBitmap(imageBitmap)
                addEditLocationViewModel.uploadImage(imageBitmap)
            } else {
                val imageUri = data?.data
                if (imageUri != null) {
                    val imageStream = contentResolver.openInputStream(imageUri)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    addEditLocationViewModel.uploadImage(selectedImage)
                }
            }
        } else if(requestCode == REQUEST_LOCATION_COORDINATES && resultCode == RESULT_OK){
            if(data?.extras != null){
                val latitude = data.extras!!.get(EXTRA_LATITUDE) as Double
                val longitude = data.extras!!.get(EXTRA_LONGITUDE) as Double
                addEditLocationViewModel.latitudeEditTextContent.value = latitude.toString()
                addEditLocationViewModel.longitudeEditTextContent.value = longitude.toString()
            }
        }
    }

}

private const val REQUEST_EXTERNAL_STORAGE_AND_CAMERA = 1234
private const val REQUEST_IMAGE_GET = 4321
private const val REQUEST_LOCATION_COORDINATES = 1111

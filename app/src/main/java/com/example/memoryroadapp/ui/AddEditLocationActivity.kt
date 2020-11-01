package com.example.memoryroadapp.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel
import com.example.memoryroadapp.databinding.ActivityAddEditLocationBinding
import com.example.memoryroadapp.util.results.LocationResult
import kotlinx.android.synthetic.main.activity_add_edit_location.*


class AddEditLocationActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_EXTERNAL_STORAGE_AND_CAMERA = 1234
        const val REQUEST_IMAGE_GET = 4321
        const val EXTRA_ID = "com.example.memoryroadapp.ui.EXTRA_ID"
    }
    private val addEditLocationViewModel by lazy {
        ViewModelProvider(this).get(
            AddEditLocationViewModel::class.java
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_location)

        val binding = DataBindingUtil
            .setContentView<ActivityAddEditLocationBinding>(
                this,
                R.layout.activity_add_edit_location
            )
        binding.lifecycleOwner = this
        binding.viewmodel = addEditLocationViewModel


        checkIntent()
        observeActionResult()
        initAddImageButton()
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
            val uri = Uri.parse(location.imageUrl)
            /*Glide.with(this)
                .load(uri)
                .into(addEditLocation_image_view)*/
        })
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
        addImageFAB.setOnClickListener {
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
                HelperClass.logTestMessage(data?.data.toString())
            }

        }


    }


}
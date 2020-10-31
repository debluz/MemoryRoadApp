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
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.Constants.Companion.EC_ADDED_LOCATION
import com.example.memoryroadapp.Constants.Companion.EC_FAIL_ADD_EDIT_LOCATION
import com.example.memoryroadapp.Constants.Companion.EC_UPDATED_LOCATION
import com.example.memoryroadapp.Constants.Companion.REQUEST_IMAGE_GET
import com.example.memoryroadapp.Constants.Companion.REQUEST_EXTERNAL_STORAGE_AND_CAMERA
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel
import com.example.memoryroadapp.databinding.ActivityAddEditLocationBinding
import kotlinx.android.synthetic.main.activity_add_edit_location.*
import kotlinx.coroutines.job


class AddEditLocationActivity : AppCompatActivity() {
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
        observeEventCode()
        initAddImageButton()
    }

    private fun checkIntent() {
        title = if (intent.hasExtra(Constants.EXTRA_ID)) {
            informViewModelAboutIntent(false)
            initFields(intent.getStringExtra(Constants.EXTRA_ID))
            String()
            resources.getString(R.string.title_edit_location)
        } else {
            informViewModelAboutIntent(true)
            resources.getString(R.string.title_edit_location)
        }
    }

    private fun informViewModelAboutIntent(flag: Boolean) {
        addEditLocationViewModel.setIsNewLocation(flag)
    }

    private fun initFields(locationId: String) {
        addEditLocationViewModel.getLocationById(locationId)
        addEditLocationViewModel.editedLocation.observe(this, Observer { location ->
            addEditLocationViewModel.initFields()
        })
    }


    private fun observeEventCode() {
        addEditLocationViewModel.eventCode.observe(this, Observer { eventCode ->
            when (eventCode) {
                EC_ADDED_LOCATION -> {
                    addEditLocationViewModel.createdLocation.observe(
                        this,
                        Observer { createdLocation ->
                            if (createdLocation.isCreated) {
                                finish()
                            }
                        })
                }
                EC_UPDATED_LOCATION -> {
                    Toast.makeText(this, "Location updated", Toast.LENGTH_SHORT).show()
                    finish()
                }
                EC_FAIL_ADD_EDIT_LOCATION -> {
                    Toast.makeText(
                        this,
                        R.string.empty_fields_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
                /*if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(addImageFAB, R.string.media_rationale_message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK") {}
                        .show()
                }*/
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
                imageView.setImageBitmap(imageBitmap)
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
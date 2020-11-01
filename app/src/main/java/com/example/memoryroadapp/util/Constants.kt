package com.example.memoryroadapp

open class Constants{
    companion object{





        //REQUEST CODES
        const val REQUEST_CODE_SING_IN: Int = 1
        const val REQUEST_CODE_SIGN_UP: Int = 2
        const val REQ_C_ADD_LOCATION: Int = 3
        const val REQ_C_EDIT_LOCATION: Int = 4


        //EVENT CODES
        const val EC_ADDED_LOCATION = 11
        const val EC_UPDATED_LOCATION = 12
        const val EC_FAIL_ADD_EDIT_LOCATION = 13

        //INTENT EXTRAS
        const val EXTRA_ID = "com.example.memoryroadapp.EXTRA_ID"
        const val EXTRA_NAME = "com.example.memoryroadapp.EXTRA_NAME"
        const val SELECTED_LOCATIONS = "com.example.memoryroadapp.SELECTED_LOCATIONS"
        const val ALL_LOCATIONS = "com.example.memoryroadapp.ALL_LOCATIONS"
        const val EXTRA_IMAGE_URL: String = "com.example.memoryroadapp.EXTRA_IMAGE_URL"

        //REQUESTS
        const val REQUEST_EXTERNAL_STORAGE_AND_CAMERA = 14
        const val REQUEST_WRITE_EXTERNAL_STORAGE = 15
        const val REQUEST_IMAGE_GET = 16
    }
}

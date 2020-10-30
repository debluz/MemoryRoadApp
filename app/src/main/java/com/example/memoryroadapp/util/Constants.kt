package com.example.memoryroadapp

open class Constants{
    companion object{
        //TAGS
        const val AUTH_TAG: String = "FirebaseAuthAppTag"
        const val TEST_TAG: String = "TestMemoryRoadAppTag"


        //REQUEST CODES
        const val REQUEST_CODE_SING_IN: Int = 1
        const val REQUEST_CODE_SIGN_UP: Int = 2
        const val REQ_C_ADD_LOCATION: Int = 3
        const val REQ_C_EDIT_LOCATION: Int = 4

        //RESULTS CODES
        const val USER: String = "user"
        const val USERS: String = "users"
        const val LOCATIONS: String = "locations"

        //EVENT CODES
        const val EC_EMPTY_FIELDS = 5
        const val EC_SIGN_IN_WITH_EMAIL = 6
        const val EC_SIGN_IN_FAIL = 17
        const val EC_SIGN_IN_GOOGLE = 7
        const val EC_REGISTRATION_COMPLETED = 8
        const val EC_REGISTRATION_FAILURE = 9
        const val EC_CREATE_LOCATION = 10
        const val EC_ADDED_LOCATION = 11
        const val EC_UPDATED_LOCATION = 12
        const val EC_FAIL_ADD_EDIT_LOCATION = 13

        //INTENT EXTRAS
        const val EXTRA_ID = "com.example.memoryroadapp.EXTRA_ID"
        const val EXTRA_NAME = "com.example.memoryroadapp.EXTRA_NAME"
        const val SELECTED_LOCATIONS = "com.example.memoryroadapp.SELECTED_LOCATIONS"
        const val ALL_LOCATIONS = "com.example.memoryroadapp.ALL_LOCATIONS"

        //REQUESTS
        const val REQUEST_EXTERNAL_STORAGE_AND_CAMERA = 14
        const val REQUEST_WRITE_EXTERNAL_STORAGE = 15
        const val REQUEST_IMAGE_GET = 16
    }
}

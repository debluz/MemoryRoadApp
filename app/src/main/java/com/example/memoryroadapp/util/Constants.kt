package com.example.memoryroadapp

open class Constants{
    companion object{
        const val AUTH_TAG: String = "FirebaseAuthAppTag"
        const val TEST_TAG: String = "TestMemoryRoadAppTag"
        //REQUEST CODES
        const val REQ_C_SING_IN: Int = 123
        const val REQ_C_SIGN_UP: Int = 321
        const val REQ_C_ADD_LOCATION: Int = 456
        const val REQ_C_EDIT_LOCATION: Int = 654

        //RESULTS CODES
        const val USER: String = "user"
        const val USERS: String = "users"
        const val LOCATIONS: String = "locations"

        //EVENT CODES
        const val EC_EMPTY_FIELDS = 111
        const val EC_SIGN_IN_WITH_EMAIL = 222
        const val EC_SIGN_IN_GOOGLE = 333
        const val EC_REGISTRATION_COMPLETED = 444
        const val EC_REGISTRATION_FAILURE = 555
        const val EC_CREATE_LOCATION = 666

    }
}

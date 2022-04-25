package com.example.athopnfc

class User : UserFunctions{
    var email:String? = null
    var password:String? = null
    override fun createUser(em: String, pass: String) {
        email = em
        password = pass
    }

}
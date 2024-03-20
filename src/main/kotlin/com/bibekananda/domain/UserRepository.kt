package com.bibekananda.domain

import com.bibekananda.data.model.Status
import com.bibekananda.data.model.user_details.User


interface UserRepository {
    suspend fun loginUser(email : String , password : String) : Status
    suspend fun signUpUser(email : String, name : String, password: String) : Status
    suspend fun getUser(subId : String) : User?
    suspend fun uploadProfile(photo : String,subId : String) : Status

}
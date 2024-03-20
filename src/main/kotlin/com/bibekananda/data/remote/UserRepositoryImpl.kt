package com.bibekananda.data.remote

import at.favre.lib.crypto.bcrypt.BCrypt
import com.bibekananda.data.model.Status
import com.bibekananda.data.model.task.Task
import com.bibekananda.data.model.user_details.User
import com.bibekananda.domain.UserRepository
import com.mongodb.client.model.Updates
import io.ktor.client.*
import org.litote.kmongo.SetTo
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.set

class UserRepositoryImpl(private val dataBase: CoroutineDatabase) :
    UserRepository {


    private val users = dataBase.getCollection<User>()//collection is a data holder for multiple fields



    override suspend fun loginUser(email: String, password: String): Status {
        val existingUser = users.findOne(User::emailAddress eq email) ?: return Status(success = false,"User doesn't exist")
        val hashedPassword = existingUser.password
        if (!BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified) {
            return Status(false, "Invalid Credentials")
        }
        return Status(true, message = "Login Successful")
    }

    override suspend fun signUpUser(email: String, name: String, password: String): Status {
        val existingUser = users.findOne(User::emailAddress eq email)
         if(existingUser == null){
            val hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray())
            val user = User(name = name, emailAddress = email, password = hashedPassword)
            val insert = users.insertOne(user).wasAcknowledged()

            return if(insert){
                Status(true,"Sign Up Successful")
            }else{
                Status(false, "Sign Up Failed")
            }
        }else{
            return Status(false, "User Already Exist")
        }

    }

    override suspend fun getUser(subId: String): User? {
        return users.findOne(User::emailAddress eq subId)

    }

    override suspend fun uploadProfile(photo: String,subId : String): Status {
        val filter = User::emailAddress eq subId
        val update = Updates.combine(
            set(SetTo(property = User::profilePhoto, photo)))
        val result = users.updateOne(filter,update).wasAcknowledged()
        return if(result){
            Status(true,"Update Successful")
        }else{
            Status(false,"Update Failed")
        }
    }


}
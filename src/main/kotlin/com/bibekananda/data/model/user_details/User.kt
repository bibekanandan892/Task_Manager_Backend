package com.bibekananda.data.model.user_details

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String?= null,
    val emailAddress: String?= null,
    val userId : String? = null,
    val password: String? = null,
    val profilePhoto: String?= null,
)
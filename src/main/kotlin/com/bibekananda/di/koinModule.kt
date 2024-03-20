package com.bibekananda.di


import com.bibekananda.data.remote.TaskRepositoryImpl
import com.bibekananda.data.remote.UserRepositoryImpl
import com.bibekananda.domain.TaskRepository
import com.bibekananda.domain.UserRepository
import com.bibekananda.util.Constants.DATABASE
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {
    single {
        KMongo.createClient()//System.getenv("MONGODB_URI"))//using heroki i have to connect to atlas using a connection string or from heroku platform
            .coroutine
            .getDatabase(DATABASE)
    }
    single<UserRepository> {//type of user data source
        UserRepositoryImpl(get())// get would fetch koin instance which is already provided amd already declared aboce
    }
    single<TaskRepository> {//type of user data source
        TaskRepositoryImpl(get())// get would fetch koin instance which is already provided amd already declared aboce
    }


}
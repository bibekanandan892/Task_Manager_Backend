package com.bibekananda.data.model.endpoint

sealed class Endpoint(val path: String) {
    data object Root: Endpoint(path = "/")
    data object TokenVerification: Endpoint(path = "/token_verification")
    data object SignIn: Endpoint(path = "/sign_in")
    data object SingUp: Endpoint(path = "/sign_up")
    data object InsertTask: Endpoint(path = "/insert_task")
    data object UpdateTask: Endpoint(path = "/update_task")
    data object UpdatePhoto: Endpoint(path = "/update_photo")
    data object DeleteTask: Endpoint(path = "/delete_task")
    data object FetchTasks: Endpoint(path = "/fetch_task")
    data object GetUser: Endpoint(path = "/get_user")
    data object Unauthorized: Endpoint(path = "/unauthorized")
    data object Authorized: Endpoint(path = "/authorized")

}

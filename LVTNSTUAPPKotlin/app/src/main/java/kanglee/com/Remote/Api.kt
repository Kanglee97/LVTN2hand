package kanglee.com.Remote

import kanglee.com.Model.LoginResponse
import kanglee.com.Model.SignupResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("users/login")
    fun userslogin (
        @Field("phoneNumber") phoneNumber:String,
        @Field("password") password: String
    ): Call<LoginResponse>



    @FormUrlEncoded
    @POST("users/signup")
    fun userssignup (
        @Field("phoneNumber") phoneNumber:String,
        @Field("password") password: String
    ):Call<SignupResponse>

}
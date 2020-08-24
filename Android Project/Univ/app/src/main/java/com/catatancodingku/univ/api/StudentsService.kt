package com.catatancodingku.univ.api

import com.catatancodingku.univ.model.StudentsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentsService {

    @GET("getData.php")
    fun getDataStudents():Call<StudentsResponse>

    @FormUrlEncoded
    @POST("insert.php")
    fun addDataStudents(
        @Field("name") name : String,
        @Field("noHp") noHp : String,
        @Field("address") address : String
    ) : Call<StudentsResponse>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("id") id: String

    ) : Call<StudentsResponse>

    @FormUrlEncoded
    @POST("update.php")
    fun updateData(
        @Field("id") id : String,
        @Field("name") name : String,
        @Field("noHp") noHp : String,
        @Field("address") address : String
    ) : Call<StudentsResponse>



}
package com.example.engineerpracticaltest

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/catom/api/challenge/banks")
    fun getData() : Call<List<BankResultItem>>
}
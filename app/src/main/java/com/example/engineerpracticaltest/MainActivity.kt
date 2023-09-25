package com.example.engineerpracticaltest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engineerpracticaltest.DB.sqliteHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    lateinit var BankAdapter : BankAdapter
    lateinit var BankDBHelper:sqliteHelper

    var Base_URL = "https://dev.obtenmas.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(this)

        BankDBHelper = sqliteHelper(this)

        getAllData()

    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        Log.d("Data",Base_URL)

        var retroData = retrofit.getData()

        retroData.enqueue(object : Callback<List<BankResultItem>>{
            override fun onResponse(
                call: Call<List<BankResultItem>>,
                response: Response<List<BankResultItem>>
            ) {
                var data = response.body()!!

                BankAdapter = BankAdapter(baseContext, data)
                rvMain.adapter = BankAdapter

                Log.d("Data",data.toString())

                BankDBHelper.addData(bank_name = BankAdapter.toString(), description = BankAdapter.toString(), age = BankAdapter.itemCount, image = BankAdapter.toString())
                /*Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()*/
            }

            override fun onFailure(call: Call<List<BankResultItem>>, t: Throwable) {
                /*Toast.makeText(this, "A ocurrido un error con la conexión", Toast.LENGTH_SHORT).show()*/
            }

        })

    }
}
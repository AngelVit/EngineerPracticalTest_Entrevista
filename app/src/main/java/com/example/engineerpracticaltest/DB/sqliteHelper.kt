package com.example.engineerpracticaltest.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqliteHelper(context: Context): SQLiteOpenHelper(
    context, "banks.db", null, 1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreate = "CREATE TABLE BANKS" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bank_name TEXT, description TEXT, age INTEGER, image TEXT)"
        db!!.execSQL(orderCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val orderDelete = "DROP TABLE IF EXISTS BANKS"
        db!!.execSQL(orderDelete)
        onCreate(db)
    }

    fun addData(bank_name: String, description: String, age:Int, image: String){
        val datos = ContentValues()
        datos.put("bank_name",bank_name)
        datos.put("description",description)
        datos.put("age",age)
        datos.put("image",image)

        val db = this.writableDatabase
        db.insert("BANKS", null, datos)
        db.close()
    }

}
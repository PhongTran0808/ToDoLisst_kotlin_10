package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, 1) {

    companion object {
        const val DBNAME = "Login.db"
    }

    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
        MyDB.execSQL("create Table tasks(id INTEGER primary key autoincrement, task TEXT, username TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
        MyDB.execSQL("drop Table if exists tasks")
    }

    fun insertData(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun checkusername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkusernamepassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", arrayOf(username, password))
        return cursor.count > 0
    }

    fun insertTask(task: String, username: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("task", task)
        contentValues.put("username", username)
        val result = MyDB.insert("tasks", null, contentValues)
        return result != -1L
    }

    fun getTasks(username: String): Cursor? {
        val MyDB = this.readableDatabase
        return MyDB.rawQuery("SELECT id as _id, task FROM tasks WHERE username = ?", arrayOf(username))
    }

    fun updateTask(id: String, task: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("task", task)
        val result = MyDB.update("tasks", contentValues, "id = ?", arrayOf(id))
        return result > 0
    }

    fun deleteTask(id: String): Boolean {
        val MyDB = this.writableDatabase
        val result = MyDB.delete("tasks", "id = ?", arrayOf(id))
        return result > 0
    }
}

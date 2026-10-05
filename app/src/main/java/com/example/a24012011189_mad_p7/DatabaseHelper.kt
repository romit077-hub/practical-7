package com.example.a24012011189_mad_p7

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "persons.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PERSONS = "persons"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_EMAIL = "email"
        private const val COL_PHONE = "phone"
        private const val COL_ADDRESS = "address"
        private const val COL_LATITUDE = "latitude"
        private const val COL_LONGITUDE = "longitude"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PERSONS (
                $COL_ID TEXT PRIMARY KEY,
                $COL_NAME TEXT,
                $COL_EMAIL TEXT,
                $COL_PHONE TEXT,
                $COL_ADDRESS TEXT,
                $COL_LATITUDE REAL,
                $COL_LONGITUDE REAL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PERSONS")
        onCreate(db)
    }

    fun insertPerson(person: Person) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_ID, person.id)
            put(COL_NAME, person.name)
            put(COL_EMAIL, person.emailId)
            put(COL_PHONE, person.phoneNo)
            put(COL_ADDRESS, person.address)
            put(COL_LATITUDE, person.latitude)
            put(COL_LONGITUDE, person.longitude)
        }
        db.insertWithOnConflict(TABLE_PERSONS, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    fun deletePerson(id: String): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete(TABLE_PERSONS, "$COL_ID = ?", arrayOf(id))
        db.close()
        return rowsDeleted
    }

    fun deleteAllPersons(): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete(TABLE_PERSONS, null, null)
        db.close()
        return rowsDeleted
    }

    fun getAllPersons(): List<Person> {
        val list = mutableListOf<Person>()
        val db = readableDatabase
        val cursor: Cursor = db.query(TABLE_PERSONS, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val person = Person(
                    id = cursor.getString(cursor.getColumnIndexOrThrow(COL_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                    emailId = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                    phoneNo = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE)),
                    address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)),
                    latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_LATITUDE)),
                    longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_LONGITUDE))
                )
                list.add(person)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}

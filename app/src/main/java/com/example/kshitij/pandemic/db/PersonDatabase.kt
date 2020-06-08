package com.example.kshitij.pandemic.db


import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kshitij.pandemic.PersonDao
import com.example.kshitij.pandemic.PersonHealthDao
import com.example.kshitij.pandemic.models.Person
import com.example.kshitij.pandemic.models.PersonHealthData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Converter


@Database(entities = arrayOf(
    Person::class,
    PersonHealthData::class),version = 2)
//@TypeConverters(Converter::class)

abstract class PersonDatabase : RoomDatabase() {
    abstract fun PersonHealthDao(): PersonHealthDao

    abstract fun PersonDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PersonDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "covid_database"

                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }


//        private class PersonDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//
//                    }
//                }
//
//            }
//
//        }

    }
}
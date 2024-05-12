package com.example.taskfinal5.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

import com.example.taskfinal5.model.theNotes
import java.util.concurrent.locks.Lock

@Database(entities = [theNotes::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun getNoteDoa(): NoteDao    //notedao interface linked

    companion object{
        @Volatile       //changes made to one thread are visivle to other
        private var instance:NoteDatabase? = null       //
        private val LOCK = Any()        //lock - synchronized the data basecreation process------------only one thread can execute the code at a time

        operator fun invoke(context: Context) = instance ?:     //create instance
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{               //if the database in null create the database
                instance = it
            }

        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "theNotes_db"       //database name
            ).build()
    }




}
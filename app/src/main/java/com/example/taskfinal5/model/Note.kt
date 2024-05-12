package com.example.taskfinal5.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import androidx.room.PrimaryKey


@Entity(tableName = "theNotes")
@Parcelize
data class theNotes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val tnote : String,
    val descripofnotes : String
):Parcelable


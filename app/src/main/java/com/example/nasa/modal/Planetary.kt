package com.example.nasa.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "planetary")
data class Planetary (@PrimaryKey(autoGenerate = true)
                      var id: Int,
                      @ColumnInfo(name = "title",defaultValue = "") @SerializedName("title") var name: String,
                      @ColumnInfo(name = "date",defaultValue = "") @SerializedName("date") var date: String,
                      @ColumnInfo(name = "explanation",defaultValue = "") @SerializedName("explanation") var details: String,
                      @ColumnInfo(name = "url",defaultValue = "") @SerializedName("url") var url: String,
                      @ColumnInfo(name = "hdurl",defaultValue = "") @SerializedName("hdurl") var hdUrl: String) : Serializable{
}
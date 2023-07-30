package com.example.roomwordsample.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
class Word(@ColumnInfo("word") var word: String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
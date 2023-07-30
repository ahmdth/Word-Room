package com.example.roomwordsample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomwordsample.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun all(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)
//
//    @Update
//    suspend fun update(word: Word)

    @Query("DELETE FROM words WHERE id = :id")
    suspend fun delete(id: Int)

//    @Query("DELETE FROM words")
//    suspend fun deleteAll(): Int?
}
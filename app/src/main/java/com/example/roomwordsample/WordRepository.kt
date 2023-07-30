package com.example.roomwordsample

import androidx.annotation.WorkerThread
import com.example.roomwordsample.entities.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordADao: WordDao) {
    val allWords: Flow<List<Word>> = wordADao.all()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordADao.insert(word)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun update(word: Word){
//        wordADao.update(word)
//    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(id: Int){
        wordADao.delete(id)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun deleteAll(){
//        wordADao.deleteAll()
//    }
}
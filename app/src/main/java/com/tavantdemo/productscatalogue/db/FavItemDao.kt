package com.example.demo.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item : List<ItemResponse>)

    @Query("Select * from favorites")
    fun getAllItems(): LiveData<List<ItemResponse>>


}
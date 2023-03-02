package com.example.demo.db

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class FavItemRepository {

    companion object{
        var dataBaseFavItem: FavoriteDataBaseItem?=null

        private fun intializeDB(context: Context): FavoriteDataBaseItem
        {
            return FavoriteDataBaseItem.invoke(context)
        }

        suspend fun insert(context: Context, favItem: ItemResponse)
        {
            dataBaseFavItem= intializeDB(context)
            withContext(IO) {
                dataBaseFavItem!!.getItemDao().insertItem(favItem)
            }
        }

        fun getAllItemData(context: Context): LiveData<List<ItemResponse>>
        {
            dataBaseFavItem = intializeDB(context)
            return dataBaseFavItem!!.getItemDao().getAllItems()
        }
    }
}
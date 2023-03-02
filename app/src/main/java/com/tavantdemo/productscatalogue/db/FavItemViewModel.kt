package com.example.demo.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavItemViewModel : ViewModel() {

    fun insert(context: Context, favItems:ItemResponse)
    {
        viewModelScope.launch{
            FavItemRepository.insert(context, favItems)
        }

    }

    fun getAllFavItemList(context: Context): LiveData<List<ItemResponse>>
    {
        return FavItemRepository.getAllItemData(context)
    }
}
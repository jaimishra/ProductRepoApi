package com.tavantdemo.productscatalogue.ui.favproducts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.repository.ProductListRepository
import com.example.demo.db.FavItemRepository
import com.example.demo.db.ItemResponse
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import com.tavantdemo.productscatalogue.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNotificationsViewModel @Inject constructor(private val productListRepository : ProductListRepository): ViewModel() {

    fun insert(context: Context, favItems: ItemResponse)
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
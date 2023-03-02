package com.tavantdemo.productscatalogue.ui.favproducts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.db.ItemResponse
import com.example.demo.db.Rating
import com.tavantdemo.productscatalogue.R
import com.tavantdemo.productscatalogue.adapter.CommonProductListAdapter
import com.tavantdemo.productscatalogue.adapter.FavoriteProductListAdapter
import com.tavantdemo.productscatalogue.databinding.FragmentFavProductBinding
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteProductsFragment : Fragment() {

    private var _binding: FragmentFavProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val  favoriteViewModel by viewModels<FavoriteNotificationsViewModel>()
    private lateinit var favoriteProductListAdapter : FavoriteProductListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.getAllFavItemList(requireContext()).observe(viewLifecycleOwner)
        { productListItem ->
            favoriteProductListAdapter = FavoriteProductListAdapter(requireContext())
            favoriteProductListAdapter.setItemsData(ArrayList(productListItem))
            _binding?.rvProduct?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            _binding?.rvProduct?.itemAnimator = DefaultItemAnimator()
            _binding?.rvProduct?.adapter = favoriteProductListAdapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.tavantdemo.productscatalogue.ui.favproducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tavantdemo.productscatalogue.databinding.FragmentFavProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteProductsFragment : Fragment() {

    private var _binding: FragmentFavProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(FavoriteNotificationsViewModel::class.java)

        _binding = FragmentFavProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
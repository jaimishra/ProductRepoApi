package com.tavantdemo.productscatalogue.ui.producthome
import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.db.ItemResponse
import com.example.demo.db.Rating
import com.tavantdemo.productscatalogue.R
import com.tavantdemo.productscatalogue.adapter.CommonProductListAdapter
import com.tavantdemo.productscatalogue.databinding.FragmentProductsHomeBinding
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import com.tavantdemo.productscatalogue.ui.favproducts.FavoriteNotificationsViewModel
import com.tavantdemo.productscatalogue.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsHomeFragment : Fragment(){

    var _binding: FragmentProductsHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val  productsHomeViewModel by viewModels<ProductsHomeViewModel>()
    private val  favoriteViewModel by viewModels<FavoriteNotificationsViewModel>()
    private lateinit var commonProductListAdapter: CommonProductListAdapter
    private var PAGE_START: Int = 20
    private var currentPage = PAGE_START

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsHomeBinding.inflate(inflater, container, false)
         val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsHomeViewModel.getProductList(currentPage)
        bindObservers()
    }

    private fun bindObservers() {
        productsHomeViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is NetworkResult.Success -> {
                    it.data?.let { itdata -> updateDataListToView(itdata) };
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Something went wrong",
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun updateDataListToView( productsResponseList: List<ProductsResponseItem>) {
        commonProductListAdapter = CommonProductListAdapter(requireContext())
        commonProductListAdapter.setItemsData(ArrayList(productsResponseList))
        commonProductListAdapter.setClickListener(object : CommonProductListAdapter.OnProductClickListener{
            override fun onShellClick(productsResponseItem: ProductsResponseItem?) {
                val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
                val bundle = Bundle()
                bundle.putParcelable("ProductItem",productsResponseItem)
                navController.navigate(R.id.productDetails,bundle)
            }

            override fun onFavClick(productsResponseItem: ProductsResponseItem?) {
                    context?.let { context ->
                        val rating = Rating(productsResponseItem?.rating?.count, productsResponseItem?.rating?.rate)
                        val itemResponse = ItemResponse(productsResponseItem?.id,productsResponseItem?.title,
                       rating, productsResponseItem?.image, productsResponseItem?.category, productsResponseItem?.description
                            , productsResponseItem?.price)
                        favoriteViewModel.insert(context, itemResponse)
                        Toast.makeText(context, context.resources.getString(R.string.item_added_to_fav),
                            Toast.LENGTH_SHORT).show()

                }

            }

        })
        _binding?.rvProduct?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding?.rvProduct?.itemAnimator = DefaultItemAnimator()
        _binding?.rvProduct?.adapter = commonProductListAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
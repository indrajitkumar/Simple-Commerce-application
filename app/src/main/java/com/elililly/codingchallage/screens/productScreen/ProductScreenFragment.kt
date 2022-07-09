package com.elililly.codingchallage.screens.productScreen

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elililly.codingchallage.databinding.ProductListFragmentBinding
import com.elililly.codingchallage.getImageFromDrawable
import com.elililly.codingchallage.models.Products
import com.elililly.codingchallage.screens.BaseFragment
import com.elililly.codingchallage.screens.orderSummaryScreen.OrderSummaryScreenFragment
import com.elililly.codingchallage.utils.Constants
import com.elililly.codingchallage.viewmodels.ProductScreenViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class ProductScreenFragment : BaseFragment() {
    private lateinit var _viewModel: ProductScreenViewModel
    private lateinit var productListAdaptor: ProductListAdaptor
    lateinit var binding: ProductListFragmentBinding
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewModel = ViewModelProvider(this)[ProductScreenViewModel::class.java]

        _viewModel.mProducts.observe(viewLifecycleOwner, Observer {
            productListAdaptor =
                ProductListAdaptor(it.products)
            val layoutManager = LinearLayoutManager(mContext)
            binding.productsListView.layoutManager = layoutManager
            binding.productsListView.adapter = productListAdaptor
        })
        _viewModel.mStores.observe(viewLifecycleOwner, Observer {
            val image: Drawable = getImageFromDrawable(
                mContext!!,
                it.stores[0].url
            )
            binding.storeBanner.setImageDrawable(image)
        })
        binding = ProductListFragmentBinding.inflate(inflater, container, false)

        binding.orderSummaryBtn.setOnClickListener {
            val productsToBeOrder = productListAdaptor.getToBeOrderedProducts()
            if (productsToBeOrder.isNotEmpty()) {
                val orderSummaryScreenFragment = OrderSummaryScreenFragment()
                val bundle = Bundle()
                bundle.putSerializable(
                    Constants.ORDER_SUMMARY_KEY,
                    productsToBeOrder as Serializable
                )
                orderSummaryScreenFragment.arguments = bundle
                replaceFragment(
                    orderSummaryScreenFragment,
                    OrderSummaryScreenFragment::class.java.simpleName,
                    true
                )
            }else{
                Snackbar.make(binding.root,"Please select item to proceed", Snackbar.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}
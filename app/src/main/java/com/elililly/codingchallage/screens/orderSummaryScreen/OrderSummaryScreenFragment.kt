package com.elililly.codingchallage.screens.orderSummaryScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.elililly.codingchallage.*
import com.elililly.codingchallage.databinding.OrdersummaryFragmentBinding
import com.elililly.codingchallage.models.OrderSubmit
import com.elililly.codingchallage.models.Product
import com.elililly.codingchallage.screens.BaseFragment
import com.elililly.codingchallage.utils.Constants
import com.elililly.codingchallage.viewmodels.OrderSummaryViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.observeOn

class OrderSummaryScreenFragment : BaseFragment(), DialogListner {
    private lateinit var orderListAdaptor: OrderListAdaptor
    lateinit var productsToBeOrder: Map<Product, Int>
    lateinit var binding: OrdersummaryFragmentBinding
    private var mContext: Context? = null
    private lateinit var _viewModel: OrderSummaryViewModel
    lateinit var mOrderSubmit: OrderSubmit
    private var fetchOrder: String? = null
    private lateinit var successDialog: SuccessDialog
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrdersummaryFragmentBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[OrderSummaryViewModel::class.java]

        _viewModel.mOrderSubmit.observe(viewLifecycleOwner) {
            mOrderSubmit = it
        }
        val arguments = arguments
        if (arguments != null && arguments.containsKey(Constants.ORDER_SUMMARY_KEY)) {
            productsToBeOrder =
                arguments.getSerializable(Constants.ORDER_SUMMARY_KEY) as Map<Product, Int>
            orderListAdaptor = OrderListAdaptor(productsToBeOrder)
            val layoutManager = LinearLayoutManager(mContext)
            binding.selectedProducts.layoutManager = layoutManager
            binding.selectedProducts.adapter = orderListAdaptor
            _viewModel.totalPrice(productsToBeOrder)
            _viewModel.totalPrice.observe(viewLifecycleOwner) {
                binding.totalPrice.text = it
            }
        }
        successDialog = SuccessDialog(mContext!!)
        successDialog.setDialogListener(this)

        binding.orderSummaryBtn.setOnClickListener {

            if (binding.name.text!!.isBlank()) {
                binding.name.error = getString(R.string.errorName)
            } else if (binding.address.text!!.isBlank()) {
                binding.address.error = getString(R.string.errorAddress)
            } else if (binding.phone.text!!.isBlank()) {
                binding.phone.error = getString(R.string.errorPhone)
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    getUserInfo()
                    storeOrder()
                }
                fetchOrderResponse()
            }
        }
        return binding.root
    }
    
    private suspend fun storeOrder() {
        _viewModel.storeLocally(mOrderSubmit.json())
    }

    private fun fetchOrderResponse() {
        var mFlow: Flow<String?>
        _viewModel.mDataStoreManager.observe(viewLifecycleOwner) { it ->
            mFlow = it
            CoroutineScope(Dispatchers.Main).launch {
                mFlow.collect {
                    fetchOrder = it
                    binding.progressBarContainer.visibility = View.VISIBLE
                    showDialog(successDialog, fetchOrder?.fromJson<OrderSubmit>())
                }
            }
        }
    }

    private fun showDialog(
        successDialog: SuccessDialog,
        orderSubmittedResponse: OrderSubmit?
    ) {
        with(successDialog) {
            show(
                orderSubmittedResponse?.data?.attributes?.address?.name!!,
                orderSubmittedResponse.data.attributes.orderId,
                orderSubmittedResponse.data.attributes.paymentMethod
            )
        }
        binding.progressBarContainer.visibility = View.GONE
    }

    private fun getUserInfo() {
        mOrderSubmit.data.attributes.address.name = binding.name.text.toString()
        mOrderSubmit.data.attributes.address.address =
            binding.address.text.toString()
        mOrderSubmit.data.attributes.address.phone = binding.phone.text.toString()
        mOrderSubmit.data.attributes.orderId =
            (10000..100000000).random().toString()
    }

    override fun onDialogDismiss() {
        val fm: FragmentManager? = activity?.supportFragmentManager
        fm?.popBackStack()
    }


}
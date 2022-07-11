package com.elililly.codingchallage.viewmodels

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import com.elililly.codingchallage.models.OrderSubmit
import com.elililly.codingchallage.utils.getJsonDataFromAsset
import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import org.powermock.reflect.Whitebox.invokeMethod


@RunWith(PowerMockRunner::class)
internal class OrderSummaryViewModelTest {

    @Mock
    lateinit var application: Application

    @Mock
    lateinit var applicationContext: Context

    @Mock
    lateinit var assets: AssetManager
    lateinit var orderSummaryViewModel: OrderSummaryViewModel

    @Mock
    private lateinit var totalPrice: MutableLiveData<String>

    @Mock
    private lateinit var mDataStoreManager: MutableLiveData<Flow<String?>>

    @Mock
    private lateinit var mOrderSubmit: MutableLiveData<OrderSubmit>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        `when`(application.applicationContext).thenReturn(applicationContext)
        getJsonDataFromAsset(applicationContext,"src/test/rs/OrderDone.json")
        val spyOrderSummaryViewModel = spy(orderSummaryViewModel)
        invokeMethod<Any>(spyOrderSummaryViewModel, "getOrderSubmitResponse")
        orderSummaryViewModel = OrderSummaryViewModel(application)
    }

    @Test
    fun `verify setProduct response`() {
        `when`(application.applicationContext).thenReturn(applicationContext)
        val spyOrderSummaryViewModel = spy(orderSummaryViewModel)
        invokeMethod<Any>(spyOrderSummaryViewModel, "setOrderSubmit", any(OrderSubmit::class.java))

        Mockito.verify(mOrderSubmit).postValue(mock(OrderSubmit::class.java))
    }


    @Test
    fun `verify totalPrice response`() {
        `when`(application.applicationContext).thenReturn(applicationContext)
        orderSummaryViewModel.totalPrice(anyMap())
        Mockito.verify(totalPrice).postValue(anyString())
    }

    @Test
    fun `verify get From Storage `() {
        `when`(application.applicationContext).thenReturn(applicationContext)
        val spyOrderSummaryViewModel = spy(orderSummaryViewModel)
        val submitOrderResponse =
            Whitebox.setInternalState(orderSummaryViewModel, "getSubmitOrderResponse")
        Assert.assertNotNull(submitOrderResponse)
    }


    @Test
    fun test() {
        Assert.assertNotNull(orderSummaryViewModel)
    }
}
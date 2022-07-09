package com.elililly.codingchallage.viewmodels

import android.app.Application
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class ProductScreenViewModelTest{
    lateinit var productScreenViewModel: ProductScreenViewModel
    @Mock
    lateinit var application: Application

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productScreenViewModel = ProductScreenViewModel(application)
    }

    @Test
    fun `verify json read`(){
        Assert.assertNotNull(productScreenViewModel)
    }
}
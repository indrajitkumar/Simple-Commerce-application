package com.elililly.codingchallage.screens.productScreen

import com.elililly.codingchallage.models.Product
import com.elililly.codingchallage.viewmodels.ProductScreenViewModel
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@PrepareForTest(Product::class, HashMap::class, ProductListService::class)
@RunWith(PowerMockRunner::class)
class ProductListServiceTest : TestCase() {
    lateinit var productListService: ProductListService
    @Mock
    lateinit var product: Product
    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        productListService = ProductListService()
    }

    @Test
    fun `test product not null`(){
        val map = productListService.addProduct(product)
        Assert.assertNotNull(map)
    }
}
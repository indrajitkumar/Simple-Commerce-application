package com.elililly.codingchallage.screens.productScreen

import com.elililly.codingchallage.models.Product
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ProductListServiceTest {
    lateinit var productListService: ProductListService

    @Mock
    lateinit var product: Product

    @Mock
    lateinit var hashMap: HashMap<Product, Int>
    @Spy
    lateinit var spyProductListService: ProductListService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productListService = ProductListService()
    }

    @Test
    fun `test addProduct returns not null`() {
        val map = productListService.addProduct(product)
        Assert.assertNotNull(map)
    }

    @Test
    fun `test addProduct returns not null and add`() {
        val map = productListService.addProduct(product)
        Assert.assertNotNull(map)
    }

    @Test
    fun `test minusProduct returns not  null`() {
        val map = productListService.minusProduct(product)
        Assert.assertNotNull(map)
    }

    @Test
    fun `test minusProduct returns  null`() {
        Mockito.`when`(hashMap.contains(product)).thenReturn(true)
        Mockito.`when`(hashMap[product]).thenReturn(0)
        val map = productListService.minusProduct(product)
        Assert.assertNotNull(map)
    }

    @Test
    fun `verify getProductList not null`(){
        Assert.assertNotNull(productListService.getProductList())
    }
}
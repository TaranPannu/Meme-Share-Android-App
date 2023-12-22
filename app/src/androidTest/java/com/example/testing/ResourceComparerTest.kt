package com.example.testing

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test


class ResourceComparerTest{
    private lateinit var resourceComparer: ResourceComparer

    @Before// run before every test case
    fun setup(){
        resourceComparer= ResourceComparer()
    }
    @After//run after every test case
    fun tearDown(){

    }

    @Test
    fun stringResourceNotSameAsGivenString_returnsFalse(){
        val context= ApplicationProvider.getApplicationContext<Context>()
        val result=resourceComparer.isEqual(context,R.string.app_name,"Test_ing")

        assertFalse(result)
    }
}
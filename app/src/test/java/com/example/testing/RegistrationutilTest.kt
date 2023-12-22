package com.example.testing

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationutilTest{
    @Test
    fun Empty_user(){
        val result=Registrationutil.validateRegistrationInput("","123","123")
        assertThat(result).isFalse()// import from truth library
    }
    @Test
    fun Test_2(){
        val result=Registrationutil.validateRegistrationInput("Philipp","123","123")
        assertThat(result).isTrue()// import from truth library
    }
    @Test
    fun user_exist_already_return_false(){
        val result=Registrationutil.validateRegistrationInput("Carl","123","123")
        assertThat(result).isTrue()// import from truth library
    }
    @Test
    fun pass_empty(){
        val result=Registrationutil.validateRegistrationInput("Carl","","123")
        assertThat(result).isFalse()// import from truth library
    }
    @Test
    fun pass_confirm(){
        val result=Registrationutil.validateRegistrationInput("Carl","1923","123")
        assertThat(result).isFalse()// import from truth library
    }
    //password empty
    //password confirm incorrect
    //pass < len(2)
}
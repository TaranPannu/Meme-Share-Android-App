package com.example.testing

object Registrationutil  {
    private val exist= listOf("harry","jerry")

    fun validateRegistrationInput(user: String, pass:String, confirmed:String):Boolean{
       if(user.isEmpty() || pass.isEmpty()){
           return false;
       }
        if(user in exist) return false
        if(pass!=confirmed)return false
        return true
    }

}
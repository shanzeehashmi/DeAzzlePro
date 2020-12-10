package com.deazzle.deazzleproject.utils

import android.view.Gravity
import android.widget.Toast
import com.deazzle.deazzleproject.MyApplication

class ShowToast(private val message: String) {
    init {
        val toast: Toast = Toast.makeText(MyApplication.myAppContext,message,Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}
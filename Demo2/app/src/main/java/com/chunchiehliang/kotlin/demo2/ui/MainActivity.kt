package com.chunchiehliang.kotlin.demo2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.kotlin.demo2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        setContentView(R.layout.activity_main)
    }


}

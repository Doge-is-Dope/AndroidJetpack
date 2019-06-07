package com.chunchiehliang.kotlin.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.kotlin.room.R

/**
 * This main activity is just a container for our fragments,
 * where the real action is.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}

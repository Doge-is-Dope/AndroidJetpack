package com.chunchiehliang.kotlin.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.kotlin.demo.R
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackView

class SwipeCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_card)

        val cardStackView = findViewById<CardStackView>(R.id.card_stack_view)
        cardStackView.layoutManager = CardStackLayoutManager()
        cardStackView.adapter = CardStackAdapter()
    }
}

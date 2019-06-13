package com.chunchiehliang.kotlin.demo.ui.filter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableFloat
import androidx.fragment.app.Fragment
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED

class FilterFragment : Fragment() {
    companion object {
        // Threshold for when normal header views and description views should "change places".
        // This should be a value between 0 and 1, coinciding with a point between the bottom
        // sheet's collapsed (0) and expanded (1) states.
        private const val ALPHA_CHANGEOVER = 0.33f
        // Threshold for when description views reach maximum alpha. Should be a value between
        // 0 and [ALPHA_CHANGEOVER], inclusive.
        private const val ALPHA_DESC_MAX = 0f
        // Threshold for when normal header views reach maximum alpha. Should be a value between
        // [ALPHA_CHANGEOVER] and 1, inclusive.
        private const val ALPHA_HEADER_MAX = 0.67f
    }

    private lateinit var binding: FragmentFilterBinding

    private lateinit var behavior: BottomSheetBehavior<*>

    private var headerAlpha = ObservableFloat(1f)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        binding.apply {
            lifecycleOwner = this@FilterFragment
            headerAlpha = this@FilterFragment.headerAlpha
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        behavior = BottomSheetBehavior.from(binding.filterSheet)

        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, slideOffset: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                updateFilterHeadersAlpha(slideOffset)
            }

        })

        binding.expand.setOnClickListener {
            behavior.state = STATE_EXPANDED
        }

        binding.filterSheet.doOnLayout {
            val slideOffset = when (behavior.state) {
                STATE_EXPANDED -> 1f
                STATE_COLLAPSED -> 0f
                else /*BottomSheetBehavior.STATE_HIDDEN*/ -> -1f
            }
            updateFilterHeadersAlpha(slideOffset)
        }


    }

    private fun updateFilterHeadersAlpha(slideOffset: Float) {
        headerAlpha.set(offsetToAlpha(slideOffset, ALPHA_CHANGEOVER, ALPHA_HEADER_MAX))
    }

    private fun offsetToAlpha(value: Float, rangeMin: Float, rangeMax: Float): Float {
        return ((value - rangeMin) / (rangeMax - rangeMin)).coerceIn(0f, 1f)
    }
}

package com.chunchiehliang.kotlin.demo.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentPagerBinding
import com.chunchiehliang.kotlin.demo.widget.FadingSnackbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class PagerFragment : Fragment() {
    companion object {
        private const val COUNT = 3
    }

    private lateinit var pagerViewModel: PagerViewModel

    private lateinit var filterFab: FloatingActionButton
    private lateinit var viewPager: ViewPager
    private lateinit var snackbar: FadingSnackbar
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)

        filterFab = binding.fabNewRecipe
        viewPager = binding.viewpager

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                pagerViewModel.handleFab(position)
            }
        })

        pagerViewModel = ViewModelProviders.of(this).get(PagerViewModel::class.java)

        pagerViewModel.showFabEvent.observe(this, Observer {
            if (it) binding.fabNewRecipe.show() else binding.fabNewRecipe.hide()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout: TabLayout = view.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.filter_sheet)!!)

        // todo: fix this using viewmodel
        bottomSheetBehavior.state = STATE_HIDDEN

        filterFab.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        viewPager.adapter = PagerAdapter(childFragmentManager)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = COUNT

        override fun getItem(position: Int): Fragment {
            return when (position) {
                1 -> TestFragment()
                else -> ListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> getString(R.string.text_now_playing)
                1 -> getString(R.string.text_coming_soon)
                2 -> getString(R.string.text_my_favorite)
                else -> "tab $position"
            }
        }
    }
}



package com.chunchiehliang.kotlin.demo.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentPagerBinding
import com.chunchiehliang.kotlin.demo.util.fabVisibility
import com.chunchiehliang.kotlin.demo.widget.FadingSnackbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class PagerFragment : Fragment() {
    companion object {
        private const val COUNT = 4
    }

    private lateinit var pagerViewModel: PagerViewModel

    private lateinit var filterFab: FloatingActionButton
    private lateinit var viewPager: ViewPager
    private lateinit var snackbar: FadingSnackbar
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)
        filterFab = binding.filterFab
        viewPager = binding.viewpager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout: TabLayout = view.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.filter_sheet)!!)

        // todo: fix this using updateFiltersUi
        bottomSheetBehavior.state = STATE_HIDDEN

        filterFab.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        viewPager.adapter = PagerAdapter(childFragmentManager)
    }

    // todo: fix this
    private fun updateFiltersUi(uiState: TransientUiState) {
        val showFab = !uiState.isAgendaPage && !uiState.hasAnyFilters
        val hideable = uiState.isAgendaPage || !uiState.hasAnyFilters

        fabVisibility(filterFab, showFab)
        // Set snackbar position depending whether fab/filters show.
        snackbar.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            bottomMargin = resources.getDimensionPixelSize(
                if (showFab) {
                    R.dimen.snackbar_margin_bottom_fab
                } else {
                    R.dimen.bottom_sheet_peek_height
                }
            )
        }
        bottomSheetBehavior.isHideable = hideable
        bottomSheetBehavior.skipCollapsed = !uiState.hasAnyFilters
        if (hideable && bottomSheetBehavior.state == STATE_COLLAPSED) {
            bottomSheetBehavior.state = STATE_HIDDEN
        }
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = COUNT

        override fun getItem(position: Int): Fragment {
            return when (position) {
                1 -> DetailFragment()
                else -> ListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "tab $position"
                else -> "tab $position"
            }
        }
    }
}



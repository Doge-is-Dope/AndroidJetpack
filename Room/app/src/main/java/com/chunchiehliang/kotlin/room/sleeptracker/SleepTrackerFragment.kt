package com.chunchiehliang.kotlin.room.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chunchiehliang.kotlin.room.R
import com.chunchiehliang.kotlin.room.database.SleepDatabase
import com.chunchiehliang.kotlin.room.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {
    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        // in kotlin, requireNotNull throws exception if null
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepTrackerViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SleepTrackerViewModel::class.java)


        binding.lifecycleOwner = this
        binding.sleepTrackerViewModel = sleepTrackerViewModel

        return binding.root
    }
}

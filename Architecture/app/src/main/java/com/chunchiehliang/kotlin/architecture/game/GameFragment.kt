package com.chunchiehliang.kotlin.architecture.game


import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.chunchiehliang.kotlin.architecture.R
import com.chunchiehliang.kotlin.architecture.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    lateinit var viewModel: GameViewModel


    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        /** Setting up LiveData observation relationship **/
//        viewModel.word.observe(this, Observer { newWord ->
//            binding.wordText.text = newWord
//        })

//        viewModel.score.observe(this, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })

        viewModel.eventGameFinish.observe(this, Observer { hasFinished ->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinishedComplete()
            }
        })

        viewModel.eventBuzz.observe(this, Observer { buzzType ->
            if (buzzType != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })

//        viewModel.currentTime.observe(this, Observer { newTime ->
//            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
//        })

        return binding.root

    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore = viewModel.score.value ?: 0
        val action =
            GameFragmentDirections.actionGameToScore(currentScore)
        NavHostFragment.findNavController(this).navigate(action)
//        Toast.(context, "Game has finished", Toast.LENGTH_SHORT).show()
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}

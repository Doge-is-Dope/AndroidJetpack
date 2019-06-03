package com.chunchiehliang.kotlin.architecture


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
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

        viewModel.word.observe(this, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.score.observe(this, Observer { newScore -> binding.scoreText.text = newScore.toString() })

        viewModel.eventGameFinished.observe(this, Observer { hasFinished ->
            if (hasFinished) {
                gameFinished()
            }
        })


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }



        return binding.root

    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
//        val currentScore = viewModel.score.value ?: 0
//        val action = GameFragmentDirections.actionGameToScore(currentScore)
//        NavHostFragment.findNavController(this).navigate(action)
        Toast.makeText(context, "Game has finished", Toast.LENGTH_SHORT).show()
    }
}

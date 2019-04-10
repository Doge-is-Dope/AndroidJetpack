package com.chunchiehliang.kotlin.selftriage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.chunchiehliang.kotlin.selftriage.databinding.FragmentQuestionBinding
import com.chunchiehliang.kotlin.selftriage.model.Question


class QuestionFragment : Fragment() {

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "Headaches",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Dizziness or faintness",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Pains in heart or chest",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Pains in lower back",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Nausea or upset stomach",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Soreness of your muscles",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Trouble getting your breathe",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        ),
        Question(
            text = "Hot or cold spells",
            answers = listOf("Extremely", "Quite a bit", "Moderately", "A little", "Not at all")
        )


    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false
        )

        setQuestion()

        binding.question = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.radioGroupOptions.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                    R.id.fifthAnswerRadioButton -> answerIndex = 4
                }

                questionIndex++
                // Advance to the next question
                if (questionIndex < numQuestions) {
                    currentQuestion = questions[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                } else {
                    view.findNavController().navigate(R.id.action_questionFragment_to_resultFragment)

                }
            }
        }

        return binding.root
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
    }
}

package ru.andyup.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private var numberOfAnswers = 0
    private var numberOfCorrectAnswers = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas , true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        trueButton.setOnClickListener {
            checkAnswer(true)
            disableButtons(listOf(trueButton, falseButton))
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            disableButtons(listOf(trueButton, falseButton))
        }

        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
            enableButtons(listOf(trueButton, falseButton))
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            enableButtons(listOf(trueButton, falseButton))
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    private fun updateQuestion() {
        if (numberOfAnswers == questionBank.size) {
            Toast.makeText(this, "You've finished all questions with the result of ${(numberOfCorrectAnswers / numberOfAnswers.toDouble() * 100).toInt()}%", Toast.LENGTH_LONG)
                .show()
        }
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            ++numberOfCorrectAnswers
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        ++numberOfAnswers
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    private fun disableButtons(btns: List<Button>) {
        for (btn in btns) {
            if (btn.isEnabled) {
                btn.isEnabled = false
            }
        }
    }

    private fun enableButtons(btns: List<Button>) {
        for (btn in btns) {
            if (!btn.isEnabled) {
                btn.isEnabled = true
            }
        }
    }
}

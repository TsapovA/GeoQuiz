package ru.andyup.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas , true),
        Question(R.string.question_asia, true))

    private var usedNumberOfCheats = 0
    private val questionCheatFlags = BooleanArray(questionBank.size)

    fun processUserUseCheat() {
        if (isUserUsedCheatForTheCurrentQuestion()) {
            return
        }
        questionCheatFlags[currentIndex] = true
        ++usedNumberOfCheats
    }

    fun isUserCanCheat() = usedNumberOfCheats < 3

    fun isUserUsedCheatForTheCurrentQuestion() = questionCheatFlags[currentIndex]

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}
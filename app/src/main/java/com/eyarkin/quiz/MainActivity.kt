package com.eyarkin.quiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.math.abs
import kotlin.math.sqrt
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var prevButton: ImageButton
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0
    private var currentNextIndex = 0
    private var currentBackIndex = questionBank.size
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")


        val toastCorrect = Toast.makeText(
            this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT
        )
        val toastIncorrect = Toast.makeText(
            this,
            R.string.incorrect_toast,
            Toast.LENGTH_SHORT
        )
        prevButton = findViewById(R.id.prev_button)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        updateQuestion()
        questionTextView.setOnClickListener {view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size //интересно реализован цикл
            updateQuestion()
        }
        prevButton.setOnClickListener {view: View ->
            currentIndex = currentIndex - 1
            if (currentIndex <0) {
                currentIndex = questionBank.size-1
            }
            updateQuestion()
        }
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        nextButton.setOnClickListener {view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size //интересно реализован цикл
            updateQuestion()
        }

    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,
            Toast.LENGTH_SHORT)
            .show()
    }
}
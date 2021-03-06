package com.eyarkin.quiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toastCorrect = Toast.makeText(this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT)
        val toastIncorrect = Toast.makeText(this,
            R.string.incorrect_toast,
            Toast.LENGTH_SHORT)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        trueButton.setOnClickListener { view: View ->
            toastCorrect.setGravity(Gravity.TOP, 0, 0)
            toastCorrect.show()
        }
        falseButton.setOnClickListener { view: View ->
            toastIncorrect.setGravity(Gravity.TOP, 0, 0)
            toastIncorrect.show()
        }
    }
}
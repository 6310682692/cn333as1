package com.example.guess_num

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var Check: ImageButton
    lateinit var Refresh: ImageButton
    lateinit var Guess_Result: TextView

    var random: Int = nextInt(1, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count = 0

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        Check = findViewById(R.id.Check)
        Refresh = findViewById(R.id.Refresh)
        Guess_Result = findViewById(R.id.Guess_Result)

        textView.text = "Please Enter Your Guess"
        Guess_Result.text = "Your Guess Number"

        Check.setOnClickListener{
            count++
            val number: Int = editText.text.toString().toInt()

                if (number < random){
                    textView.text = "INCORRECT, Your Guess is too LOW!"
                    editText.text.clear()
                }
                else if(number > random){
                    textView.text = "INCORRECT, Your Guess is too HIGH!"
                    editText.text.clear()
                }
                else{
                    textView.text = "Congrat, You Guess Correctly"
                    Guess_Result.text = "Your Attempt: $count"
                    count = 0
                    editText.text.clear()
                }
        }

            Refresh.setOnClickListener{
                reset()
            }

    }
    fun reset(){
        random = nextInt(1, 100)
        textView.text = "Please Enter Your Guess"
        editText.text.clear()
    }
}
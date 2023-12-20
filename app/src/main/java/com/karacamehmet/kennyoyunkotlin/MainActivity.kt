package com.karacamehmet.kennyoyunkotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.karacamehmet.kennyoyunkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            binding.buttonStart.isEnabled = false
            binding.imageViewKenny.isClickable = true
            score = 0
            binding.textViewScore.text = "Score: ${score}"

            binding.imageViewKenny.setOnClickListener {
                score++
                binding.textViewScore.text = "Score: ${score}"
            }

            object : CountDownTimer(20000, 500) {
                var counter = true
                override fun onTick(millisUntilFinished: Long) {
                    counter != counter
                    if (counter) {
                        binding.textViewTime.text = "Time: ${millisUntilFinished / 1000}"
                    }

                    val parent = binding.imageViewKenny.parent as ViewGroup
                    val randomX =
                        (parent.width - binding.imageViewKenny.width).coerceAtLeast(-(parent.width) / 2)
                            .let { (-it..it).random() }
                    val randomY =
                        (parent.height - binding.imageViewKenny.height).coerceAtLeast(-(parent.height) / 2)
                            .let { (-it..it).random() }
                    val layoutParams =
                        binding.imageViewKenny.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.leftMargin = randomX
                    layoutParams.topMargin = randomY
                    binding.imageViewKenny.layoutParams = layoutParams
                }

                override fun onFinish() {
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle("Game Over, Your Score is: ${score}")
                        .setMessage("Would you like to play again?")
                        .setPositiveButton("Yes") { dialog, which ->
                            binding.buttonStart.isEnabled = true
                            binding.buttonStart.performClick()
                        }
                        .setNegativeButton("No") { dialog, which ->
                            binding.buttonStart.isEnabled = true
                            binding.imageViewKenny.isClickable = false
                        }
                        .show()
                }
            }.start()
        }


    }
}
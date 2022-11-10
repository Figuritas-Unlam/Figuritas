package ar.edu.unlam.figuritas.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import ar.edu.unlam.figuritas.databinding.ActivityInitScreenBinding


class InitScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitScreenBinding.inflate(layoutInflater)

        binding.openPackButton.setOnClickListener {
            val openPackActivityIntent = Intent(baseContext, OpenPackActivity::class.java)
            startActivity(openPackActivityIntent)
        }
        binding.ivTitle.setOnClickListener {
            val mapActivityIntent = Intent(baseContext, MapActivity::class.java)
            startActivity(mapActivityIntent)
        }
        setContentView(binding.root)

    }

    private fun initMotionLayout() {
        with(binding) {
            constraint.addTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) { }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) { }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                 /*  intent = Intent(applicationContext, SecondActivity::class.java)
                    startActivity(intent)*/
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) { }
            })
        }
    }

}
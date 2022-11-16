package ar.edu.unlam.figuritas.ui.activities

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import ar.edu.unlam.figuritas.databinding.ActivityInitScreenBinding
import ar.edu.unlam.figuritas.ui.viewModel.FiguritasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitScreenActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityInitScreenBinding
    private val viewModel: FiguritasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitScreenBinding.inflate(layoutInflater)

        binding.openPackButton.setOnClickListener {
            val openPackActivityIntent = Intent(baseContext, OpenPackActivity::class.java)
            startActivity(openPackActivityIntent)
        }
        setContentView(binding.root)
        setShakeSensor()
        initMotionLayout()
        initOpenPack()
        initMisFiguritas()
        initAlbum()
        initSwaps()
    }

    private fun initMotionLayout() {
        with(binding) {
            constraint.addTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    /*  intent = Intent(applicationContext, SecondActivity::class.java)
                       startActivity(intent)*/
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {
                }
            })
        }
    }

    private fun initOpenPack() {
        binding.ivOpenPack.setOnClickListener {
            val intent = Intent(applicationContext, OpenPackActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSwaps() {
        binding.swapsButton.setOnClickListener {
            val intent = Intent(applicationContext, SwapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAlbum() {
        binding.ivMyAlbum.setOnClickListener {
            val intent = Intent(applicationContext, AlbumActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initMisFiguritas() {
        binding.clMisFiguritas.setOnClickListener {
            val intent = Intent(applicationContext, MyFiguritasActivity::class.java)
            startActivity(intent)
        }
    }

    //Sense Shake
    private fun setShakeSensor() {
        viewModel.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onSensorChanged(srEvent: SensorEvent?) {
        if (srEvent != null && srEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            startOpenPack(srEvent)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    private fun startOpenPack(event: SensorEvent) {
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        val accelerationSquareRoot =
            (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        if (accelerationSquareRoot >= 12) {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(300)
            val openPackActivityIntent = Intent(baseContext, OpenPackActivity::class.java)
            startActivity(openPackActivityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sensorManager.registerListener(
            this,
            viewModel.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        viewModel.sensorManager.unregisterListener(this)
    }

}
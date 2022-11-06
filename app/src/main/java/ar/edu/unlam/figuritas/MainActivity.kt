package ar.edu.unlam.figuritas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.edu.unlam.figuritas.databinding.ActivityMainBinding
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*binding.hellos.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }*/
        AppCenter.start(
            application, "{Your app secret here}",
            Analytics::class.java, Crashes::class.java
        )
    }
}
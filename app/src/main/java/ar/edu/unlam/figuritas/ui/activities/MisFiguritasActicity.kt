package ar.edu.unlam.figuritas.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.figuritas.databinding.ActivityInitScreenBinding
import ar.edu.unlam.figuritas.databinding.ActivityMisFiguritasBinding

class MisFiguritasActicity : AppCompatActivity() {

    private lateinit var binding: ActivityMisFiguritasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisFiguritasBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package ar.edu.unlam.figuritas.ui.activities

import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.TRANSPARENT
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.databinding.ActivityMainBinding
import ar.edu.unlam.figuritas.ui.fragments.DetailFiguritaFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var figuritaFragment : DetailFiguritaFragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        figuritaFragment = DetailFiguritaFragment()
        supportFragmentManager.beginTransaction().add(R.id.espacioFragmentFigurita, Fragment()).commit()

        transaction = supportFragmentManager.beginTransaction()

        mainBinding.boton.setOnClickListener {
            mainBinding.mainActiviy.backgroundTintMode

            mainBinding.boton.visibility = View.GONE
            transaction.replace(R.id.espacioFragmentFigurita, figuritaFragment).commit()

        }
    }


}
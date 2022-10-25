package ar.edu.unlam.figuritas

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import ar.edu.unlam.figuritas.databinding.ActivityOpenPackBinding

class OpenPackActivity : AppCompatActivity() {
    private lateinit var firstCard: ImageView
    private lateinit var secondCard: ImageView
    private lateinit var thirdCard: ImageView
    private lateinit var fourthCard: ImageView
    private lateinit var fifthCard: ImageView
    private lateinit var binding: ActivityOpenPackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenPackBinding.inflate(layoutInflater)
        val view = binding.root

        firstCard = binding.imageView
        secondCard = binding.imageView2
        thirdCard = binding.imageView3
        fourthCard = binding.imageView4
        fifthCard = binding.imageView5
        var currentCard = firstCard
        var incomingCard = secondCard

        binding.button.setOnClickListener {
            ObjectAnimator.ofFloat(currentCard, "translationX", 500f).apply {
                duration = 1000
                start()
            }.doOnEnd { currentCard.visibility = View.GONE }
            ObjectAnimator.ofFloat(incomingCard, "translationY", -1300f).apply {
                duration = 1000
                start()
            }
            currentCard = secondCard
            incomingCard = thirdCard
        }

        setContentView(view)
    }
}
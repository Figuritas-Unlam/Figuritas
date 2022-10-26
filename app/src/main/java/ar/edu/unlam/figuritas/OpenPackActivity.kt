package ar.edu.unlam.figuritas

import android.os.Bundle
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
        var height = 100f

        binding.button.setOnClickListener {
            currentCard.animate().setDuration(1000).x(600f).y(height)
            incomingCard.animate().setDuration(1000).y(400f)

            currentCard = secondCard
            incomingCard = thirdCard
            height += 100
        }

        setContentView(view)
    }
}
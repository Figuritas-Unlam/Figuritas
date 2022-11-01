package ar.edu.unlam.figuritas

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ar.edu.unlam.figuritas.databinding.ActivityOpenPackBinding

class OpenPackActivity : AppCompatActivity() {
    private lateinit var incomingCard: ImageView
    private var currentCard: ImageView? = null
    private lateinit var binding: ActivityOpenPackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenPackBinding.inflate(layoutInflater)
        val view = binding.root

        incomingCard = binding.imageView1

        var height = 100f
        var incomingCardNumber = 1

        binding.button.setOnClickListener {
            currentCard?.animate()?.setDuration(1000)?.x(600f)?.y(height)
            incomingCard.animate().setDuration(1000).y(400f)

            currentCard = incomingCard
            updateViews(++incomingCardNumber)
            height += 100
        }

        setContentView(view)
    }

    private fun updateViews(incomingCardNumber: Int) {
        if (incomingCardNumber <= 5) {
            incomingCard = binding.root.findViewById(resources.getIdentifier("imageView${incomingCardNumber}", "id", BuildConfig.APPLICATION_ID))
        } else {
            binding.button.visibility = View.GONE
            for (cardNumber in 1..5) {
                val cardView: ImageView = binding.root
                    .findViewById(resources.getIdentifier("imageView${cardNumber}", "id", BuildConfig.APPLICATION_ID))
                if (cardNumber == 5) {
                    cardView.animate()
                        .setDuration(1000)
                        .setStartDelay((300*(cardNumber-1)).toLong())
                        .x(20f)
                        .y(500f)
                } else {
                    cardView.animate().setDuration(1000).setStartDelay((300*(cardNumber-1)).toLong()).x(10f*cardNumber).y(120f*(cardNumber+2))
                }
            }
        }
    }

}
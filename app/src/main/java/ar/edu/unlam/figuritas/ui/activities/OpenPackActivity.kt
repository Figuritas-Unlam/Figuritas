package ar.edu.unlam.figuritas.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.figuritas.BuildConfig
import ar.edu.unlam.figuritas.data.api.PlayerClient
import ar.edu.unlam.figuritas.data.repository.PlayerRepository
import ar.edu.unlam.figuritas.databinding.ActivityOpenPackBinding
import ar.edu.unlam.figuritas.ui.viewmodel.OpenPackViewModel

class OpenPackActivity : AppCompatActivity() {
    private lateinit var incomingCard: ImageView
    private var currentCard: ImageView? = null
    private lateinit var binding: ActivityOpenPackBinding
    private lateinit var openPackViewModel: OpenPackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenPackBinding.inflate(layoutInflater)
        val view = binding.root
        suscribeToViewModel()

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

    private fun suscribeToViewModel() {
        val client = PlayerClient()
        val repo = PlayerRepository(client)
        openPackViewModel = OpenPackViewModel(repo)
        openPackViewModel.playerData.observe(this) { it ->
            Log.d("RESPONSE:", it.toString())
            binding.playerData.text = it.map { playerData -> Pair(playerData?.data?.fullname, playerData?.data?.nationality) }.toString()
        }
    }

    private fun updateViews(incomingCardNumber: Int) {
        if (incomingCardNumber <= 5) {
            incomingCard = binding.root.findViewById(resources.getIdentifier("imageView${incomingCardNumber}", "id",
                BuildConfig.APPLICATION_ID
            ))
        } else {
            binding.button.animate().setDuration(300).alpha(0F).withEndAction {
                binding.button.visibility = View.GONE
            }
            for (cardNumber in 1..5) {
                val cardView: ImageView = binding.root
                    .findViewById(resources.getIdentifier("imageView${cardNumber}", "id",
                        BuildConfig.APPLICATION_ID
                    ))
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
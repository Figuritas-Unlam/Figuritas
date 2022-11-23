package ar.edu.unlam.figuritas.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.figuritas.BuildConfig
import ar.edu.unlam.figuritas.databinding.ActivityOpenPackBinding
import dagger.hilt.android.AndroidEntryPoint
import ar.edu.unlam.figuritas.ui.OpenPackViewModel
import ar.edu.unlam.figuritas.domain.response.PlayerResponse
import ar.edu.unlam.figuritas.ui.fragments.StickerItemFragment

@AndroidEntryPoint
class OpenPackActivity : AppCompatActivity() {
    private lateinit var incomingCard: View
    private var currentCard: View? = null
    private lateinit var binding: ActivityOpenPackBinding
    private val openPackViewModel: OpenPackViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenPackBinding.inflate(layoutInflater)
        val view = binding.root
        suscribeToViewModel()

        incomingCard = binding.stickerView1

        var height = 300f
        var incomingCardNumber = 1

        binding.nextButton.setOnClickListener {
            (it as Button).isClickable = false
            currentCard?.animate()?.setDuration(1000)?.x(600f)?.y(height)
            incomingCard.animate().setDuration(1000).y(550f).withEndAction{
                it.isClickable = true
            }

            currentCard = incomingCard
            updateViews(++incomingCardNumber)
            height += 150
        }

        setContentView(view)
    }

    private fun suscribeToViewModel() {
        openPackViewModel.playersData.observe(this) {
            Log.d("RESPONSE:", it.toString())
            setPackPlayers(it)
            binding.loader.visibility = View.GONE
            binding.nextButton.visibility = View.VISIBLE
        }
        openPackViewModel.error.observe(this) { error ->
            if (error) { showErrorDialog() }
        }
    }

    private fun setPackPlayers(playersData: List<PlayerResponse?>) {
        for((index, playerData) in playersData.withIndex()) {
            val stickerView = supportFragmentManager.findFragmentById((resources.getIdentifier("stickerView${index+1}", "id",
                BuildConfig.APPLICATION_ID
            ))) as StickerItemFragment
            playerData?.data?.let { stickerView.passPlayerDataToFragment(it) }
        }
    }

    private fun updateViews(incomingCardNumber: Int) {
        if (incomingCardNumber <= 5) {
            incomingCard = binding.root.findViewById(resources.getIdentifier("stickerView${incomingCardNumber}", "id",
                BuildConfig.APPLICATION_ID
            ))
        } else {
            binding.nextButton.animate().setDuration(300).alpha(0F).withEndAction {
                binding.nextButton.visibility = View.GONE
            }
            for (cardNumber in 1..5) {
                val cardView: View = binding.root
                    .findViewById(resources.getIdentifier("stickerView${cardNumber}", "id",
                        BuildConfig.APPLICATION_ID
                    ))
                if (cardNumber == 5) {
                    cardView.animate()
                        .setDuration(1000)
                        .setStartDelay((300*(cardNumber-1)).toLong())
                        .y(600f)
                } else {
                    cardView.animate()
                        .setDuration(1000)
                        .setStartDelay((300*(cardNumber-1)).toLong())
                        .x(280f+(40*cardNumber))
                        .y(400f+(cardNumber*80))
                }
            }
        }
    }

    private fun showErrorDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Hubo un problema")
            .setMessage("Chequeá to conexion e intenta mas tarde")
            .setPositiveButton("Ok") { _, _ ->
                finish()
            }
        dialog.show()
    }

}
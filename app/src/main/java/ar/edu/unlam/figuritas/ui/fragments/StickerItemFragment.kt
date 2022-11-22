package ar.edu.unlam.figuritas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.databinding.ItemCardFiguritaBinding
import ar.edu.unlam.figuritas.domain.response.PlayerResponseData
import com.squareup.picasso.Picasso

class StickerItemFragment: Fragment(), FragmentPlayerCommunicator {
    private lateinit var binding : ItemCardFiguritaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_card_figurita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ItemCardFiguritaBinding.bind(view)
    }

    override fun passPlayerDataToFragment(data: PlayerResponseData) {
        Picasso.get().load(data.image).into(binding.imagenJugador)
        binding.nombreJugador.text = data.name
        binding.alturaJugador.text = data.height
        binding.pesoJugador.text = data.weight
        binding.nationality.text = data.nationality
        binding.birthdate.text = data.birthdate
    }
}
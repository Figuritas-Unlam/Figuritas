package ar.edu.unlam.figuritas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.databinding.ItemCardFiguritaBinding

class StickerItemFragment: Fragment() {
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
}
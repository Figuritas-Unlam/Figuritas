package ar.edu.unlam.figuritas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.databinding.FragmentDetailFiguritaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFiguritaFragment : Fragment() {

    private lateinit var detailFiguritaBinding: FragmentDetailFiguritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_figurita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailFiguritaBinding = FragmentDetailFiguritaBinding.bind(view)

    }


}
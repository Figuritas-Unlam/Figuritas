package ar.edu.unlam.figuritas.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import ar.edu.unlam.figuritas.databinding.ActivityAlbumBinding
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.ui.adapter.AlbumAdapter
import ar.edu.unlam.figuritas.ui.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {

    private lateinit var albumBinding: ActivityAlbumBinding
    private lateinit var albumAdapter : AlbumAdapter
    private lateinit var listCountries : MutableList<String>
    private lateinit var listSelecciones : MutableList<Seleccion>

    private val albumViewModel : AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumBinding = ActivityAlbumBinding.inflate(layoutInflater)
        val view = albumBinding.root
        setContentView(view)
        setupObservers()
        initRecyclerView()
    }



    private fun initRecyclerView(){
        listSelecciones = albumViewModel.searchPlayers()
        albumAdapter = AlbumAdapter(listSelecciones, applicationContext)
        albumBinding.rvSelecciones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        albumBinding.rvSelecciones.adapter = albumAdapter
    }

    private fun setupObservers(){
/*
        albumViewModel.searchPlayersAvailable()*/
    }
}

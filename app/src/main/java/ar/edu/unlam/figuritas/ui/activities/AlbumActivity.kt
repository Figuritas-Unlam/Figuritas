package ar.edu.unlam.figuritas.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unlam.figuritas.R

import ar.edu.unlam.figuritas.databinding.ActivityAlbumBinding
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.model.WorldCupTeamId
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import ar.edu.unlam.figuritas.ui.OpenPackViewModel
import ar.edu.unlam.figuritas.ui.adapter.AlbumAdapter
import ar.edu.unlam.figuritas.ui.viewModel.AlbumViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {

    private lateinit var albumBinding: ActivityAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var listPlayers : List<PlayerEntity>
    private lateinit var listSelecciones: MutableList<Seleccion>
    private lateinit var player: PlayerResponse

    private val albumViewModel: AlbumViewModel by viewModels()
    private val nuevasViewMdel: OpenPackViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumBinding = ActivityAlbumBinding.inflate(layoutInflater)
        val view = albumBinding.root

        verifiyIntent()
        initMyFiguritas()
        setContentView(view)

    /*
        albumViewModel.setCountrys()
        albumViewModel.searchSelecciones()
        subscribeToViewModel()

        figus()*/


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerAlbumCompleted() {

        albumAdapter = AlbumAdapter(albumViewModel.insertPlayersInSelecciones(), applicationContext, albumViewModel, false)
        albumAdapter.notifyDataSetChanged()
        albumBinding.rvSelecciones.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        albumBinding.rvSelecciones.adapter = albumAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerIndividual(countryId : Int) {

        albumAdapter = AlbumAdapter(albumViewModel.insertPlayersInSeleccion(countryId),
            applicationContext, albumViewModel, true)
        albumAdapter.notifyDataSetChanged()
        albumBinding.rvSelecciones.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        albumBinding.rvSelecciones.adapter = albumAdapter

    }


    private fun initMyFiguritas() {

        albumBinding.figurita.setOnClickListener {
            val intent = Intent(applicationContext, MyFiguritasActivity::class.java)
            startActivity(intent)
        }
    }


    private fun verifiyIntent() {

            var playerId = intent.getIntExtra("id", 0)
            if (playerId == 0) {
                initRecyclerAlbumCompleted()
            } else {
                albumViewModel.playerId = playerId
                var player = albumViewModel.databaseRepository.getPlayerForId(playerId)
                initRecyclerIndividual(player!!.seleccionId)
            }
        }


}

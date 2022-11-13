package ar.edu.unlam.figuritas.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import ar.edu.unlam.figuritas.databinding.ActivityAlbumBinding
import ar.edu.unlam.figuritas.ui.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {

    private lateinit var albumBinding: ActivityAlbumBinding
    private val albumViewModel : AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumBinding = ActivityAlbumBinding.inflate(layoutInflater)
    }
}

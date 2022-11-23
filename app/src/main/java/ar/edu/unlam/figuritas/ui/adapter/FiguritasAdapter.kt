package ar.edu.unlam.figuritas.ui.adapter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.data.database.entities.PlayerEntity
import ar.edu.unlam.figuritas.databinding.ItemFiguritaBinding
import ar.edu.unlam.figuritas.model.entities.PlayerAlbumEntity
import ar.edu.unlam.figuritas.ui.viewModel.AlbumViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FiguritasAdapter(var figuritas: List<PlayerEntity>,
                       var albumViewModel: AlbumViewModel,
                       var imageCountry : String,
                       val nameSeleccion : String,
                       var inInsert : Boolean,
                       var applicationContext: Context
) : RecyclerView.Adapter<FiguritaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiguritaViewHolder {
        val figuritas =
            ItemFiguritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FiguritaViewHolder(figuritas)
    }

    override fun onBindViewHolder(holder: FiguritaViewHolder, position: Int) {

        val player = figuritas[position]
        bind(holder, player, imageCountry, nameSeleccion, position, inInsert, albumViewModel, applicationContext)
    }

    override fun getItemCount(): Int = figuritas.size


}

class FiguritaViewHolder(val binding : ItemFiguritaBinding) : RecyclerView.ViewHolder(binding.root)

private fun bind(
    holder : FiguritaViewHolder,
    player : PlayerEntity,
    imageCountry: String,
    nameSeleccion: String,
    position: Int,
    inInsert : Boolean,
    albumViewModel: AlbumViewModel,
    applicationContext: Context
){

    holder.binding.alturaJugador.text = player?.height
    holder.binding.pesoJugador.text = player?.weight
    holder.binding.nombreJugador.text = player?.playerName
    holder.binding.fechaNacimiento.text = player?.birthdate
    holder.binding.nameSeleccion.text = nameSeleccion

    if(inInsert){
        holder.binding.pasteFigu.visibility = View.VISIBLE
        holder.binding.imagenJugador.visibility = View.INVISIBLE
    }
    else
    {
        holder.binding.pasteFigu.visibility = View.INVISIBLE
    }

    holder.binding.pasteFigu.setOnClickListener {
        try {
            albumViewModel.databaseRepository.insertPlayerInAlbum(
                PlayerAlbumEntity(
                    albumViewModel.playerId,
                    position
                )
            )
            Toast.makeText(applicationContext, "Se pego la figurita", Toast.LENGTH_SHORT).show()
        }
        catch (e : SQLiteConstraintException){
            Toast.makeText(applicationContext, "La figurita ya se pego", Toast.LENGTH_SHORT).show()
        }
    }
    Picasso.get()
        .load(player.imageUrl)
        .placeholder(R.drawable.image_not_found)
        .into(holder.binding.imagenJugador)

    Picasso.get()
        .load(imageCountry)
        .placeholder(R.drawable.bandera_not_found)
        .into(holder.binding.imageCountry)

}
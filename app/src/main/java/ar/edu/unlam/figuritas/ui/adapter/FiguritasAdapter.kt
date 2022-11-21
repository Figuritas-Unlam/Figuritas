package ar.edu.unlam.figuritas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.R
import ar.edu.unlam.figuritas.databinding.ItemFiguritaBinding
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.response.PlayerResponseData
import com.squareup.picasso.Picasso

class FiguritasAdapter(var figuritas: List<PlayerEntity>
) : RecyclerView.Adapter<FiguritaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiguritaViewHolder {
        val figuritas =
            ItemFiguritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FiguritaViewHolder(figuritas)
    }

    override fun onBindViewHolder(holder: FiguritaViewHolder, position: Int) {

        val player = figuritas[position]
        bind(holder, player)
    }

    override fun getItemCount(): Int = figuritas.size


}

class FiguritaViewHolder(val binding : ItemFiguritaBinding) : RecyclerView.ViewHolder(binding.root)

private fun bind(
    holder : FiguritaViewHolder,
    player : PlayerEntity
){

    holder.binding.alturaJugador.text = player.height
    holder.binding.pesoJugador.text = player.weight
    holder.binding.nombreJugador.text = player.playerName
    holder.binding.fechaNacimiento.text = player.birthdate


    Picasso.get()
        .load(player.imageUrl)
        .placeholder(R.drawable.image_not_found)
        .into(holder.binding.imagenJugador)

}
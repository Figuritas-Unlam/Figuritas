package ar.edu.unlam.figuritas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.databinding.ItemCardFiguritaBinding
import ar.edu.unlam.figuritas.model.response.PlayerResponse
import com.squareup.picasso.Picasso

class SwapsStickersAdapter(private val figuritasRepetidas: Array<PlayerResponse>) :
    RecyclerView.Adapter<SwapsStickersAdapter.SwapsStickersViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SwapsStickersViewHolder {
        val stickerBiding = ItemCardFiguritaBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return SwapsStickersViewHolder(stickerBiding)
    }

    override fun onBindViewHolder(viewHolder: SwapsStickersViewHolder, position: Int) {
        viewHolder.bind(figuritasRepetidas[position])
    }

    override fun getItemCount() = figuritasRepetidas.size

    class SwapsStickersViewHolder(val binding : ItemCardFiguritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playerData: PlayerResponse) {
            binding.birthdate.text = playerData.data.birthdate
            binding.pesoJugador.text = playerData.data.weight
            binding.nationality.text = playerData.data.nationality
            Picasso.get().load(playerData.data.image).into(binding.imagenJugador)
            binding.nombreJugador.text = playerData.data.name
        }
    }
}

package ar.edu.unlam.figuritas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.databinding.ItemCardFiguritaBinding
import ar.edu.unlam.figuritas.domain.model.PlayerModel
import com.squareup.picasso.Picasso

class SwapsStickersAdapter(
    private val swappableStickers: List<PlayerModel>
) : RecyclerView.Adapter<SwapsStickersAdapter.SwapsStickersViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SwapsStickersViewHolder {
        val stickerBinding = ItemCardFiguritaBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return SwapsStickersViewHolder(stickerBinding)
    }

    override fun onBindViewHolder(viewHolder: SwapsStickersViewHolder, position: Int) {
        val item = swappableStickers[position]
        viewHolder.bind(item)
        viewHolder.itemView.setOnClickListener {
            if (swappableStickers.count { sticker -> sticker.isSelected } < 3 || item.isSelected) {
                item.isSelected = !item.isSelected
                it.alpha = if (item.isSelected) 1F else 0.5F
            }
        }
    }

    override fun getItemCount() = swappableStickers.size

    class SwapsStickersViewHolder(val binding : ItemCardFiguritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playerData: PlayerModel) {
            binding.root.isClickable = true
            binding.root.alpha = if (playerData.isSelected) 1F else 0.5F
            binding.birthdate.text = playerData.birthdate
            binding.pesoJugador.text = playerData.weight
            binding.nationality.text = playerData.nationality
            Picasso.get().load(playerData.imageUrl).into(binding.imagenJugador)
            binding.nombreJugador.text = playerData.playerName
        }
    }
}

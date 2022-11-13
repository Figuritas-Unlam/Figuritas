package ar.edu.unlam.figuritas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.databinding.ItemFiguritaBinding
import ar.edu.unlam.figuritas.model.response.PlayerResponseData

class FiguritasAdapter(var figuritas: MutableList<PlayerResponseData>
) : RecyclerView.Adapter<FiguritaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiguritaViewHolder {
        val figuritas =
            ItemFiguritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FiguritaViewHolder(figuritas)
    }

    override fun onBindViewHolder(holder: FiguritaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = figuritas.size


}

class FiguritaViewHolder(val binding : ItemFiguritaBinding) : RecyclerView.ViewHolder(binding.root)
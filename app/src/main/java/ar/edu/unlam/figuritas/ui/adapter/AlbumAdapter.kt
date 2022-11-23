package ar.edu.unlam.figuritas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.figuritas.databinding.ItemSeleccionBinding
import ar.edu.unlam.figuritas.model.Seleccion
import ar.edu.unlam.figuritas.ui.viewModel.AlbumViewModel

class AlbumAdapter(

    var countries: MutableList<Seleccion>,
    var applicationContext: Context,
    var albumViewModel: AlbumViewModel,
    var inInsert : Boolean
) : RecyclerView.Adapter<SeleccionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeleccionViewHolder {
        val countries =
            ItemSeleccionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SeleccionViewHolder(countries)
    }

    override fun onBindViewHolder(holder: SeleccionViewHolder, position: Int) {

        val seleccion = countries[position]
        bind(holder, seleccion, applicationContext, albumViewModel, inInsert)
    }

    override fun getItemCount(): Int = countries.size

}

class SeleccionViewHolder(val binding:ItemSeleccionBinding) : RecyclerView.ViewHolder(binding.root)

private fun bind(
    holder : SeleccionViewHolder,
    seleccion : Seleccion,
    applicationContext: Context,
    albumViewModel: AlbumViewModel,
    inInsert: Boolean
){
    holder.binding.nameSeleccion.text = seleccion.nameCountry
    val figuritasAdapter = FiguritasAdapter(seleccion.players, albumViewModel, seleccion.imageCountry, seleccion.displayNameCountry, inInsert, applicationContext)
    holder.binding.rvFiguritas.layoutManager = GridLayoutManager(applicationContext, 2)
    holder.binding.rvFiguritas.adapter = figuritasAdapter

}
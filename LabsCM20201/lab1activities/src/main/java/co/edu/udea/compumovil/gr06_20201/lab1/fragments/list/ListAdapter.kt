package co.edu.udea.compumovil.gr06_20201.lab1.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr06_20201.lab1.R
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var lugarList = emptyList<Lugar>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return lugarList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lugarList[position]
        holder.itemView.id_descripcion.text = currentItem.descripcion
        holder.itemView.id_titulo.text = currentItem.nombre
        holder.itemView.id_ubicacion.text = currentItem.ubicacion
        holder.itemView.id_puntuacion.text = currentItem.puntuacion.toString()
        holder.itemView.id_temperatura.text = currentItem.temperatura
        holder.itemView.rowLayout.setOnClickListener{
           val action = listDirections.actionListToUpdate(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        //val diceImage: ImageView = findViewById(R.id.dice_image)
        if (currentItem.sitiosRecomendados.toString() == "botero"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.botero)
        }else if(currentItem.sitiosRecomendados.toString() == "atanasio"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.atanasio)
        }else if(currentItem.sitiosRecomendados.toString() == "metro"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.metro)
        }else if(currentItem.sitiosRecomendados.toString() == "tranvia"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.tranvia)
        }else if(currentItem.sitiosRecomendados.toString() == "trece"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.trece)
        }else if(currentItem.sitiosRecomendados.toString() == "explora"){
            holder.itemView.id_imagen.setImageResource(R.mipmap.explora)
        }else{
            holder.itemView.id_imagen.setImageResource(R.mipmap.explora)
        }

    }

    fun setData(lugar: List<Lugar>){
        this.lugarList=lugar
        notifyDataSetChanged()
    }


}
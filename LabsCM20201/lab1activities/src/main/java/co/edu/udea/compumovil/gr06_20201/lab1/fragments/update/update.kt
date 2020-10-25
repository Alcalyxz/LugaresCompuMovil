package co.edu.udea.compumovil.gr06_20201.lab1.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.edu.udea.compumovil.gr06_20201.lab1.R
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar
import co.edu.udea.compumovil.gr06_20201.lab1.viewModel.LugarViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class update : Fragment() {

    private val args by navArgs<updateArgs>()

    private lateinit var mlugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mlugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)

        view.updateNombre.setText(args.currentLugar.nombre)
        view.updateDescripcion.setText(args.currentLugar.descripcion)
        view.updatePuntuacion.setText(args.currentLugar.puntuacion.toString())
        view.updateUbicacion.setText(args.currentLugar.ubicacion)
        view.updateTemperatura.setText(args.currentLugar.temperatura)
        view.updateRecomendado.setText(args.currentLugar.sitiosRecomendados)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val nombre = updateNombre.text.toString()
        val descripcion = updateDescripcion.text.toString()
        val puntuacion = Integer.parseInt(updatePuntuacion.text.toString())
        val ubicacion = updateUbicacion.text.toString()
        val temperatura = updateTemperatura.text.toString()
        val recomendados = updateRecomendado.text.toString()

        if(inputCheck(nombre,descripcion,updatePuntuacion.text,ubicacion,temperatura,recomendados)){
            //Create Lugar Object
            var updatedLugar = Lugar(args.currentLugar.id, nombre, descripcion, puntuacion, ubicacion, temperatura, recomendados)

            //Update current Lugar
            mlugarViewModel.updateLugar(updatedLugar)
            Toast.makeText(requireContext(),"Update Sucessfully!!", Toast.LENGTH_SHORT).show()

            //Navigate back
            findNavController().navigate(R.id.action_update_to_list)
        }else{
            Toast.makeText(requireContext(),"Update Failed!!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(nombre:String, descripcion:String, puntuacion: Editable, ubicacion:String, temperatura:String, sitios:String): Boolean{
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(descripcion) && puntuacion.isEmpty() && TextUtils.isEmpty(ubicacion) && TextUtils.isEmpty(temperatura) && TextUtils.isEmpty(sitios))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteLugar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLugar(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mlugarViewModel.deleteLugar(args.currentLugar)
            Toast.makeText(requireContext(), "Succesfully remove Lugar: ${args.currentLugar.nombre}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_to_list)
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete ${args.currentLugar.nombre}?")
        builder.setMessage("Estas seguro que quieres eliminar el lugar ${args.currentLugar.nombre}?")
        builder.create().show()
    }

}
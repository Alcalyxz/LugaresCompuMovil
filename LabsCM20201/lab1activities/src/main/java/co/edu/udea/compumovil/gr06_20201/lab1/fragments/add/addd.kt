package co.edu.udea.compumovil.gr06_20201.lab1.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.gr06_20201.lab1.R
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar
import co.edu.udea.compumovil.gr06_20201.lab1.viewModel.LugarViewModel
import kotlinx.android.synthetic.main.fragment_addd.*
import kotlinx.android.synthetic.main.fragment_addd.view.*


class addd : Fragment() {

    private lateinit var mLugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addd, container, false)

        mLugarViewModel=ViewModelProvider(this).get(LugarViewModel::class.java)
        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view;
    }

    private fun insertDataToDatabase() {
        val nombre = addNombre.text.toString()
        val descripcion = addDescripcion.text.toString()
        val puntuacion = addPuntuacion.text

        val ubicacion = addUbicacion.text.toString()
        val temperatura = addTemperatura.text.toString()
        val sitios = addRecomendado.text.toString()

        if(inputCheck(nombre, descripcion, puntuacion, ubicacion, temperatura, sitios)){
            //Create LUGAR OBJECT
            val lugar = Lugar(
                0,
                nombre,
                descripcion,
                Integer.parseInt(puntuacion.toString()),
                ubicacion,
                temperatura,
                sitios
            )

            //Add data to database
            mLugarViewModel.addLugar(lugar)
            Toast.makeText(requireContext(),"Succesfully added!",Toast.LENGTH_LONG).show()

            //Navigate Back
            findNavController().navigate(R.id.action_addd_to_list)
        }else{
            Toast.makeText(requireContext(),"Sorry don´t add !",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nombre:String, descripcion:String, puntuacion: Editable, ubicacion:String, temperatura:String, sitios:String): Boolean{
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(descripcion) && puntuacion.isEmpty() && TextUtils.isEmpty(ubicacion) && TextUtils.isEmpty(temperatura) && TextUtils.isEmpty(sitios))
    }
}
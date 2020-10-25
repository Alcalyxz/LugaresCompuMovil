package co.edu.udea.compumovil.gr06_20201.lab1.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.udea.compumovil.gr06_20201.lab1.R
import co.edu.udea.compumovil.gr06_20201.lab1.viewModel.LugarViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class list : Fragment() {

    private lateinit var mLugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        //Recycler view
        val adapter = ListAdapter()

        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mLugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)
        mLugarViewModel.readAllData.observe(viewLifecycleOwner, Observer {lugar ->
            adapter.setData(lugar)
        })


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_list_to_addd)

        }

        view.logout.setOnClickListener{
            findNavController().navigate(R.id.loginAccess)
        }




        setHasOptionsMenu(true)

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllLugares()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllLugares() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mLugarViewModel.deleteAllLugares()
            Toast.makeText(requireContext(), "Succesfully remove Lugares!!", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Estas seguro que quieres eliminar TODOS los lugares?")
        builder.create().show()
    }

}
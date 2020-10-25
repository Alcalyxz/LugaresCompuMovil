package co.edu.udea.compumovil.gr06_20201.lab1.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr06_20201.lab1.data.LugarDatabase
import co.edu.udea.compumovil.gr06_20201.lab1.repository.LugarRepository
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar


class LugarViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Lugar>>
    private val repository: LugarRepository

    init{
        val lugarDao = LugarDatabase.getDatabase(
            application
        ).lugarDao()
        repository =
            LugarRepository(
                lugarDao
            )
        readAllData = repository.readAllData
    }

    fun addLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLugar(lugar)
        }
    }

    fun updateLugar(lugar:Lugar){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLugar(lugar)
        }
    }

    fun deleteLugar(lugar:Lugar){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLugar(lugar)
        }
    }

    fun deleteAllLugares(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllLugares()
        }
    }

}
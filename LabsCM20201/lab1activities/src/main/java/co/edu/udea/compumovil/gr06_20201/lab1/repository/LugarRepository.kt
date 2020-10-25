package co.edu.udea.compumovil.gr06_20201.lab1.repository

import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr06_20201.lab1.data.LugarDao
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {
    val readAllData: LiveData<List<Lugar>> = lugarDao.readAllData()

    suspend fun addLugar(lugar: Lugar){
        lugarDao.addLugar(lugar)
    }

    suspend fun deleteLugar(lugar:Lugar){
        lugarDao.deleteLugar(lugar)
    }

    suspend fun updateLugar(lugar:Lugar){
        lugarDao.updateLugar(lugar)
    }

    suspend fun deleteAllLugares(){
        lugarDao.deleteAllLugares()
    }
}
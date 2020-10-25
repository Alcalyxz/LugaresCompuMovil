package co.edu.udea.compumovil.gr06_20201.lab1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar

@Dao
interface LugarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar(lugar: Lugar)

    @Update
    suspend fun updateLugar(lugar: Lugar)

    @Delete
    suspend fun deleteLugar(lugar: Lugar)

    @Query("DELETE FROM lugar_tabla")
    suspend fun deleteAllLugares()


    @Query("SELECT * FROM lugar_tabla ORDER BY id ASC")
    fun readAllData():LiveData<List<Lugar>>
}
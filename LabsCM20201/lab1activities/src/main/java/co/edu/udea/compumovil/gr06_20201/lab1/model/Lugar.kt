package co.edu.udea.compumovil.gr06_20201.lab1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="lugar_tabla")
data class Lugar(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val puntuacion: Int,
    val ubicacion: String,
    val temperatura: String,
    val sitiosRecomendados: String
): Parcelable
package co.edu.udea.compumovil.gr06_20201.lab1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import co.edu.udea.compumovil.gr06_20201.lab1.model.Lugar

@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class LugarDatabase:RoomDatabase(){
    abstract fun lugarDao(): LugarDao

    companion object{
        @Volatile
        private var INSTANCE: LugarDatabase? = null

        fun getDatabase(context: Context): LugarDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDatabase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
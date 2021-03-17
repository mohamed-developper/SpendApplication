package tn.org.spendapplication.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.org.spendapplication.Pokemon


@Database(
    entities = [Pokemon::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {

        private var instance: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                PokemonDatabase::class.java,
                "pokemon_db"
            ).build()
        }
    }
}
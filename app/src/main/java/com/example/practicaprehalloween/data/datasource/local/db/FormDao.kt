package com.example.practicaprehalloween.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import kotlinx.coroutines.flow.Flow

@Dao // @Dao le dice a Room que esto es un Data Access Object
interface FormDao {

    // 1. @Insert le dice a Room que esta es una función de ESCRITURA.
    //    Usamos 'suspend' porque es una operación de una sola vez.
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(formEntity: FormEntity)

    // 2. @Query le dice a Room que esta es una función de LECTURA.
    //    Usamos 'Flow' para que la lista se actualice automáticamente
    //    en nuestra UI cada vez que haya un cambio en la tabla.
    @Query("SELECT * FROM form_data_table ORDER BY timestamp DESC")
    fun getAll(): Flow<List<FormEntity>>
}
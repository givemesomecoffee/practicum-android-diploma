package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.AddressEntity

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg addressEntity: AddressEntity)

    @Query("SELECT * FROM address_table")
    suspend fun select(): List<AddressEntity>

    @Query("SELECT * FROM address_table WHERE id = :id")
    suspend fun select(id: Int): List<AddressEntity>

    @Query("SELECT id FROM address_table WHERE city = :city")
    suspend fun select(city: String): List<Int>

    @Query("DELETE FROM address_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}
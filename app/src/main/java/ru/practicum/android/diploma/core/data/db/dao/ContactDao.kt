package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg contactEntity: ContactEntity)

    @Query("SELECT * FROM contact_table")
    suspend fun select(): List<ContactEntity>

    @Query("SELECT * FROM contact_table WHERE id = :id")
    suspend fun select(id: Int): List<ContactEntity>

    @Query("SELECT id FROM contact_table WHERE email = :email AND name = :name AND phones = :phones")
    suspend fun select(email: String, name: String, phones: String): List<Int>

    @Query("DELETE FROM contact_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}
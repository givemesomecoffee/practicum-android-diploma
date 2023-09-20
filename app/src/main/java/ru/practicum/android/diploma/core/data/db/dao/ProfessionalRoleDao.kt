package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.ProfessionalRoleEntity

@Dao
interface ProfessionalRoleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg professionalRoleEntity: ProfessionalRoleEntity)

    @Query("SELECT * FROM professional_role_table")
    suspend fun select(): List<ProfessionalRoleEntity>

    @Query("SELECT * FROM professional_role_table WHERE id = :id")
    suspend fun select(id: Int): List<ProfessionalRoleEntity>

    @Query("SELECT id FROM professional_role_table WHERE requestId = :requestId AND name = :name")
    suspend fun select(requestId: String, name: String): List<Int>

    @Query("DELETE FROM professional_role_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}
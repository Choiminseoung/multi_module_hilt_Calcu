package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.model.Calculation

@Dao
interface CalculationDao {
    @Insert
    suspend fun insetCalculation(calculation: CalculationEntity)

    @Query("SELECT * FROM calculations")
    suspend fun getAllCalculations(): List<CalculationEntity>
}
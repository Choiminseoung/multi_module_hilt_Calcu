package com.example.data.repository

import com.example.data.local.CalculationDao
import com.example.data.local.toCalculation
import com.example.data.local.toEntity
import com.example.domain.model.Calculation
import com.example.domain.repository.CalculatorRepository
import javax.inject.Inject

class CalculatorRepositoryImpl @Inject constructor(
    private val calculationDao: CalculationDao) : CalculatorRepository {
    override suspend fun saveCalculation(calculation: Calculation) {
        calculationDao.insetCalculation(calculation.toEntity())
    }

    override suspend fun getCalculationHistory(): List<Calculation> {
        return calculationDao.getAllCalculations().map { it.toCalculation() }
    }
}
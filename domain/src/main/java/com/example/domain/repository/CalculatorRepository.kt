package com.example.domain.repository

import com.example.domain.model.Calculation

interface CalculatorRepository {
    suspend fun saveCalculation(calculation: Calculation)
    suspend fun getCalculationHistory(): List<Calculation>
}
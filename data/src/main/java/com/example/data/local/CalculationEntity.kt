package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Calculation
import com.example.domain.model.Operation

@Entity(tableName = "calculations")
data class CalculationEntity(@PrimaryKey(autoGenerate = true)
                             val firstNumber : Double,
                             val secondNumber : Double,
                             val operation: String,
                             val result: Double)

fun Calculation.toEntity() = CalculationEntity(
    firstNumber = firstNumber,
    secondNumber = secondNumber,
    operation = operation.toString(),
    result = result
)

fun CalculationEntity.toCalculation() = Calculation(
    firstNumber = firstNumber,
    secondNumber = secondNumber,
    operation = Operation.valueOf(operation),
    result = result
)

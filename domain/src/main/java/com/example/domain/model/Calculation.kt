package com.example.domain.model

data class Calculation(val firstNumber: Double,
                       val secondNumber: Double,
                       val operation : Operation,
                       val result : Double)

enum class Operation {
    ADD , SUBTRACT , MULTIPLY , DIVIDE
}


package com.example.domain.usecase

import com.example.domain.model.Calculation
import com.example.domain.model.Operation
import com.example.domain.repository.CalculatorRepository
import javax.inject.Inject

// Hilt 로 의존성 주입 지시
class CalculateUseCase @Inject constructor(private val repository: CalculatorRepository) {
    // operator fun invoke : 클래스를 함수처럼 호출 가능하게 만듬
    suspend operator fun invoke ( firstNumber : Double,
                                 secondNumber : Double,
                                 operation: Operation ) : Calculation {

        // 로직
        val result = when (operation) {
            Operation.ADD -> firstNumber + secondNumber
            Operation.SUBTRACT -> firstNumber - secondNumber
            Operation.MULTIPLY -> firstNumber * secondNumber
            Operation.DIVIDE -> if(secondNumber != 0.0) firstNumber / secondNumber
                                else throw IllegalArgumentException("나눌 수 없습니다.")
        }

        val calculation = Calculation(firstNumber, secondNumber, operation, result)
        repository.saveCalculation(calculation)
        return calculation

    }
}
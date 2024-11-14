package com.example.presentation.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Operation
import com.example.domain.usecase.CalculateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculateUseCase: CalculateUseCase) : ViewModel(){
    private val _state = MutableStateFlow(CalculatorState())
    val state : StateFlow<CalculatorState> = _state

    fun calculate(firstNumber : Double , secondNumber: Double , operation: Operation) {
        viewModelScope.launch {
            try{
                val result = calculateUseCase(firstNumber, secondNumber, operation)
                _state.value = CalculatorState(
                    result = result.result,
                    error = null
                )
            }catch(e: Exception) {
                _state.value = CalculatorState(
                    result = null,
                    error = e.message
                )
            }
        }
    }




}

data class CalculatorState(
    val result: Double? = null,
    val error: String? = null
)
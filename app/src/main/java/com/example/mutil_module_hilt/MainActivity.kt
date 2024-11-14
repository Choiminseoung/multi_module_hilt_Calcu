package com.example.mutil_module_hilt

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Operation
import com.example.mutil_module_hilt.databinding.ActivityMainBinding
import com.example.presentation.calculator.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()
    private enum class step {   //첫번째 두번째 나누기 위한 enum
        FIRST , SECOND
    }
    private lateinit var binding : ActivityMainBinding
    private var nowStep : step = step.FIRST
    private var firstNumber = arrayListOf<Int>()     // 첫 번째 숫자 담아 둘 Array
    private var secondNumber = arrayListOf<Int>()    // 두 번째 숫자 담아 둘 Array

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListener()
        observeState()
    }

    private fun clickListener(){
        binding.apply {
            btn0.setOnClickListener{addNumber(0)}
            btn1.setOnClickListener{addNumber(1)}
            btn2.setOnClickListener{addNumber(2)}
            btn3.setOnClickListener{addNumber(3)}
            btn4.setOnClickListener{addNumber(4)}
            btn5.setOnClickListener{addNumber(5)}
            btn6.setOnClickListener{addNumber(6)}
            btn7.setOnClickListener{addNumber(7)}
            btn8.setOnClickListener{addNumber(8)}
            btn9.setOnClickListener{addNumber(9)}
            btnResult.setOnClickListener { viewModel.calculate(
                    arrayToInt(firstNumber).toDouble(),
                    arrayToInt(secondNumber).toDouble(),
                    Operation.DIVIDE)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                state -> binding.apply{
                    state.result?.let {
                        result ->
                            binding.textInputNum.text = ""
                            binding.textResult.text = result.toString()

                    }

                    // 에러 처리
                    state.error?.let {
                        error ->
                            binding.textInputNum.text = ""
                            binding.textNotify.text = error
                    }
                }
            }
        }
    }

    private fun addNumber(num : Int){
        when(nowStep) {
            step.FIRST -> {
                firstNumber.add(num)
                binding.textInputNum.text = arrayToInt(firstNumber).toString()
            }
            step.SECOND -> {
                secondNumber.add(num)
                binding.textInputNum.text = arrayToInt(secondNumber).toString()
            }
        }
    }

    private fun arrayToInt(array: ArrayList<Int>) : Int{
        var resultInt = 0
        try {
            resultInt = Integer.parseInt(array.toString())
        }catch (e : NumberFormatException){
            e.printStackTrace()
        }
        return resultInt
    }
}
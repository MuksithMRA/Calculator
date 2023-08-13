package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var strText:StringBuilder = StringBuilder();
    private var historyStrText:StringBuilder = StringBuilder();
    private lateinit var inputButtons:Array<Button>
    private val operators = listOf<String>("+","-","×","/")
    private var operator:Operator = Operator.NONE;
    private var isOperatorClicked = false;
        private var newNumberClicked = true;
    private var operand1:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeButtonComponents()
    }

    private fun initializeButtonComponents() {
        inputButtons = arrayOf(btn_plus_or_minus , btn_zero , btn_dot,btn_equal,btn_one,btn_two ,
            btn_three,btn_divide,btn_four,btn_five,btn_six,btn_multipication,btn_seven,btn_eight,
            btn_nine,btn_minus,btn_clear,btn_delete,btn_plus
        )
        for (button in inputButtons){button.setOnClickListener{inputButtonClicked(button)}}
    }

    private fun inputButtonClicked(button:Button){
        if(button.text == "CLEAR"){



        }else if(button.text == "DEL"){

        }else if(operators.contains(button.text)){
            if(newNumberClicked){
                if(button.text == "+") operator = Operator.ADD
                else if(button.text == "-")operator = Operator.SUB
                else if(button.text == "×")operator = Operator.MUL
                else if(button.text == "/") operator = Operator.DIV
                isOperatorClicked = true;
                calculation()
                showHistoryDisplayData(button.text.toString())
            }

        }else if(button.text == "="){

        }else if(button.text == "+/-"){

        }else{
            if(!((strText.equals("0") || strText.isEmpty()) && button.text.equals("0"))){
                newNumberClicked = true;
                showDisplayData(button.text.toString())
            }
        }
    }

    private fun showDisplayData( input:String){
        if(isOperatorClicked){
            strText.clear();
            isOperatorClicked=false;
            operator = Operator.NONE;
        }
        strText.append(input)
        txt_tvDisplay.text = strText
    }

    private fun showHistoryDisplayData( input:String){
            newNumberClicked = false;
            historyStrText.clear();
            strText.clear();
            showDisplayData(operand1.toString())
            historyStrText.append(operand1.toString())
            historyStrText.append(input)
            txt_tvDisplayHistory.text = historyStrText
    }

    private fun calculation() {
        try {
            if (operator == Operator.ADD) {
                operand1 += strText.toString().toInt()
            } else if (operator == Operator.DIV) {
                operand1 /= strText.toString().toInt()
            } else if (operator == Operator.MUL) {
                operand1 *= strText.toString().toInt()
            } else if (operator == Operator.SUB) {
                operand1 -= strText.toString().toInt()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

}

enum class Operator{ADD , SUB , MUL , DIV , NONE}


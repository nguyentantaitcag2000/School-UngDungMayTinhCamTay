package com.example.b1906342_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var display: TextView; // lateinit để khai báo ở trọng onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById<TextView>(R.id.textViewDisplay)
    }
    fun rawToScreen(view: View)
    {
        // Lấy giá trị hiện tại của TextView
        val currentText = display.text.toString()

        // Lấy giá trị của nút được nhấn
        val buttonText = (view as Button).text.toString()
        try{

            // Cập nhật giá trị của TextView
            display.text = currentText + buttonText;
        }
        catch (e: Exception){
            // Trường hợp lỗi có thể là do 2 biến trên là kiểu text không
            // ép kiểu được về kiểu số nên sẽ gán nó bằng 0
            display.text = "0";
        }

    }
    fun onOperator(view: View)
    {
        // Lấy giá trị hiện tại của TextView
        val currentText = display.text.toString()
        if(currentText.length==0)
            return;
        // Lấy giá trị của nút được nhấn
        val buttonText = (view as Button).text.toString()
        if(currentText.last() != '+' && currentText.last() != '-' && currentText.last() != '*' && currentText.last() != '/'  )
            rawToScreen(view)
    }
    fun onDelete(view: View)
    {
        // Lấy giá trị hiện tại của TextView
        val currentText = display.text.toString()
        val newText = currentText.substring(0,currentText.length - 1)
        display.text = newText
    }
    fun onDigit(view: View)
    {
        rawToScreen(view)
    }
    fun onDot(view:View){
        // Lấy giá trị hiện tại của TextView
        val currentText = display.text.toString()
        if(currentText.length==0)
            return;
        // Lấy giá trị của nút được nhấn
        val buttonText = (view as Button).text.toString()
        if(currentText.last() != '.'
            && currentText.last() != '+'
            && currentText.last() != '-'
            && currentText.last() != '*'
            && currentText.last() != '/'
            )
            rawToScreen(view)
    }
    fun onEqual(view: View) {
        try{
            // Lấy giá trị hiện tại của TextView
            val currentText = display.text.toString()
            println(currentText);
            // Tách số và dấu
            //currentText.split("[+\\-*/]".toRegex()) ở đây sẽ tách số ra riêng vào 1 mảng
            val numbers = currentText.split("[+\\-*/]".toRegex()).map { it.trim().toDouble() }
            // Tách các phép toán ra riêng 1 mảng
            val operators = currentText.filter { it in "+-*/" }
            println(numbers)
            println(operators)
            // Thực hiện phép tính
            var result = numbers[0]
            // Lặp đến khi hết các số đang có trong mảng numbers
            for (i in 1 until numbers.size) {
                when (operators[i-1]) {
                    '+' -> result += numbers[i]
                    '-' -> result -= numbers[i]
                    '*' -> result *= numbers[i]
                    '/' -> {
                        if (numbers[i] != 0.0) {
                            result /= numbers[i]
                        } else {
                            // Xử lý trường hợp chia cho 0
                            display.text = "Error"
                            return
                        }
                    }
                }
            }

            // Cập nhật kết quả lên TextView
            display.text = result.toString()
        }
        catch (e: Exception){
            // Trường hợp lỗi
            display.text = "ERROR";
        }

    }
    fun onClear(view: View){
        display.text = ""
    }

}
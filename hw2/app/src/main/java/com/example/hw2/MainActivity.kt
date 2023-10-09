import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var currentInput = ""
    private var currentOperator = ""
    private var result = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main.xml)
        editText = findViewById<EditText>(R.id.editText)

        // Set click listeners for number and operator buttons
        setButtonClickListener(R.id.button0)
        setButtonClickListener(R.id.button1)
        setButtonClickListener(R.id.button2)
        setButtonClickListener(R.id.button3)
        setButtonClickListener(R.id.button4)
        setButtonClickListener(R.id.button5)
        setButtonClickListener(R.id.button6)
        setButtonClickListener(R.id.button7)
        setButtonClickListener(R.id.button8)
        setButtonClickListener(R.id.button9)
        setButtonClickListener(R.id.buttonPlus)
        setButtonClickListener(R.id.buttonMinus)
        setButtonClickListener(R.id.buttonMultiply)
        setButtonClickListener(R.id.buttonDivide)
        setButtonClickListener(R.id.buttonDot)

        // Handle equals button
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        buttonEquals.setOnClickListener { calculate() }

        // Handle clear button
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener { clear() }
    }

    private fun setButtonClickListener(buttonId: Int) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener { onButtonClick(button) }
    }

    private fun onButtonClick(button: Button) {
        val buttonText = button.text.toString()
        currentInput += buttonText
        editText!!.setText(currentInput)
    }

    private fun calculate() {
        try {
            val input = currentInput.toDouble()
            if (currentOperator == "+") {
                result += input
            } else if (currentOperator == "-") {
                result -= input
            } else if (currentOperator == "*") {
                result *= input
            } else if (currentOperator == "/") {
                if (input == 0.0) {
                    throw ArithmeticException("Division by zero")
                }
                result /= input
            } else {
                result = input
            }
            currentInput = result.toString()
            editText!!.setText(currentInput)
        } catch (e: NumberFormatException) {
            // Handle invalid input
            editText!!.setText("Error")
        } catch (e: ArithmeticException) {
            // Handle division by zero
            editText!!.setText("Division by zero")
        }
    }

    private fun clear() {
        currentInput = ""
        currentOperator = ""
        result = 0.0
        editText!!.setText("")
    }
}
package com.bowen.interactiveappslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

var numCoffee = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val minusButton: Button = findViewById(R.id.minus_button)
        minusButton.setOnClickListener {
            updateQuantity(-1)
        }
        val plusButton: Button = findViewById(R.id.plus_button)
        plusButton.setOnClickListener {
            updateQuantity(1)
        }
        val orderButton: Button = findViewById(R.id.order_button)
        orderButton.setOnClickListener {
                submitOrder()
        }
    }


    fun updateQuantity(num: Int) {
        val newAmount = numCoffee + num
        if (newAmount < 1)
            Toast.makeText(this, R.string.lessThanOne, Toast.LENGTH_SHORT).show()
        else if (newAmount > 10)
            Toast.makeText(this, R.string.moreThanTen, Toast.LENGTH_SHORT).show()
        else {
            numCoffee = newAmount
            val number_coffee: TextView = findViewById(R.id.num_coffee_textView)
            number_coffee.text = numCoffee.toString() //caste int as string
        }
    }

    fun submitOrder(){
        var priceOneCoffee = 5
        val orderAmount: TextView = findViewById(R.id.total_amount_textView)
        val insertName: EditText = findViewById(R.id.enter_text)
        val name = insertName.text
        val hasWhippedCream: CheckBox = findViewById(R.id.whipped_cream_checkBox)
        val hasChocolate: CheckBox = findViewById(R.id.chocolate_checkBox)
        if ((hasWhippedCream).isChecked)
            priceOneCoffee++
        if (hasChocolate.isChecked)
            priceOneCoffee += 2
        val totalPrice = priceOneCoffee * numCoffee
        val orderSummary = "Thanks, $name!\n${if(numCoffee > 1) "$numCoffee coffees\n" else "1 coffee\n"}${if(hasChocolate.isChecked) "+ Chocolate\n" else ""}${if(hasWhippedCream.isChecked) "+ Whipped Cream\n" else ""}Total: $$totalPrice.00 "
        orderAmount.text = orderSummary
    }
}


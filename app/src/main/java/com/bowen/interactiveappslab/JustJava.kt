package com.bowen.interactiveappslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bowen.interactiveappslab.databinding.ActivityMainBinding
import org.w3c.dom.Text

var numCoffee = 0
lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        binding.minusButton.setOnClickListener {
            updateQuantity(-1)
        }
        binding.plusButton.setOnClickListener {
            updateQuantity(1)
        }
        binding.orderButton.setOnClickListener {
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
            binding.numCoffeeTextView.text = numCoffee.toString() //caste int as string
        }
    }

    fun submitOrder(){
        var priceOneCoffee = 5
        if ((binding.chocolateCheckBox).isChecked)
            priceOneCoffee++
        if (binding.chocolateCheckBox.isChecked)
            priceOneCoffee += 2
        val totalPrice = priceOneCoffee * numCoffee
        val orderSummary = "Thanks, ${binding.enterText.text}!\n${if(numCoffee > 1) "$numCoffee coffees\n" else "1 coffee\n"}${if(binding.chocolateCheckBox.isChecked) "+ Chocolate\n" else ""}${if(binding.whippedCreamCheckBox.isChecked) "+ Whipped Cream\n" else ""}Total: $$totalPrice.00 "
        binding.totalAmountTextView.text = orderSummary
    }
}


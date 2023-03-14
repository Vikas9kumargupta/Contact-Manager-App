package com.example.assignment2

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_contact.*


class ContactActivity : AppCompatActivity() {
    lateinit var dialog : Dialog
    companion object{
        const val KEY = "package com.example.assignment2.ContactActivity.KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        val myButton = findViewById<Button>(R.id.btnDetails)

        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.backgroudalert))

        val buttonGood = dialog.findViewById<Button>(R.id.btn2)
        val buttonFeedback = dialog.findViewById<Button>(R.id.btn1)

        buttonGood.setOnClickListener{
            val orderPlaced = etName.text.toString() + "PHONE NUMBER IS : " + etPhoneNumber.text.toString() + "USER ID IS "+
                    etUserId.text.toString()

            intent = Intent(this, ContactDetail::class.java)
            intent.putExtra(KEY, orderPlaced)
            startActivity(intent)
        }
        buttonFeedback.setOnClickListener {
            dialog.dismiss()
        }
        myButton.setOnClickListener {
            dialog.show()
        }

    }
}
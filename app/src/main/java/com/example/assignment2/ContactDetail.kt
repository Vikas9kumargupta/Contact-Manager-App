package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_contact_detail.*
import kotlinx.android.synthetic.main.custom_dialog.*

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactDetails = intent.getStringExtra(ContactActivity.KEY)
        tVOrder.text =  contactDetails.toString()
    }
}
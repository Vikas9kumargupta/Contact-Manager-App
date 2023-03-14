package com.example.assignment2

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.assignment2.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    lateinit var binding: ActivityMainBinding
    companion object{
        const val KEY1 = "com.example.assignment2.SignIn.mail"
        const val KEY2 = "com.example.assignment2.SignIn.name"
        const val KEY3 = "com.example.assignment2.SignIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val userName = findViewById<TextInputEditText>(R.id.uniqueId)
        signInButton.setOnClickListener{
            //take reference till node "users"
            val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this,"Please Enter user Name", Toast.LENGTH_SHORT).show()
            }
        }
        checkBox.setOnClickListener{
            if(checkBox.isChecked){
                Toast.makeText(this,"Agree to All terms and conditions",Toast.LENGTH_SHORT).show()
            }else{
                checkBox.buttonTintList = ColorStateList.valueOf(Color.RED)
                Toast.makeText(this,"Please check all the T&C",Toast.LENGTH_SHORT).show()
            }

        }
        val signUpText = findViewById<TextView>(R.id.tvSignUp)
        signUpText.setOnClickListener{
            val openSignUpActivity = Intent(this,SignUpActivity::class.java)
            startActivity(openSignUpActivity)
        }
    }
    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("userId").value
                val intentWelcome = Intent(this, ContactActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY3,userId.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this,"User does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed, Error in DataBase", Toast.LENGTH_SHORT).show()
        }
    }
}

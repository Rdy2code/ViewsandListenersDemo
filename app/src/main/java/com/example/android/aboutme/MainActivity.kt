package com.example.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.aboutme.databinding.ActivityMainBinding
import com.example.android.aboutme.model.MyName

class MainActivity : AppCompatActivity() {

    //region Class variables
    private lateinit var binding: ActivityMainBinding
    //endregion

    private val myName: MyName = MyName("Louis Wollenberger")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Create the binding object
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener {addNickName()}
        binding.nicknameText.setOnClickListener{updateNickname()}
    }

    private fun addNickName() {
        binding.apply{
            //Set the nickname text
            myName?.nickname = nicknameEdit.text.toString()
            //After the nickname is set, refresh the UI with new data
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            //Hide keyboard
            val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
        }
    }

    private fun updateNickname() {
        binding.apply{
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE
            //Show keyboard when the user taps on the textview
            nicknameEdit.requestFocus()
            val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.showSoftInput(nicknameEdit, 0)
        }
    }
}
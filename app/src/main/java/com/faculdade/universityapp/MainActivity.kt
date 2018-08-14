package com.faculdade.universityapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.faculdade.universityapp.pages.CadStudent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cadEstudante.setOnClickListener {
            val i = Intent(this, CadStudent::class.java)
            startActivity(i)
        }

    }
}

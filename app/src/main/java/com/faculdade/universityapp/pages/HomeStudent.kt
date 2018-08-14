package com.faculdade.universityapp.pages

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.faculdade.universityapp.R
import kotlinx.android.synthetic.main.activity_home_student.*


class HomeStudent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)

        var nome = intent.getStringExtra("Nome")
        var nasc = intent.getStringExtra("Nascimento")
        var ingresso = intent.getStringExtra("Ingresso").toInt()
        var cursado = 2018.minus(ingresso)*2

        nomeStudent.text = nome
        nascimentoStudent.text = nasc
        ingressoStudent.text = ingresso.toString()
        cursadoStudent.text = cursado.toString()

        cadDisciplina_button.setOnClickListener{
            val i = Intent(applicationContext,Disciplinas::class.java)
            startActivity(i)
        }

    }
}

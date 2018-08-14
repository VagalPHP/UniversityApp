package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.content.Intent

import android.view.View
import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.R.layout.activity_disciplinas
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper

import kotlinx.android.synthetic.main.activity_disciplinas.*
import kotlinx.android.synthetic.main.disciplinas.*

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper : DisciplinaBDHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)
        DisciplinasDBHelper = DisciplinaBDHelper(this)

        btn_AddDisc.setOnClickListener{
            fun addDisciplinas(v: View){
                setContentView(R.layout.activity_disciplinas)
                var sigla = this.Sigla.text.toString()
                var nome = this.Nome_Disc.text.toString()
                var semAno = this.SemAno.text.toString().toInt()
                var nota = this.Nota.text.toString().toDouble()

                var result = DisciplinasDBHelper.inserirDisciplina(Disciplina(sigla, nome, semAno, nota))        //clear all edittext s
                if (result){
                    setContentView(R.layout.disciplinas)
                }else{
                    Toast.makeText(this, "Erro ao inserir disciplina",Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}

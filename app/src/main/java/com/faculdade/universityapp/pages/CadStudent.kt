package com.faculdade.universityapp.pages

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.faculdade.universityapp.R
import kotlinx.android.synthetic.main.activity_home_student.view.*
import kotlinx.android.synthetic.main.estudante.*
import kotlinx.android.synthetic.main.estudante.view.*
import com.faculdade.universityapp.bd.*


class CadStudent : AppCompatActivity(){


    val REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estudante)
        btn_TakePic.setOnClickListener {
            val CameraRequest = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (CameraRequest.resolveActivity(packageManager) != null) {
                startActivityForResult(CameraRequest, REQUEST_CODE)
            }
        }
        var validado = 0
        var nome = nomeCS.text
        var nasc = nascimentoCS.text
        var anoIngresso = ingressoCS.text


        if (nome.isBlank()){
            validado++
        }else {
            validado--
        }
        if (nasc.isBlank()){
            validado++
        }else{
            validado--
        }

        if (anoIngresso.isBlank()){
            validado++
        }else{
            validado--
        }

            cadNewStudent.setOnClickListener {
                if (validado == 3){
                    val i = Intent(applicationContext, HomeStudent::class.java)
                    i.putExtra("Nome",nome.toString())
                    i.putExtra("Nascimento",nasc.toString())
                    i.putExtra("Ingresso",anoIngresso.toString())
                    startActivity(i)
                }else{
                    Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode.equals(REQUEST_CODE)){
            if (resultCode == Activity.RESULT_OK && data != null){
                Foto.setImageBitmap(data.extras.get("data") as Bitmap)
            } else {
                Toast.makeText(this,"Não foi possível salvar a imagem",Toast.LENGTH_SHORT).show()
            }
        }
}
}

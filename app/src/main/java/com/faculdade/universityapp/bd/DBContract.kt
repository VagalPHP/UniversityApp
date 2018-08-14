package com.faculdade.universityapp.bd

import android.provider.BaseColumns

object DBContract {
    class Disciplinas_Ent : BaseColumns {
        companion object {
            val TABLE_NAME = "Disciplinas"
            val COL_DISC_ID = "Id"
            val COL_SIGLA = "Sigla"
            val COL_NOME = "Nome"
            val COL_SEM = "Semestre/Ano"
            val COL_NOTA = "Nota"
        }
    }
}
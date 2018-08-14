package com.faculdade.universityapp.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DisciplinaBD_Manager {
    private val dbName = "DisciplinasCursadas"
    private val dbTable = "DiscCursadas_tbl"
    private val colId = "Id"
    private val colNomeDisc = "Disciplina"
    private val colSigla = "Sigla"
    private val colNota = "Nota"
    private val colSemAno = "Semestre/Ano"
    private val dbVersion = 1

    private val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+colId+" INTEGER PRIMARY KEY"+ colSigla + "TEXT" + colNomeDisc + "TEXT" + colSemAno + "TEXT" + colNota + "DOUBLE)"
    private var db : SQLiteDatabase? = null

    constructor(context: Context){
        var dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }

    fun insert(values : ContentValues) : Long {
        val ID = db!!.insert(dbTable, "", values)
        return ID
    }

    fun selectAll() : Cursor {
        return db!!.rawQuery("SELECT * FROM ${dbTable}", null)
    }

    fun delete(selecao : String, seleccaoArgs : Array<String>) : Int{
        var cont = db!!.delete(dbTable,selecao,seleccaoArgs)
        return cont
    }

    fun update(values: ContentValues, selection: String, selectionargs: Array<String>): Int {
        val count = db!!.update(dbTable, values, selection, selectionargs)
        return count
    }

    inner class DatabaseHelper : SQLiteOpenHelper {

        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE)
            Toast.makeText(this.context, " Base de dados criada ", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS " + dbTable)
        }
    }

}

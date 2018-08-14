package com.faculdade.universityapp.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DisciplinaBDHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun inserirDisciplina(disc : Disciplina): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.Disciplinas_Ent.COL_SIGLA, disc.sigla)
        values.put(DBContract.Disciplinas_Ent.COL_NOME, disc.nome)
        values.put(DBContract.Disciplinas_Ent.COL_SEM, disc.semAno)
        values.put(DBContract.Disciplinas_Ent.COL_NOTA, disc.nota)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.Disciplinas_Ent.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun apagarDisciplina(disc_id: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.Disciplinas_Ent.COL_DISC_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(disc_id)
        // Issue SQL statement.
        db.delete(DBContract.Disciplinas_Ent.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun lerDisciplina(Id : Int): ArrayList<Disciplina> {
        val disciplinas = ArrayList<Disciplina>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.Disciplinas_Ent.TABLE_NAME + " WHERE " + DBContract.Disciplinas_Ent.COL_DISC_ID + "='" + Id + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var disc_id: Int
        var sigla: String
        var nome: String
        var semAno: Int
        var nota: Double
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                disc_id = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_DISC_ID))
                sigla = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SIGLA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOME))
                semAno = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SEM))
                nota = cursor.getDouble(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOTA))

                disciplinas.add(disc_id,Disciplina(sigla, nome, semAno, nota))
                cursor.moveToNext()
            }
        }
        return disciplinas
    }

    fun lerTodasDisciplinas(): ArrayList<Disciplina> {
        val disciplinas = ArrayList<Disciplina>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.Disciplinas_Ent.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var disc_id: Int
        var sigla: String
        var nome: String
        var semAno: Int
        var nota: Double
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                disc_id = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_DISC_ID))
                sigla = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SIGLA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOME))
                semAno = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SEM))
                nota = cursor.getDouble(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOTA))

                disciplinas.add(disc_id,Disciplina(sigla, nome, semAno, nota))
                cursor.moveToNext()
            }
        }
        return disciplinas
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Disciplina.bd"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.Disciplinas_Ent.TABLE_NAME + " (" +
                        DBContract.Disciplinas_Ent.COL_DISC_ID + " INT PRIMARY KEY," +
                        DBContract.Disciplinas_Ent.COL_SIGLA + " TEXT," +
                        DBContract.Disciplinas_Ent.COL_NOME + " TEXT," +
                        DBContract.Disciplinas_Ent.COL_SEM + " INT," +
                        DBContract.Disciplinas_Ent.COL_NOTA + " DOUBLE)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.Disciplinas_Ent.TABLE_NAME
    }

}
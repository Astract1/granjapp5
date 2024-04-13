package com.example.granjapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "granjapp.db";
    private static final int DATABASE_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USUARIOS_TABLE = "CREATE TABLE " +
                UsuariosContract.UsuarioEntry.TABLE_NAME_USUARIOS + " (" +
                UsuariosContract.UsuarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsuariosContract.UsuarioEntry.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                UsuariosContract.UsuarioEntry.COLUMN_APELLIDO + " TEXT NOT NULL, " +
                UsuariosContract.UsuarioEntry.COLUMN_CORREO + " TEXT NOT NULL, " +
                UsuariosContract.UsuarioEntry.COLUMN_CONTRASENA + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_CAMPESINOS_TABLE = "CREATE TABLE " +
                UsuariosContract.CampesinoEntry.TABLE_NAME_CAMPESINOS + " (" +
                UsuariosContract.CampesinoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsuariosContract.CampesinoEntry.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                UsuariosContract.CampesinoEntry.COLUMN_APELLIDO + " TEXT NOT NULL, " +
                UsuariosContract.CampesinoEntry.COLUMN_CORREO + " TEXT NOT NULL, " +
                UsuariosContract.CampesinoEntry.COLUMN_CONTRASENA + " TEXT NOT NULL, " +
                UsuariosContract.CampesinoEntry.COLUMN_NOMBRE_GRANJA + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_SOCIOS_TABLE = "CREATE TABLE " +
                UsuariosContract.SocioEntry.TABLE_NAME_SOCIOS + " (" +
                UsuariosContract.SocioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsuariosContract.SocioEntry.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                UsuariosContract.SocioEntry.COLUMN_APELLIDO + " TEXT NOT NULL, " +
                UsuariosContract.SocioEntry.COLUMN_CORREO + " TEXT NOT NULL, " +
                UsuariosContract.SocioEntry.COLUMN_CONTRASENA + " TEXT NOT NULL, " +
                UsuariosContract.SocioEntry.COLUMN_NOMBRE_ORGANIZACION + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_USUARIOS_TABLE);
        db.execSQL(SQL_CREATE_CAMPESINOS_TABLE);
        db.execSQL(SQL_CREATE_SOCIOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsuariosContract.UsuarioEntry.TABLE_NAME_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + UsuariosContract.CampesinoEntry.TABLE_NAME_CAMPESINOS);
        db.execSQL("DROP TABLE IF EXISTS " + UsuariosContract.SocioEntry.TABLE_NAME_SOCIOS);
        onCreate(db);
    }

    public long insertarUsuario(String nombre, String apellido, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsuariosContract.UsuarioEntry.COLUMN_NOMBRE, nombre);
        values.put(UsuariosContract.UsuarioEntry.COLUMN_APELLIDO, apellido);
        values.put(UsuariosContract.UsuarioEntry.COLUMN_CORREO, correo);
        values.put(UsuariosContract.UsuarioEntry.COLUMN_CONTRASENA, contrasena);
        return db.insert(UsuariosContract.UsuarioEntry.TABLE_NAME_USUARIOS, null, values);
    }

    public long insertarCampesino(String nombre, String apellido, String correo, String contrasena, String nombreGranja) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsuariosContract.CampesinoEntry.COLUMN_NOMBRE, nombre);
        values.put(UsuariosContract.CampesinoEntry.COLUMN_APELLIDO, apellido);
        values.put(UsuariosContract.CampesinoEntry.COLUMN_CORREO, correo);
        values.put(UsuariosContract.CampesinoEntry.COLUMN_CONTRASENA, contrasena);
        values.put(UsuariosContract.CampesinoEntry.COLUMN_NOMBRE_GRANJA, nombreGranja);
        return db.insert(UsuariosContract.CampesinoEntry.TABLE_NAME_CAMPESINOS, null, values);
    }

    public long insertarSocio(String nombre, String apellido, String correo, String contrasena, String nombreOrganizacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsuariosContract.SocioEntry.COLUMN_NOMBRE, nombre);
        values.put(UsuariosContract.SocioEntry.COLUMN_APELLIDO, apellido);
        values.put(UsuariosContract.SocioEntry.COLUMN_CORREO, correo);
        values.put(UsuariosContract.SocioEntry.COLUMN_CONTRASENA, contrasena);
        values.put(UsuariosContract.SocioEntry.COLUMN_NOMBRE_ORGANIZACION, nombreOrganizacion);
        return db.insert(UsuariosContract.SocioEntry.TABLE_NAME_SOCIOS, null, values);
    }

    public boolean validarCredencialesCampesino(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                UsuariosContract.CampesinoEntry.TABLE_NAME_CAMPESINOS,
                null,
                UsuariosContract.CampesinoEntry.COLUMN_CORREO + " = ? AND " +
                        UsuariosContract.CampesinoEntry.COLUMN_CONTRASENA + " = ?",
                new String[]{correo, contraseña},
                null,
                null,
                null
        );
        boolean credencialesValidas = cursor.getCount() > 0;
        cursor.close();
        return credencialesValidas;
    }

    public boolean validarCredencialesComprador(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                UsuariosContract.UsuarioEntry.TABLE_NAME_USUARIOS,
                null,
                UsuariosContract.UsuarioEntry.COLUMN_CORREO + " = ? AND " +
                        UsuariosContract.UsuarioEntry.COLUMN_CONTRASENA + " = ?",
                new String[]{correo, contraseña},
                null,
                null,
                null
        );
        boolean credencialesValidas = cursor.getCount() > 0;
        cursor.close();
        return credencialesValidas;
    }

    public boolean validarCredencialesSocio(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                UsuariosContract.SocioEntry.TABLE_NAME_SOCIOS,
                null,
                UsuariosContract.SocioEntry.COLUMN_CORREO + " = ? AND " +
                        UsuariosContract.SocioEntry.COLUMN_CONTRASENA + " = ?",
                new String[]{correo, contraseña},
                null,
                null,
                null
        );
        boolean credencialesValidas = cursor.getCount() > 0;
        cursor.close();
        return credencialesValidas;
    }

    public boolean existeCorreo(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                UsuariosContract.UsuarioEntry.TABLE_NAME_USUARIOS,
                null,
                UsuariosContract.UsuarioEntry.COLUMN_CORREO + " = ?",
                new String[]{correo},
                null,
                null,
                null
        );
        boolean existeCorreo = cursor.getCount() > 0;
        cursor.close();
        return existeCorreo;
    }


}

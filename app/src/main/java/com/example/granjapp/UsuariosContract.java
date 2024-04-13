    package com.example.granjapp;

    import android.provider.BaseColumns;

    public final class UsuariosContract {
        private UsuariosContract() {}

        public static class UsuarioEntry implements BaseColumns {
            public static final String TABLE_NAME_USUARIOS = "usuarios";
            public static final String COLUMN_NOMBRE = "nombre";
            public static final String COLUMN_APELLIDO = "apellido";
            public static final String COLUMN_CORREO = "correo";
            public static final String COLUMN_CONTRASENA = "contrasena";
        }

        public static class CampesinoEntry implements BaseColumns {
            public static final String TABLE_NAME_CAMPESINOS = "campesinos";
            public static final String COLUMN_NOMBRE = "nombre";
            public static final String COLUMN_APELLIDO = "apellido";
            public static final String COLUMN_CORREO = "correo";
            public static final String COLUMN_CONTRASENA = "contrasena";
            public static final String COLUMN_NOMBRE_GRANJA = "nombre_granja";
        }

        public static class SocioEntry implements BaseColumns {
            public static final String TABLE_NAME_SOCIOS = "socios";
            public static final String COLUMN_NOMBRE = "nombre";
            public static final String COLUMN_APELLIDO = "apellido";
            public static final String COLUMN_CORREO = "correo";
            public static final String COLUMN_CONTRASENA = "contrasena";
            public static final String COLUMN_NOMBRE_ORGANIZACION = "nombre_organizacion";
        }
    }

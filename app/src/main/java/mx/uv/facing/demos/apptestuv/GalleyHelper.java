package mx.uv.facing.demos.apptestuv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class GalleyHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gallery.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contactos";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_TITLE = "titulo";

    public static final String SQL_CREATE_TABLE_GALLERY =
            "create table " + TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_TITLE + " text not null)";


    public static final String SQL_DELETE_TABLE_GALLERY = "DELETE FROM " + TABLE_NAME;

    public static final String SQL_INSERT_DEFAULT_VALUES = "insert into " + TABLE_NAME +
            " values (null, \"Marisol Pérez\", \"Ing. en Electrónica\"), " +
            "(null, \"Lourdes ´Rodríguez\", \"Lic. en Pedagogía\"), " +
            "(null, \"Víctor Jiménez\", \"Ing. en Electrónica\"), " +
            "(null, \"José Manuel Cartas\", \"Mtro. en Redes y Telecomunicaciones\"), " +
            "(null, \"Diego Basurto\", \"Ing. en Electrónica\"), " +
            "(null, \"Lorely González\", \"Lic. Informática\"), " +
            "(null, \"Arturo López\", \"Mtro. en Tecnologías de Información\"), " +
            "(null, \"Rufino Hinojosa\", \"Mtro. en Redes y Telecomunicaciones\"), " +
            "(null, \"José Carlos Nieto\", \"Ing. en Sistemas\")";


    public GalleyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL (SQL_CREATE_TABLE_GALLERY);
        sqLiteDatabase.execSQL (SQL_INSERT_DEFAULT_VALUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL (SQL_DELETE_TABLE_GALLERY);
        onCreate (sqLiteDatabase);
    }
}

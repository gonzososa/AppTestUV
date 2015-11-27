package mx.uv.facing.demos.apptestuv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class GalleryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "gallery.db";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "settings";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_REMEMBER_USER = "rememberUser";
    public static final String COLUMN_USE_LOCATION = "useLocation";
    public static final String COLUMN_IMAGE_QUALITY = "imageQuality";

    public static final String SQL_CREATE_TABLE =
            "create table " + TABLE_NAME +
            " (" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_REMEMBER_USER + " integer not null, " +
            COLUMN_USE_LOCATION + " integer not null, " + COLUMN_IMAGE_QUALITY + " text not null)";

    public static final String SQL_DELETE_TABLE = "DELETE FROM " + TABLE_NAME;

    public static final String SQL_INSERT_VALUES = "insert into " + TABLE_NAME + " values (null, 1, 1, \"ALTA\")";

    public GalleryDBHelper(Context context) {
        super (context, DATABASE, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("UV", SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL (SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL (SQL_INSERT_VALUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL (SQL_CREATE_TABLE);
        onCreate (sqLiteDatabase);
    }
}

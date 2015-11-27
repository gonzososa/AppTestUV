package mx.uv.facing.demos.apptestuv;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate (R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GalleryDBHelper galleryDBHelper = new GalleryDBHelper (getActivity().getBaseContext());
        final SQLiteDatabase database = galleryDBHelper.getReadableDatabase ();

        //Cursor c = database.query (GalleryDBHelper.TABLE_NAME,
        //        null, null, null, null, null, null);

        Cursor c = database.rawQuery ("SELECT * FROM settings", null);
        //String [] whereArgs = new String [] {"1"};
        //Cursor c = database.rawQuery ("DELETE FROM settings WHERE _id=?", whereArgs);

        c.moveToNext ();

        final int id = c.getInt (0);
        int rememberUser = c.getInt (1);
        int useLocation = c.getInt (2);
        String imageQuality = c.getString (3);

        final CheckBox c1 = (CheckBox) getActivity().findViewById (R.id.chkRememberUser);
        final CheckBox c2 = (CheckBox) getActivity().findViewById (R.id.chkUseLocation);
        final EditText e1 = (EditText) getActivity().findViewById (R.id.spQuality);

        if (rememberUser == 1)
            c1.setChecked (true);

        if (useLocation == 1)
            c2.setChecked (true);

        e1.setText (imageQuality);

        /*sharedPreferences = getActivity().getSharedPreferences("UV", Context.MODE_PRIVATE);
        int rememberUser  = sharedPreferences.getInt ("rememberUser", 0);
        int useLocation  = sharedPreferences.getInt ("useLocation", 0);
        String quality = sharedPreferences.getString ("quality", "");

        if (rememberUser == 1)
            c1.setChecked (true);

        if (useLocation == 1)
            c2.setChecked (true);

        e1.setText (quality);*/

        Button btnSaveSettings = (Button) getActivity().findViewById (R.id.btnSaveSettings);
        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SharedPreferences.Editor editor = sharedPreferences.edit();

                int rememberUser1 = c1.isChecked() ? 1 : 0;
                int useLocation1 = c2.isChecked() ? 1 : 0;
                String q = e1.getText ().toString ();

                editor.putInt ("rememberUser", rememberUser1);
                editor.putInt ("useLocation", useLocation1);
                editor.putString("quality", q);
                editor.apply ();*/

                ContentValues contentValues = new ContentValues ();
                contentValues.put ("rememberUser", c1.isChecked() ? 1 : 0);
                contentValues.put ("useLocation", c2.isChecked() ? 1 : 0);
                contentValues.put ("imageQuality", e1.getText().toString ());

                String where = "_id=?";

                String [] whereArgs = new String [] {String.valueOf (id)};

                database.update (GalleryDBHelper.TABLE_NAME, contentValues, where, whereArgs);

                Toast.makeText (getActivity().getBaseContext (), "Información almacenada!", Toast.LENGTH_LONG).show ();
            }
        });
    }
}

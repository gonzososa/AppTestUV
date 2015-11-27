package mx.uv.facing.demos.apptestuv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GalleryAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView img;

    public GalleryAsyncTask (ImageView img) {
        this.img = img;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap =null;
        String uri = urls[0];

        try {
            URL url = new URL (uri);
            URLConnection urlConnection = url.openConnection ();
            InputStream inputStream = urlConnection.getInputStream ();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException ioex) {
            Log.i("UVDEMOAPP", ioex.getMessage());
        }

        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        this.img.setImageBitmap (bmp);

        super.onPostExecute(bmp);
    }
}

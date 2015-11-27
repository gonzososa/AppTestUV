package mx.uv.facing.demos.apptestuv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Observable;

import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ArrayList<String> datos;
    Context context;

    public GalleryAdapter (Context context, ArrayList<String> datos) {
        this.datos= datos;
        this.context = context;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.setImgPhoto (datos.get (position));
    }

    @Override
    public int getItemCount() {
        return this.datos.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
       public ImageView imgPhoto;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById (R.id.imgPhoto);
        }

        public void setImgPhoto (String url) {
            /*Picasso.with (context)
                    .load (url)
                    .into (imgPhoto);*/


            GalleryAsyncTask g = new GalleryAsyncTask (imgPhoto);
            g.execute (url);


            /*downloadImage(url).observeOn(AndroidSchedulers.mainThread ()).subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onNext(Bitmap bitmap) {
                            imgPhoto.setImageBitmap(bitmap);
                        }
                    });*/

        }

//        private rx.Observable<Bitmap> downloadImage (final String uri) {
//            return rx.Observable.create(new rx.Observable.OnSubscribe<Bitmap>() {
//                @Override
//                public void call(Subscriber<? super Bitmap> subscriber) {
//                    Bitmap bitmap = null;
//
//                    try {
//                        URL url = new URL (uri);
//                        URLConnection urlConnection = url.openConnection ();
//                        InputStream inputStream = urlConnection.getInputStream();
//                        bitmap = BitmapFactory.decodeStream (inputStream);
//                        inputStream.close();
//                    } catch (IOException ioex) {
//                        Log.i("UV", ioex.getMessage());
//                    }
//
//                    subscriber.onNext(bitmap);
//                }
//            });
//        }
    }
}

package mx.uv.facing.demos.apptestuv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> datos = new ArrayList<>();
        datos.add ("https://c2.staticflickr.com/6/5677/22774348265_f1c48e38d1_b.jpg");
        /*datos.add ("https://c1.staticflickr.com/1/782/22777363712_c4516525a4_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/710/22795823751_2bf18a5b5f_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5644/22577330970_1f137ebd30_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5675/22583928510_c9fb4b9757_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/704/22370407737_dd86935ac3_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/695/22150009743_f7f94dde86_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5818/22165570454_613fef7f30_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/725/22761893806_787d8e7e3f_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/575/22760466446_d3fc8e60ca_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5719/22149989643_5bccaccbc5_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/757/22792566551_5d29c88cd5_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5759/22152616354_966f8df53e_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5674/22576088730_1be5149f98_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/614/22371403867_43abaaa20a_b.jpg");
        datos.add ("https://c1.staticflickr.com/1/729/22783664585_ddcb75eb65_b.jpg");
        datos.add ("https://c2.staticflickr.com/6/5670/22577019310_e2d8bf894b_b.jpg");

        /*datos.add ("UNO");
        datos.add ("DOS");
        datos.add ("TRES");
        datos.add ("CUATRO");
        datos.add ("CINCO");
        datos.add ("SEIS");
        datos.add ("SIETE");
        datos.add ("OCHO");
        datos.add ("NUEVE");
        datos.add ("DIEZ");
        datos.add ("ONCE");
        datos.add ("DOCE");
        datos.add ("TRECE");
        datos.add ("CATORCE");
        datos.add ("QUINCE");
        datos.add ("DIECISEIS");*/

        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById (R.id.recViewLista);
        GalleryAdapter adapter = new GalleryAdapter (getActivity().getBaseContext (), datos);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));
        recyclerView.setAdapter (adapter);
    }
}


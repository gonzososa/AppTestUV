package mx.uv.facing.demos.apptestuv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");
        datos.add ("UNO");

        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById (R.id.recViewLista);
        GalleryAdapter adapter = new GalleryAdapter (datos);
        recyclerView.setLayoutManager (new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}


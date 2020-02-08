package com.project.ateam.a_team321.bottomNavigation;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.ateam.a_team321.model.jenis.Jenis;
import com.project.ateam.a_team321.model.jenis.JenisAdapter;
import com.project.ateam.a_team321.model.jenis.JenisData;


import java.util.ArrayList;


import com.project.ateam.a_team321.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Jenis> list = new ArrayList<>();


    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_jenis);
        recyclerView.setHasFixedSize(true);

        list.addAll(JenisData.getListData());
        showRecyclerJenis();


    }

    private void showRecyclerJenis(){
        JenisAdapter jenisAdapter = new JenisAdapter(list);
        recyclerView.setAdapter(jenisAdapter);
    }

}

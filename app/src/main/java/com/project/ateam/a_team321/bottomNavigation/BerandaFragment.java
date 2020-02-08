package com.project.ateam.a_team321.bottomNavigation;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.ateam.a_team321.DetailPaketActivity;
import com.project.ateam.a_team321.daftarAgendesa.ModelPaketWisata;
import com.project.ateam.a_team321.model.jenis.Jenis;
import com.project.ateam.a_team321.model.jenis.JenisAdapter;
import com.project.ateam.a_team321.model.jenis.JenisData;


import java.util.ArrayList;


import com.project.ateam.a_team321.R;
import com.project.ateam.a_team321.recyclerRekomendasi.rekomendasiAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView123;
    private ArrayList<Jenis> list = new ArrayList<>();
    private static final String TAG = "ListFOod";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    private rekomendasiAdapter adapter;

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

        recyclerView123 = view.findViewById(R.id.rv_jenis);
        recyclerView123.setHasFixedSize(true);

        list.addAll(JenisData.getListData());
        showRecyclerJenis();

        setUpRecyclerView(view);


    }

    private void showRecyclerJenis(){
        JenisAdapter jenisAdapter = new JenisAdapter(list);
        recyclerView123.setAdapter(jenisAdapter);
    }

    private void setUpRecyclerView(View view) {
        Query query = db.collection("paket_wisata").orderBy("nama_desa").limit(10);

        FirestoreRecyclerOptions<ModelPaketWisata> options = new FirestoreRecyclerOptions.Builder<ModelPaketWisata>()
                .setQuery(query, ModelPaketWisata.class)
                .build();

        adapter = new rekomendasiAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.rv_Rekomendasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new rekomendasiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String namaDesa = documentSnapshot.getId();
                Log.d("SSSSSSSSSSSSSS", "onItemClick: "+namaDesa);
                Intent intent = new Intent(getContext(), DetailPaketActivity.class);
                //intent.putExtra("model", model);
                intent.putExtra("jangkrik", namaDesa);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
      adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
       adapter.stopListening();
    }
}


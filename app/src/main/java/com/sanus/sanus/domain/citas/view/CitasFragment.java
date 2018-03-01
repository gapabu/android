package com.sanus.sanus.domain.citas.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.citas.adapter.CitasAdapter;
import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CitasFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Citas> busquedaDoctors;
    private CitasAdapter adapter;
    private FirebaseFirestore mFirestore;

    public static String IDENTIFIER = "CITAS_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_citas, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        mFirestore = FirebaseFirestore.getInstance();

        initializedData();
        adapter = new CitasAdapter(getActivity().getApplicationContext(), busquedaDoctors);
        recyclerView.setAdapter(adapter);

    }

    private void initializedData() {
        busquedaDoctors = new ArrayList<>();

        mFirestore.collection("citas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        String doctor = doc.getDocument().getString("doctor");
                        String hospital = doc.getDocument().getString("hospital");
                        String fecha = doc.getDocument().getString("fecha");
                        String hora = doc.getDocument().getString("hora");
                        //String usuario = doc.getDocument().getString("usuario");

                        busquedaDoctors.add(new Citas(doctor,hospital, fecha, hora));

                        adapter.notifyDataSetChanged();
                        //https://www.youtube.com/watch?v=y3exATaC0kA
                    }
                }
            }
        });
    }

}
package com.sanus.sanus.domain.search.interactor;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.domain.search.presenter.SearchPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchInteractorImpl implements SearchInteractor{
    private SearchPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private List<BusquedaDoctor> busquedaDoctors =  new ArrayList<>();
    private List<BusquedaDoctor> listAuxiliar = new ArrayList<>();

    public SearchInteractorImpl(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(final QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for(final DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        final String user_id = doc.getDocument().getId();
                        final String especialidad = doc.getDocument().getString("especialidad");

                        mFirestore.collection("usuarios").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                final String image = documentSnapshot.getString("avatar");
                                String usuario = nombre + " " +apellido;
                                busquedaDoctors.add(new BusquedaDoctor(usuario, especialidad, image, user_id));
                                presenter.setDataAdapter(busquedaDoctors);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void buscador(String texto) {
        listAuxiliar.clear();
        if(texto.isEmpty()){
            presenter.setDataAdapter(busquedaDoctors);
            return;
        }
        for (int i = 0; i < busquedaDoctors.size(); i++) {
            if(busquedaDoctors.get(i).getEspecialidad().toLowerCase().contains(texto.toLowerCase())){
                listAuxiliar.add(new BusquedaDoctor(busquedaDoctors.get(i).getNombre(),busquedaDoctors.get(i).getEspecialidad(),
                        busquedaDoctors.get(i).getAvatar(),busquedaDoctors.get(i).getId() ));
            }
        }
        presenter.setDataAdapter(listAuxiliar);
    }

    @Override
    public void showImage(String idImage, final Context context, final CircleImageView image) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri.toString()).placeholder(R.drawable.user).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Error al traer foto", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

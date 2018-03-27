package com.sanus.sanus.domain.doctor_module.all_comments.interactor;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.all_comments.data.AllCommentsDoctor;
import com.sanus.sanus.domain.doctor_module.all_comments.presenter.AllCommentsPresenter;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCommentsInteractorImpl implements AllCommentsInteractor {
    private final String TAG = this.getClass().getSimpleName();
    private AllCommentsPresenter presenter;

    private List<AllCommentsDoctor> commentsDoctorList = new ArrayList<>();

    public AllCommentsInteractorImpl(AllCommentsPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewComents(String idDoc) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("comentarios").whereEqualTo("doctor", idDoc).orderBy("hora", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<String> mensages = new ArrayList<>();
                mensages.clear();
                commentsDoctorList.clear();

                for (DocumentSnapshot doc : value) {

                    String dataMensage = String.valueOf(doc.getData());
                    mensages.add(dataMensage);

                    String usuario1 = doc.getString("usuario");
                    final String fecha = doc.getString("fecha");
                    final String comentario = doc.getString("comentario");
                    final String calificacion1 = doc.getString("calificacion");

                    mFirestore.collection("usuarios").document(usuario1).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.w(TAG, "Listen failed.", e);
                                return;
                            }
                            if (snapshot != null && snapshot.exists()) {
                                Log.d(TAG, "Current data: " + snapshot.getData());
                                String nombre = snapshot.getString("nombre");
                                String apellido = snapshot.getString("apellido");
                                final String image = snapshot.getString("avatar");
                                String usuario = nombre + " " + apellido;
                                commentsDoctorList.add(new AllCommentsDoctor(usuario, comentario, fecha, calificacion1, image));
                                presenter.setDataAdapter(commentsDoctorList);
                            } else {
                                Log.d(TAG, "Current data: null");
                            }
                        }
                    });
                }
                Log.d(TAG, "Current data: " + mensages);
            }
        });

    }

    @Override
    public void showImage(String idImage, final Context context, final CircleImageView image) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                GlideApp.with(context).load(uri.toString()).placeholder(R.drawable.user).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d(TAG, "No hay conexion");
            }
        });
    }
}

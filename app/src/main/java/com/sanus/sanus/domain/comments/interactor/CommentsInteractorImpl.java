package com.sanus.sanus.domain.comments.interactor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.data.repository.firebase.entity.user.CommentEntity;
import com.sanus.sanus.data.repository.firebase.entity.user.DoctorEntity;
import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;
import com.sanus.sanus.utils.glide.GlideApp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsInteractorImpl implements CommentsInteractor {
    private final String TAG = this.getClass().getSimpleName();
    private CommentsPresenter presenter;
    private String idUser;
    private CommentEntity commentEntity = new CommentEntity();
    private DoctorEntity doctorEntity = new DoctorEntity();
    private String hour;
    private String date;

    private List<CommentsDoctor> commentsDoctorList = new ArrayList<>();

    public CommentsInteractorImpl(CommentsPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewComents(String idDoc) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("comentarios").whereEqualTo("doctor", idDoc).orderBy("hora", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {Log.w(TAG, "Listen failed.", e);return;}

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
                                Log.d(TAG, "Data: " + snapshot.getData());
                                String nombre = snapshot.getString("nombre");
                                String apellido = snapshot.getString("apellido");
                                final String image = snapshot.getString("avatar");
                                String usuario = nombre.concat(" " + apellido);
                                commentsDoctorList.add(new CommentsDoctor(usuario, comentario, fecha, calificacion1, image));
                                presenter.setDataAdapter(commentsDoctorList);
                            } else {
                                Log.d(TAG, "Data: null");
                            }
                        }
                    });
                }
                Log.d(TAG, "Data: " + mensages);
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

    @Override
    public void onClickSaveData() {
        final String qualification = presenter.getCalificacion();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        if (auth.getCurrentUser() != null) {idUser = auth.getCurrentUser().getUid();}

        getDate();
        commentEntity.comentario = presenter.getComment();
        commentEntity.hora = hour;
        commentEntity.fecha = date;
        commentEntity.calificacion = qualification;
        commentEntity.doctor = presenter.getIdDoctor();
        commentEntity.usuario = idUser;

        mFirestore.collection("comentarios").add(commentEntity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                updatingCalification(qualification);
            }
        });

    }

    @Override
    public void getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss:SS");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        hour = simpleTimeFormat.format(calendar.getTime());
        date = simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void updatingCalification(final String qualification) {

        final FirebaseFirestore mFirestoreDoct = FirebaseFirestore.getInstance();
        mFirestoreDoct.collection("doctores").document(presenter.getIdDoctor()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String comentarios = task.getResult().getString("comentario");
                        String calificacion = task.getResult().getString("calificacion");

                        Integer com = Integer.parseInt(comentarios);
                        Integer califi = Integer.parseInt(calificacion);
                        int totalCalif = califi + Integer.parseInt(qualification);
                        int totalComen = com + 1;

                        doctorEntity.cv = task.getResult().getString("cv");
                        doctorEntity.cedula = task.getResult().getString("cedula");
                        doctorEntity.especialidad = task.getResult().getString("especialidad");
                        doctorEntity.hospital = task.getResult().getString("hospital");
                        doctorEntity.calificacion = String.valueOf(totalCalif);
                        doctorEntity.comentario = String.valueOf(totalComen);

                        mFirestoreDoct.collection("doctores").document(presenter.getIdDoctor()).set(doctorEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "actualizado");
                            }
                        });
                    }
                }
            }
        });

    }


}

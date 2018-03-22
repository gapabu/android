package com.sanus.sanus.domain.doctor_module.chat.interactor;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.chat.data.ContactDoctor;
import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresent;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatDoctorInteractorImpl implements ChatDoctorInteractor{
    private ChatDoctorPresent present;
    private final String TAG= this.getClass().getSimpleName();
    private List<ContactDoctor> busquedaDoctors =  new ArrayList<>();
    private String idUser;

    public ChatDoctorInteractorImpl(ChatDoctorPresent present){this.present = present;}


    @Override
    public void init() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        final FirebaseFirestore mFirestoreUser = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }

        mFirestore.collection("contactos").document(idUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String autor = task.getResult().getString("autor");

                        mFirestoreUser.collection("usuarios").document(autor).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                final String user_id = documentSnapshot.getId();
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                final String image = documentSnapshot.getString("avatar");

                                String usuario = nombre + " " +apellido;
                                busquedaDoctors.add(new ContactDoctor(usuario,image,user_id));
                                present.setDataAdapter(busquedaDoctors);
                            }
                        });


                    }else {
                        Log.d(TAG, "data no exist");
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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
                Log.d(TAG, "Sin conexion");
            }
        });
    }
}

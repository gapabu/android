package com.sanus.sanus.domain.chat.interactor;

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
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.chat.data.ContactUser;
import com.sanus.sanus.domain.chat.presenter.ChatPresenter;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatInteractorImpl implements ChatInteractor {
    private ChatPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private List<ContactUser> busquedaDoctors =  new ArrayList<>();
    private String idUser;

    public ChatInteractorImpl(ChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        final FirebaseFirestore mFirestoreUser = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }

        mFirestore.collection("contactos").whereEqualTo("autor", idUser).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        final String doctor = document.getString("doctor");
                        mFirestoreUser.collection("usuarios").whereEqualTo("doctor", doctor).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        mFirestoreUser.collection("usuarios").document(doctor).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                            @Override
                                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                                final String user_id = documentSnapshot.getId();
                                                final String image = documentSnapshot.getString("avatar");
                                                String estado = documentSnapshot.getString("estado");
                                                String usuario = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                                                busquedaDoctors.add(new ContactUser(usuario, image, user_id, estado));
                                                presenter.setDataAdapter(busquedaDoctors);
                                            }
                                        });
                                    }
                                });
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

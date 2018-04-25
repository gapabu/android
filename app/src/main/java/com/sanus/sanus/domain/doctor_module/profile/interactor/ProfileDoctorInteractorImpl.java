package com.sanus.sanus.domain.doctor_module.profile.interactor;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenter;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDoctorInteractorImpl implements ProfileDoctorInteractor {
    private ProfileDoctorPresenter presenter;
    private String TAG = getClass().getSimpleName();

    public ProfileDoctorInteractorImpl(ProfileDoctorPresenter presenter){this.presenter = presenter;}


    @Override
    public void init(String idDoct, final RatingBar ratingBar, final TextView especialidad, final TextView cedula, final TextView cv) {

        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").document(idDoct).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String especialidad1 = task.getResult().getString("especialidad");
                        String cedul = task.getResult().getString("cedula");
                        String cv1 = task.getResult().getString("cv");
                        String comentarios = task.getResult().getString("comentario");
                        String calificacion = task.getResult().getString("calificacion");
                        Integer comen = Integer.parseInt(comentarios);
                        Integer cal = Integer.parseInt(calificacion);

                        if (comen == 0 && cal == 0){
                            ratingBar.setRating(0);
                        }else {
                            Integer valoracion = (cal / comen) / 20;
                            ratingBar.setRating(valoracion);
                        }

                        especialidad.setText(especialidad1);
                        cedula.setText(cedul);
                        cv.setText(cv1);
                    }
                }
            }
        });

    }

    @Override
    public void showImage(String idImage, final Context context, final CircleImageView image) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                presenter.showPhoto(uri);

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

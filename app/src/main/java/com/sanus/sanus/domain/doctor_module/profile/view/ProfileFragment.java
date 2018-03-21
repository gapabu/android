package com.sanus.sanus.domain.doctor_module.profile.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenterImpl;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileFragmentView  {
    public static String IDENTIFIER = "PROFILE_FRAGMENT";
    private ProfileDoctorPresenter presenter;
    TextView cedula, especialidad, cv;
    private CircleImageView setupImage;
    private String image;
    private String idDoct;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_doctor, container, false);
        setUpVariable();
        setUpView(view);
        presenter.init(idDoct, ratingBar, cedula, especialidad, cv);
        showPhoto();
        return view;
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new ProfileDoctorPresenterImpl(this);
        }
    }
    public void setUpView(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idDoct = user.getUid();
        }
        cedula = view.findViewById(R.id.tvCedula);
        especialidad = view.findViewById(R.id.tvEspecialidad);
        cv = view.findViewById(R.id.tvCv);
        setupImage = view.findViewById(R.id.setup_image);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.getRating();
    }

    private void showPhoto() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("usuarios").document(idDoct).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                image = documentSnapshot.getString("avatar");
                presenter.showImage(image, getContext(), setupImage);
            }
        });

    }
}

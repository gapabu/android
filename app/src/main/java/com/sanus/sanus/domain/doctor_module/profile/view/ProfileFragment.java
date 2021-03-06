package com.sanus.sanus.domain.doctor_module.profile.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.sanus.sanus.domain.comments.view.CommentsActivity;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.view.EditCurriculumActivity;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenterImpl;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileFragmentView  {
    public static String IDENTIFIER = "PROFILE_FRAGMENT";
    private ProfileDoctorPresenter presenter;
    private TextView cedula, especialidad, cv;
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
        FloatingActionButton goComments = view.findViewById(R.id.floatinIrComentarios);
        FloatingActionButton editCurriculum = view.findViewById(R.id.editCurriculum);

        goComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goComments();
            }
        });

        editCurriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEditCurriculum();
            }
        });
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

    public void goComments() {
        Intent intent = new Intent(getContext(), CommentsActivity.class);
        intent.putExtra("idDoctor", idDoct);
        startActivity(intent);
    }

    @Override
    public void showPhoto(Uri uri) {
        Picasso.get().load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
    }

    @Override
    public void goEditCurriculum() {
        Intent intent = new Intent(getContext(), EditCurriculumActivity.class);
        startActivity(intent);
    }
}

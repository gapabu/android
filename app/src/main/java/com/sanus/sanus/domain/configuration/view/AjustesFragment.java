package com.sanus.sanus.domain.configuration.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.account.complete.view.CompleteRegisterActivity;
import com.sanus.sanus.domain.splash.view.SplashActivity;
import com.sanus.sanus.utils.alert.CallbackAlert;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenterImpl;
import com.sanus.sanus.R;
import com.sanus.sanus.utils.alert.AlertUtils;

import com.sanus.sanus.utils.glide.GlideApp;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AjustesFragment extends Fragment implements AjustesView, CallbackAlert {

    public static String IDENTIFIER = "CONFIG_FRAGMENT";
    private AjustesPresenter presenter;
    private TextView tvNombre;
    private CircleImageView setupImage;
    private String id, image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        setUpVariable();
        setUpView(view);
        showImage();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void setUpVariable() {

        if (presenter == null) {
            presenter = new AjustesPresenterImpl(this);
        }
    }

    private void setUpView(View view) {

        tvNombre = view.findViewById(R.id.tvNombre);
        setupImage = view.findViewById(R.id.avatar);
        LinearLayout linearLayoutLogout = view.findViewById(R.id.logout);
        linearLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertLogout();
            }
        });

        LinearLayout linearLayoutEdit = view.findViewById(R.id.editProfile);
        linearLayoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CompleteRegisterActivity.class));
            }
        });

    }

    private void showAlertLogout() {
        AlertUtils alertUtils = new AlertUtils(this);
        alertUtils.configureAlert(getContext(), getString(R.string.logout_msg));
    }

    @Override
    public void acceptAlert() {
        presenter.logout();
    }

    @Override
    public void cancelAlert() {

    }

    @Override
    public void goSplash() {
        Intent intent = new Intent(getActivity(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        if(getActivity() != null){
            getActivity().finish();
        }
    }

    @Override
    public void showData(String name, String email) {
        tvNombre.setText(email);
    }

    @Override
    public void showPhoto(String photo) {
        GlideApp.with(this).load(photo).transform(new RoundedCorners(500)).diskCacheStrategy(DiskCacheStrategy.ALL).into(setupImage);
    }

    private void showImage(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        if (user != null) {
            id = user.getUid();
        }

        mFirestore.collection("usuarios").document(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                String nombre = documentSnapshot.getString("nombre");
                String apellido = documentSnapshot.getString("apellido");
                image = documentSnapshot.getString("avatar");
                String usuario = nombre + " " +apellido;
                tvNombre.setText(usuario);
                final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
                storageReference.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(getContext()).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "error al traer imagen", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

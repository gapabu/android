package com.sanus.sanus.domain.configuration.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class AjustesFragment extends Fragment implements AjustesView, CallbackAlert {

    public static String IDENTIFIER = "CONFIG_FRAGMENT";
    private AjustesPresenter presenter;
    private TextView tvNombre;
    private CircleImageView setupImage;
    private String idUser, image;
    private Button activo, inactivo;
    private FirebaseAuth.AuthStateListener mAutthListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        setUpVariable();
        setUpView(view);
        showPhoto(image);
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

        activo = view.findViewById(R.id.btnActivo);
        inactivo = view.findViewById(R.id.btnInactivo);

        activo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActive();
            }
        });

        inactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectInactive();
            }
        });

        mAutthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    goSplash();
                }
            }
        };

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
    public void showPhoto(final String photo) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        if (user != null) {
            idUser = user.getUid();
        }

        mFirestore.collection("usuarios").document(idUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
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
                        GlideApp.with(getContext()).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Log.d(String.valueOf(this), "no hay conexion" );
                    }
                });
            }
        });
    }

    @Override
    public void selectActive() {
        activo.setBackgroundColor(getResources().getColor(R.color.black));
        activo.setTextColor(getResources().getColor(R.color.white));
        inactivo.setBackgroundColor(getResources().getColor(R.color.white));
        inactivo.setTextColor(getResources().getColor(R.color.black));
        //presenter.onClickActive("1");

    }

    @Override
    public void selectInactive() {
        inactivo.setBackgroundColor(getResources().getColor(R.color.black));
        inactivo.setTextColor(getResources().getColor(R.color.white));
        activo.setBackgroundColor(getResources().getColor(R.color.white));
        activo.setTextColor(getResources().getColor(R.color.black));
        //presenter.onClickActive("0");
    }
}

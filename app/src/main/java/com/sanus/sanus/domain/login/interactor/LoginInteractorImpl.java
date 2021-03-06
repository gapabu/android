package com.sanus.sanus.domain.login.interactor;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;
import com.sanus.sanus.data.repository.firebase.entity.user.UserEntity;
import com.sanus.sanus.domain.login.presenter.LoginPresenter;
import com.sanus.sanus.utils.regex.RegexUtils;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;
    private ProgressDialog loading;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG = this.getClass().getSimpleName();
    private UserEntity userEntity = new UserEntity();
    private String idUser;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClickLogin() {
        showLoading();
        presenter.signInWithEmailAndPassword();
    }

    @Override
    public void signInWithEmailAndPasswordComplete(Task<AuthResult> task) {
        cancelLoading();
        if (!task.isSuccessful()) {
            presenter.showMessage(R.string.autenticacion_fallida);
            return;
        }


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String userIdNow = user.getUid();
            DocumentReference usuarios = db.collection("usuarios").document(userIdNow);
            usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String tipo = document.getString("tipo");
                            if (tipo.equals("Medico")){
                                presenter.goMainDoctor();
                                onClickActive("1");
                            }
                            if (tipo.equals("Paciente")){
                                presenter.goMain();
                                onClickActive("1");
                            }

                            Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
                        } else {
                            presenter.showAlertRegister();
                            Log.d(TAG, "No such document");
                        }
                    } else {
                       presenter.showMessage(R.string.error);
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            return;
        }

        presenter.showMessage(R.string.error);


    }

    @Override
    public void validateButtonEnable() {
        if (presenter.getEmail().matches(RegexUtils.emailPattern()) && presenter.getPassword().length() > 6) {
            presenter.enableButton();
            return;
        }
        presenter.disableButton();
    }

    @Override
    public void onClickActive(final String estado) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }

        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("usuarios").document(idUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        userEntity.apellido = task.getResult().getString("apellido");
                        userEntity.avatar = task.getResult().getString("avatar");
                        userEntity.edad = task.getResult().getString("edad");
                        userEntity.nombre = task.getResult().getString("nombre");
                        userEntity.sexo = task.getResult().getString("sexo");
                        userEntity.tipo = task.getResult().getString("tipo");
                        userEntity.estado = estado;
                        mFirestore.collection("usuarios").document(idUser).set(userEntity).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                    }
                }
            }
        });
    }

    private void showLoading() {

        loading = presenter.getLoading();
        loading.setCancelable(false);
        loading.show();
        loading.setContentView(R.layout.alert_loading);
    }

    private void cancelLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }

    @Override
    public void acceptAlert() {
        presenter.goCompleteRegister();
    }

    @Override
    public void cancelAlert() {
        FirebaseAuth.getInstance().signOut();
    }
}

package com.sanus.sanus.domain.new_chat.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainActivityDoctor;
import com.sanus.sanus.domain.new_chat.adapter.MessagesAdapter;
import com.sanus.sanus.domain.new_chat.data.Messages;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NewChatActivity extends AppCompatActivity implements NewChatView {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private NewChatPresenter presenter;
    private Toolbar toolbar;
    private String idDoct;
    private String idUser;
    private EditText edNewMessage;
    private String message;
    private String hour;
    private String date;
    RecyclerView recyclerView;
    MessagesAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         setUpVariable();
         setUpView();
         showDataDoctor();
         getDate();
         viewMessagesByTipe();
    }

    private void setUpVariable() {
            if(presenter == null){
                presenter = new NewChatPresenterImpl(this);
            }
    }

    private void setUpView() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }
        idDoct = getIntent().getStringExtra("idDoctor");
        toolbar = findViewById(R.id.toolbar);
        edNewMessage = findViewById(R.id.editMessage);

        FloatingActionButton sendMessage = findViewById(R.id.btnSaveMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = edNewMessage.getText().toString();
                sendMessagesByType();

            }
        });



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                    DocumentReference usuarios = db.collection("usuarios").document(idUser);
                    usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    String tipo = document.getString("tipo");
                                    if (tipo.equals("Medico")) {
                                        goFramentChat();
                                    }
                                    if (tipo.equals("Paciente")) {
                                        goCurriculum();
                                    }
                                }
                            }
                        }
                    });

                break;
                }
                return true;
        }



    @Override
    public void showDataDoctor() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("usuarios").document(idDoct).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                String nombre = documentSnapshot.getString("nombre");
                String apellido = documentSnapshot.getString("apellido");

                String usuario = nombre + " " + apellido;
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(usuario);
            }

        });
    }

    @Override
    public void getDate() {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss:SS");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar calendar = Calendar.getInstance();

        hour = simpleTimeFormat.format(calendar.getTime());
        date = simpleDateFormat.format(calendar.getTime());

        /*int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int anio = calendar.get(Calendar.YEAR);
        int hora = calendar.get(Calendar.HOUR);
        int minutos = calendar.get(Calendar.MINUTE);
        int segundos = calendar.get(Calendar.SECOND);*/
        //hour = (hora + ":" + minutos + ":" + segundos);
        //date = (dia + "/" + (mes + 1) + "/" + anio);

    }

    @Override
    public void setDataAdapter(List<Messages> commentsDoctorList) {
        MessagesAdapter commentsDoctorAdapter = new MessagesAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

    @Override
    public void goMessages() {
        Intent intent = new Intent(getApplicationContext(), NewChatActivity.class);
        intent.putExtra("idDoctor", idDoct);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void goCurriculum() {
        Intent intent = new Intent(NewChatActivity.this, CurriculumActivity.class);
        intent.putExtra("idDoctor", idDoct);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void goFramentChat() {
        Intent intent = new Intent(NewChatActivity.this, MainActivityDoctor.class);
        intent.putExtra("idDoctor", idDoct);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void viewMessagesByTipe() {
        DocumentReference usuarios = db.collection("usuarios").document(idUser);
        usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String tipo = document.getString("tipo");
                        if (tipo.equals("Medico")){
                            presenter.viewMessages(idUser, idDoct);
                            edNewMessage.getText().clear();
                        }
                        if (tipo.equals("Paciente")){
                            edNewMessage.getText().clear();
                            presenter.viewMessages(idDoct, idUser);

                        }
                    }
                }
            }
        });
    }

    @Override
    public void sendMessagesByType() {
        DocumentReference usuarios = db.collection("usuarios").document(idUser);
        usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String tipo = document.getString("tipo");
                        if (tipo.equals("Medico")) {
                            presenter.sendMessages(idUser, idUser, date, hour, message, idDoct);
                        }
                        if (tipo.equals("Paciente")) {
                            presenter.sendMessages(idUser, idDoct, date, hour, message, idUser);
                        }
                    }
                }
            }
        });
    }
}

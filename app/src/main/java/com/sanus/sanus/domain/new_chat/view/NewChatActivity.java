package com.sanus.sanus.domain.new_chat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

import java.util.List;

public class NewChatActivity extends AppCompatActivity implements NewChatView{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private NewChatPresenter presenter;
    private Toolbar toolbar;
    private String idDoct, idUser;
    private EditText edNewMessage;
    private RecyclerView recyclerView;
    MessagesAdapter adapter;
    private int TEXT_LINES = 1;

    /*List<Messages> commentsDoctorList;
    MessagesAdapter commentsDoctorAdapter = new MessagesAdapter(this, commentsDoctorList, presenter);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         setUpVariable();
         setUpView();
         showDataDoctor();
         viewMessagesByTipe();
    }

    private void setUpVariable() {
            if(presenter == null){presenter = new NewChatPresenterImpl(this);}
    }

    private void setUpView() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {idUser = user.getUid();}

        idDoct = getIntent().getStringExtra("idDoctor");
        toolbar = findViewById(R.id.toolbar);
        edNewMessage = findViewById(R.id.editMessage);

        edNewMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(NewChatActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                if (edNewMessage.getLayout().getLineCount() != TEXT_LINES){
                    //recyclerView.scrollToPosition(commentsDoctorAdapter.getItemCount() -1);
                    TEXT_LINES = edNewMessage.getLayout().getLineCount();
                    setSctollbarChat();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        FloatingActionButton sendMessage = findViewById(R.id.btnSaveMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendMessagesByType();}
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //recyclerView.scrollToPosition(recyclerView.getScrollBarSize() -1);

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
                String usuario = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(usuario);
            }
        });
    }

    @Override
    public void setDataAdapter(List<Messages> commentsDoctorList) {
        MessagesAdapter commentsDoctorAdapter = new MessagesAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        recyclerView.scrollToPosition(commentsDoctorAdapter.getItemCount() -1);
        commentsDoctorAdapter.notifyDataSetChanged();
        //setSctollbarChat();
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
                            presenter.viewMessages(idDoct, idUser);
                            edNewMessage.getText().clear();
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
                            presenter.sendMessages(idUser, idUser, idDoct);
                        }
                        if (tipo.equals("Paciente")) {
                            presenter.sendMessages(idUser, idDoct, idUser);
                        }
                    }
                }
            }
        });
    }

    public void setSctollbarChat(){
        recyclerView.scrollToPosition(adapter.getItemCount() -1);
    }
    @Override
    public String getMessages() {
        return edNewMessage.getText().toString();
    }
}

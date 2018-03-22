package com.sanus.sanus.domain.new_chat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenterImpl;

import java.util.Calendar;


public class NewChatActivity extends AppCompatActivity implements NewChatView {
    private NewChatPresenter presenter;
    private Toolbar toolbar;
    private String idDoct;
    private String idUser;
    private EditText edNewMessage;
    private String message;
    private String hour;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         setUpVariable();
         setUpView();
         showDataDoctor();
         getDate();

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
                presenter.sendMessages(idUser, idDoct, date, hour, message);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, CurriculumActivity.class);
                intent.putExtra("idDoctor", idDoct);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
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
        final Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int anio = calendar.get(Calendar.YEAR);
        int hora = calendar.get(Calendar.HOUR);
        int minutos = calendar.get(Calendar.MINUTE);

        hour = (hora + ":" + minutos);
        date = (dia + "/" + (mes + 1) + "/" + anio);
    }
}
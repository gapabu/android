package com.sanus.sanus.domain.curriculum.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenterImpl;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.domain.new_chat.view.NewChatActivity;
import com.sanus.sanus.utils.glide.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurriculumActivity extends AppCompatActivity implements CurriculumView {

    private CurriculumPresenter presenter;
    private Toolbar toolbar;
    private TextView cedula, especialidad, cv, name;
    private CircleImageView setupImage;
    private String image;
    private String idDoct;
    private String idUser;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        setUpVariable();
        setUpView();
        showDataDoctor();
        presenter.init(idDoct, ratingBar, cedula, especialidad, cv);
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new CurriculumPresenterImpl(this);
        }
    }

    public void setUpView() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }
        idDoct = getIntent().getStringExtra("idDoctor");
        toolbar = findViewById(R.id.toolbar);
        cedula = findViewById(R.id.tvCedula);
        especialidad = findViewById(R.id.tvEspecialidad);
        cv = findViewById(R.id.tvCv);
        name = findViewById(R.id.nameDocto);
        setupImage = findViewById(R.id.setup_image);
        FloatingActionButton goComent = findViewById(R.id.floatinIrComentarios);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.getRating();

        FloatingActionButton newChat = findViewById(R.id.imgNewChat);

        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.verifyContact(idUser, idDoct);

            }
        });


        goComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goComments();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void goComments() {
            Intent intent = new Intent(CurriculumActivity.this, CommentsActivity.class);
            intent.putExtra("idDoctor", idDoct);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
    }

    @Override
    public void goNewChat() {
        Intent intent = new Intent(CurriculumActivity.this, NewChatActivity.class);
        intent.putExtra("idDoctor", idDoct);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void showDataDoctor() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("usuarios").document(idDoct).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                image = documentSnapshot.getString("avatar");

                presenter.showImage(image);
                name.setText(documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido")));

                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(" ");
            }
        });
    }

    @Override
    public void showPhoto(Uri uri) {
        GlideApp.with(getApplicationContext()).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
    }


}



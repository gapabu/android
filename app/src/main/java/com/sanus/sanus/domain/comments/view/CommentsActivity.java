package com.sanus.sanus.domain.comments.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenterImpl;

public class CommentsActivity extends AppCompatActivity implements CommentsView{
    private CommentsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);

        setUpVariable();
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new CommentsPresenterImpl(this);
        }
    }
}

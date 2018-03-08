package com.sanus.sanus.domain.new_cita_main.view;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.new_cita_main.Adapter.MyPagerAdapter;
import com.sanus.sanus.domain.new_cita_main.presenter.NewCitaPresenter;
import com.sanus.sanus.domain.new_cita_main.presenter.NewCitaPresenterImpl;
import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaActivity;

public class NewCitaActivity extends AppCompatActivity implements NewCitaView{
    private NewCitaPresenter presenter;

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dotstv;
    private int[]layouts;
   // private Button btnSkip;
    //private Button btnNext;
    private ImageView btnSkip;
    private ImageView btnNext;
    private MyPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStatusBarTransparent();
        setContentView(R.layout.activity_new_cita);

        setUpVariable();

        viewPager = findViewById(R.id.view_pager);
        layoutDot = findViewById(R.id.dotLayout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        //When user press skip, start Main Activity
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()-1;
                if(currentPage < layouts.length) {
                    //move to next page
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startMainActivity();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length) {
                    //move to next page
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startMainActivity();
                }
            }
        });
        layouts = new int[]{R.layout.select_hospital,R.layout.select_doctor, R.layout.select_day, R.layout.select_hour};
        pagerAdapter = new MyPagerAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == layouts.length-1){
                    //LAST PAGE
                    //btnNext.setText("START");
                    btnSkip.setVisibility(View.GONE);
                }else {
                    //btnNext.setText("NEXT");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);



    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new NewCitaPresenterImpl(this);
        }

    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotstv =new TextView[layouts.length];
        for (int i = 0; i < dotstv.length; i++) {
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }
        //Set current dot active
        if(dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#0094EA"));
        }
    }
    private void startMainActivity(){
        //setFirstTimeStartStatus(false);
        startActivity(new Intent(NewCitaActivity.this, ResumeNewCitaActivity.class));
        finish();
    }
    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }
}

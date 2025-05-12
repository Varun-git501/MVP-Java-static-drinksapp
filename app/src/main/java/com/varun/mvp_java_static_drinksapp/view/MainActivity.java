package com.varun.mvp_java_static_drinksapp.view;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.varun.mvp_java_static_drinksapp.R;
import com.varun.mvp_java_static_drinksapp.presenter.IPresenter;
import com.varun.mvp_java_static_drinksapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IView {

   public TextView tvDrinkName, tvLoginResult;
   public ProgressBar progressBar;
   public Button bGetDrink;

//   MainPresenter mainController;
    IPresenter iPresenter;  // add the Presenter interface reference in the View. So that i will communicate with it.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDrinkName = findViewById(R.id.tvDrinkName);
        progressBar = findViewById(R.id.progressBar);
        bGetDrink = findViewById(R.id.bGetDrink);

//        mainController = new MainPresenter(this);
        iPresenter = new MainPresenter(this);

        bGetDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainController.suggestDrinkController();
                iPresenter.suggestDrink();
            }
        });

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showDrinkToUser(String drinkName) {
        tvDrinkName.setText(drinkName);
    }
}

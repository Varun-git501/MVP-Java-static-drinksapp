package com.varun.mvp_java_static_drinksapp.presenter;

import android.media.ImageReader;
import android.view.View;

import com.varun.mvp_java_static_drinksapp.repository.IRepository;
import com.varun.mvp_java_static_drinksapp.repository.MainRepository;
import com.varun.mvp_java_static_drinksapp.view.IView;
import com.varun.mvp_java_static_drinksapp.view.MainActivity;

public class MainPresenter implements IPresenter{

    IView iView;
    IRepository iRepository;

//    private final MainRepository mainRepository;
//    private final MainActivity mainActivity;

    public MainPresenter(IView iView){
        this.iView = iView;
        iRepository = new MainRepository(this);
    }
//    public MainPresenter(MainActivity mainActivity){
//        this.mainActivity = mainActivity;
//        drinksModel = new DrinksModel(this);
//    }
//    public void suggestDrinkController(){
//        this.mainActivity.progressBar.setVisibility(View.VISIBLE);
//        mainRepository.suggestDrinkModel();
//    }

    @Override
    public void suggestDrink() {
        iView.showProgress();  // show the progress when the function is called
        iRepository.suggestNewDrink();
    }

    @Override
    public void onDrinkSuggested(String drinkName) {
        iView.hideProgress();       // hide the progress when response is retrieved
        iView.showDrinkToUser(drinkName);
    }

   /* public void onDrinkSuggested(String drinkName){
        this.mainActivity.tvDrinkName.setText(drinkName);
        this.mainActivity.progressBar.setVisibility(View.INVISIBLE);
    }
    */
}

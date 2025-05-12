package com.varun.mvp_java_static_drinksapp.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.varun.mvp_java_static_drinksapp.presenter.IPresenter;
import com.varun.mvp_java_static_drinksapp.presenter.MainPresenter;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository implements IRepository {
    String[] drinksListRemote = {"Spiking coffee", "Sweet Bananas", "Tomato Tang",
            "Apple Berry Smoothie", "Coding Reel Coffee"};
    String[] drinksListLocal = {"Mint Magret"};

//    private final MainPresenter mainController;
    IPresenter iPresenter;

    boolean isLocalDrinkAvailable;

    public MainRepository(IPresenter iPresenter){
        this.iPresenter = iPresenter;
        isLocalDrinkAvailable = false;
    }
//    public MainRepository(MainPresenter controller){
//        this.mainController = controller;
//    }
//    public void suggestDrinkModel() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.getMainLooper());
//        final String[] drinkName = {""};
//        //Before executing background task
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                //Background work here
//                try {
//                    Thread.sleep(1000); // Mimic server request / long execution
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                drinkName[0] = drinksListRemote[new Random()
//                            .nextInt(drinksListRemote.length)];
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                    mainController.onDrinkSuggested(drinkName[0]);
//                    }
//                });
//            }
//        });
//    }


    public void suggestDrinkLocal() {
        String drinkName = drinksListLocal[new Random()
                .nextInt(drinksListLocal.length)];
        iPresenter.onDrinkSuggested(drinkName);

    }

    public void suggestDrinkRemote() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] drinkName = {""};
        //Before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                try {
                    Thread.sleep(1000); // Mimic server request / long execution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                drinkName[0] = drinksListRemote[new Random()
                        .nextInt(drinksListRemote.length)];
                Log.e( "drinkName: ", drinkName[0]);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //mainController.onDrinkSuggested(drinkName[0]);
                        iPresenter.onDrinkSuggested(drinkName[0]); // When the drink is retrieved from the list,
                                                                    // we need to use this interface reference
                                                                    // to send back to the response

                    }
                });
            }
        });
    }

    @Override
    public void suggestNewDrink() {
        if(isLocalDrinkAvailable) {
            suggestDrinkLocal();
        }else{
            suggestDrinkRemote();
        }
    }
}

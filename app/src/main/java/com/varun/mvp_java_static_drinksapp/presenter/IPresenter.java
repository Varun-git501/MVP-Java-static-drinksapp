package com.varun.mvp_java_static_drinksapp.presenter;

public interface IPresenter {
    void suggestDrink();
    void onDrinkSuggested(String drinkName);
}

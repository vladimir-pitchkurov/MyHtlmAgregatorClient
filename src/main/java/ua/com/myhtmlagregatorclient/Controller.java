package ua.com.myhtmlagregatorclient;

import ua.com.myhtmlagregatorclient.model.Model;

public class Controller {
    private Model model;

    public Controller(Model model) {
        if (model == null){
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}

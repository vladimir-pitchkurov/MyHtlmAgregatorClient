package ua.com.myhtmlagregatorclient.model;

import ua.com.myhtmlagregatorclient.view.View;
import ua.com.myhtmlagregatorclient.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}

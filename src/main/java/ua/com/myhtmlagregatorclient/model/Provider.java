package ua.com.myhtmlagregatorclient.model;


import ua.com.myhtmlagregatorclient.vo.Vacancy;

import java.util.Collections;
import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        List<Vacancy> vacancies;
        if (strategy != null) {
            vacancies = strategy.getVacancies(searchString);
        } else {
            vacancies = Collections.emptyList();
        }
        return vacancies;
    }
}

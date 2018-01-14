package ua.com.myhtmlagregatorclient.model;


import ua.com.myhtmlagregatorclient.vo.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}

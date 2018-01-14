package ua.com.myhtmlagregatorclient.view;

import ua.com.myhtmlagregatorclient.Controller;
import ua.com.myhtmlagregatorclient.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}

package ua.com.myhtmlagregatorclient;


import ua.com.myhtmlagregatorclient.model.HHStrategy;
import ua.com.myhtmlagregatorclient.model.Model;
import ua.com.myhtmlagregatorclient.model.Provider;
import ua.com.myhtmlagregatorclient.view.HtmlView;

import java.io.IOException;

public class Aggregator {
    public static void main(String[] args) throws IOException {
        HtmlView htmlView = new HtmlView();
        Provider provider = new Provider(new HHStrategy());
        Model model = new Model(htmlView, provider);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}

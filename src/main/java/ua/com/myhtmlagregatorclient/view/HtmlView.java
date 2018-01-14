package ua.com.myhtmlagregatorclient.view;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ua.com.myhtmlagregatorclient.Aggregator;
import ua.com.myhtmlagregatorclient.Controller;
import ua.com.myhtmlagregatorclient.util.MyProperties;
import ua.com.myhtmlagregatorclient.vo.Vacancy;

import java.io.*;
import java.util.List;


public class HtmlView implements View {

    private Controller controller;
    private String filePath = getFilePath();

    /*get property*/
    private String getFilePath() {
        String propertyPath = MyProperties.getMyProperties().getProperty("vacancies.path");
        if (propertyPath == null || propertyPath.equals("")) {
            propertyPath = "C:/TestAgregator/vacancies.html";
        }
        return propertyPath;
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /*get property*/
    public void userCitySelectEmulationMethod() {
        String city = "";
        city = MyProperties.getMyProperties().getProperty("city");
        if (city == null || city.equals("")) {
            city = "dnepr";
        }
        controller.onCitySelect(city);
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent;
        try {
            //получаем файл
            Document document = getDocument();

            //Находим блок (элемент с упоминанием в имени класса template)
            Element templateElement = document.select(".template").first();
            //Делаем копию этого элемента
            Element cleanElement = templateElement.clone();
            //удаляем с неe лишние аттрибуты и классы
            cleanElement.removeAttr("style");
            cleanElement.removeClass("template");
            //чистим ранее добавленные вакансии в этом файле
            document.select("tr[class=vacancy]").remove();

            //добавляем вакансии
            for (Vacancy vacancy : vacancies) {
                //делаем новую копию скопированного уже чистого элемента
                Element vacancyElement = cleanElement.clone();
                //и заполняем его данными по текущей вакансии
                vacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                vacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                vacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                //создаем ссылку
                Element link = vacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                //добавляем outerHtml элемента в который мы записывали данные вакансии, непосредственно перед шаблоном <tr class="vacancy template" style="display: none">
                templateElement.before(vacancyElement.outerHtml());
            }

            //возвращаем код всего документа
            fileContent = document.html();

        } catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }
        return fileContent;
    }

    private void updateFile(String s) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + "/vacancies.html"));
            bufferedWriter.write(s);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        File vacancyNew = null;
        if (file.isFile() && !file.isDirectory()) {
            return Jsoup.parse(new File(filePath + "/vacancies.html"), "UTF-8");
        } else {
            InputStream inputStream = null;
            vacancyNew = new File(filePath, "vacancies.html");
            boolean newFile = vacancyNew.createNewFile();

            FileOutputStream writer = null;
            if (newFile) {
                try {
                    inputStream = Aggregator.class.getClassLoader().getResourceAsStream("vacancies.html");
                    writer = new FileOutputStream(filePath + "/vacancies.html");
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        writer.write(buffer, 0, length);
                    }
                } catch (Exception e) {
                    inputStream.close();
                    writer.close();
                    e.printStackTrace();
                } finally {
                    inputStream.close();
                    writer.close();
                }
            }
        }
        return Jsoup.parse(new File(filePath + "/vacancies.html"), "UTF-8");
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}

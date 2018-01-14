пока консольная версия.
получает список вакансий с сайта ХеадХантер(в следующих версиях и с других сайтов)
записывает все вакансии в HTML-файл на жестком диске
есть 3 настройки в файле app.properties

vacancies.path=E:/TestAgregator/vacancies.html      - (default: C:/TestAgregator/vacancies.html)
city=kiev                                           - (default:  "dnepr")
vacancy=                                            - (default:   "java")

запуски из командной строки windows:(в той же директории, где скомпилированный JAR)::  java -jar MyHtlmAgregatorClient-jar-with-dependencies.jar


планируется доработка: подключение (swing or javaFx) desktop version and add functional


# distance-calculator-settings


Для первого запуска приложения необходимо:

- Создать базу данных(схему) "distance_calculator" в MySQL Workbench
- Прописать настройки драйвера mySQL в сервере приложений WildFly.
    Для этого (скопировать драйвер и файл с настройками) скопировать
    папку /mysql/ находящуюся в папке scripts в папку сервера приложений
    wildfly-14.0.0.Final/modules/system/layers/base/com.
- Прописать в файле standalone.xml настройки в тегах <datasource> и <driver>.
    содержимое тегов находиться в файле standalone_settings.xml в папке scripts.
- Создать таблицы в базе данных : mvn liquibase:update
- Собрать и задеплоить приложение
- Запустить сервер приложений Wildfly с помощью файла
    wildfly-14.0.0.Final/bin/standalone.bat
- Приложение должно быть по адресу: http://127.0.0.1:8080/distance-calculator-1.0/

# SalesInfo
Program, that return from PostgresDB information about customers and purchases.

# Краткое описание
Программа выполняет следующие действия:
* чтение из параметров (аргументов) запуска приложения и входного файла запросов в JSON формате;
* получение информации из базы данных PostgreSQL;
* сохранение ответов в выходной файл в JSON формате одного из трех типов:
    - search - данные результатов поиска по базе данных;
    - stat - статистические данные по покупателям за определенный период;
    - error - ответ в случае возникновения ошибки во время выполнения с описанием ошибки

# Используемый стек:
* JDK 8;
* PostgreSQL;
* Maven;
* Hibernate;
* Jackson JSON

# Инструкция по запуску

Во время работа приложения с помощью Hibernate в базе данных по умолчанию postgres в схеме (schemas) public
создаются следующие таблицы:
* customer;
* product;
* purchase

1. Для работы Hibernate в файле конфигурации hibernate.cfg.xml необходимо указать корректные
значения настроек:
* <property name="connection.url">jdbc:postgresql://localhost:5432/postgres</property> - номер порта;
* <property name="connection.username">postgres</property> - логин;
* <property name="connection.password">ABCdef123$%^</property> - пароль
2. Для заполнения базы данных можно воспользоваться файлом dumpDBTables.sql, в котором содержится дамп
всех таблиц (customer, product, purchase) либо запустить файлы customerTable.sql, productTable.sql, purchaseTable.sql 
по отдельности в указанной последовательности. Скрипты можно выполнить из pgAdmin 4 (до запуска программы).
3. В папке data находятся следующие файлы:
* cliArgs.txt - значения параметров запуска из IDE, командной строки и для тестирования функционала;
* inputSearch.json - входной файл для проверки команды SEARCH;
* inputStat.json - входной файл для проверки команды STAT
4. После настройки Hibernate и загрузки базы данных можно запускать приложение одним из следующих способов:
* из IDE:
    - в настройках запуска указать значения аргументов командной строки (расположение входного файла inputSearch.json 
    или inputStat.json должно соответствовать данным, указываемым в настройках запуска);
    - выполнить непосредственный запуск приложения;
    - результат работы приложения будет записан в файл, указанный в аргументах командной строки
* из командной строки (cli):
    - при помощи Maven необходимо создать пакет с зависимостями (jar-with-dependencies) командой package;
    - после успешного ее выполнения необходимо файл SalesInfo-1.0-jar-with-dependencies.jar из папки target поместить
    одну папку с входными файлами (inputSearch.json и/или inputStat.json);
    - затем в командной строке выбрать эту папку и выполнить в ней запуск приложения одной из следующих команд, например:
        * java -jar SalesInfo-1.0-jar-with-dependencies.jar search inputSearch.json output.json;
        * java -jar SalesInfo-1.0-jar-with-dependencies.jar stat inputStat.json output.json
    - результат работы приложения будет записан файл, указанный в аргументах командной строки
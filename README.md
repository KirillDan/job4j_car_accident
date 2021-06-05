[![Build Status](https://travis-ci.org/KirillDan/job4j_car_accident.svg?branch=develop)](https://travis-ci.org/KirillDan/job4j_car_accident)
<br/>
# Проект "Автонарушители"

### О проекте

Приложение имеет CRUD функционал для инцидентов. Для каждого инцидента можно указывать
список статей, описание, тип и др.

Использованные технологии в проекте:

- клиентская часть: HTML, CSS, JS.
- серверная часть: Spring, Spring Security, Spring Data, liquibase

### Сборка

Необходимо создать базу данных auto_crash в СУБД PostgreSQL и указать параметры в файле src/main/resources/application.properties:
```
jdbc.url=jdbc:postgresql://127.0.0.1:5432/auto_crash
jdbc.username=postgres
jdbc.password=123
```

Cобрать maven проект: mvn package

Запуск на сервере Tomcat

### Использование

* Регистрация

![registration](images/registration.png)

* Вход

![login](images/login.png)

* Главная страница

![index](images/index.png)

* Создание нарушения

![create](images/create.png)

* Страница Изменения нарушения

![update](images/update.png)

измененные данные

![create](images/create.png)

* logout

![logout](images/logout.png)

### Контакты

* Skype: live:.cid.8f17c3f8d147e77





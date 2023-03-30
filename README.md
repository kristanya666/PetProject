# **SWAPI Project**

В этом проекте описаны API тесты Star Wars API (SWAPI). 


### Цель 

Проверить функциональность Star Wars API, попрактиковаться в API тестах, а также запустить весь проект в Jenkins и сгенерировать отчет в Allure Report.

### Инструменты

- Java (версия 19)
- IntelliJ IDEA 
- JUnit5
- Gradle (версия 7.6)
- Rest-Assured
- Allure Report
- Jenkins

### Ресурсы
https://swapi.dev/

### Тест кейсы
* Проверить функциональность эндпоинта "People"
* Проверить функциональность эндпоинта "Films"
* Проверить функциональность эндпоинта "Starships"
* Проверить функциональность эндпоинта "Vehicles"
* Проверить функциональность эндпоинта "Species"
* Проверить функциональность эндпоинта "Planets"


###  Шаги тестирования к каждому кейсу:

Шаг 1: Создать GET запрос для каждого эндпоинта.

Шаг 2: Проверить, что код ответа равен 200 (OK) для каждого эндпоинта.

Шаг 3: Проверить , что по айди можно получить данные конкретной сущности.

Шаг 4: Проверить, что тело ответа по конкретной сущности содержит ожидаемые данные (с помощью _json schema_).

Шаг 5: Проверить, что время ответа находится в допустимом диапазоне для каждой конечной точки (до 5 секунд).

Шаг 6: Проверить, что отображаются сообщения об ошибках при неверных запросах:
* проверить, что при POST запросе выходит код ответа 405, запрос не отправится
* проверить, что при невалидном айди выходит код ответа 404, сущность будет не найдена

### Метрики тестирования:
Для измерения эффективности тестирования API будут использоваться следующие метрики:

* Количество выполненных тестовых случаев
* Количество пройденных / не пройденных тестовых случаев
* Время ответа для каждой конечной точки


### Отчет о тестировании
![reportSWAPI](https://github.com/kristanya666/PetProject/blob/master/src/test/resources/allure1.png)
![reportSWAPI2](https://github.com/kristanya666/PetProject/blob/master/src/test/resources/allure2.png)
![reportSWAPI3](https://github.com/kristanya666/PetProject/blob/master/src/test/resources/allure3.png)

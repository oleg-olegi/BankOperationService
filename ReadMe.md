Стек: Java 17 Spring Boot 3 База данных PostgreSQL Maven REST API Swagger 
# Запуск API на Java с PostgreSQL

Этот проект представляет собой пример простого RESTful API на Java с использованием PostgreSQL в качестве базы данных.

## Требования

- Java Development Kit (JDK)
- Apache Maven
- PostgreSQL

## Установка и настройка

1. **Клонирование репозитория:**

```
git clone https://github.com/oleg-olegi/BankOperationService 
```

2. **Создание базы данных:**

Создайте базу данных PostgreSQL и настройте соединение в файле application.properties.

Сборка проекта:

Перейдите в корневую директорию проекта и выполните: 

```
mvn clean install
```

## Запуск

3. **Запуск приложения:**

После успешной сборки приложения выполните:

```
java -jar target/BankOperationService.jar
 ```
## Тестирование API:

4. 
После запуска приложения API будет доступно по адресу `http://localhost:8080`
Тестирование возможно через Swagger, либо
 используйте любой HTTP-клиент (например, cURL, Postman) для тестирования эндпоинтов.
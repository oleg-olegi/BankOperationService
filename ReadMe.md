Стек: Java 17 Spring Boot 3 База данных PostgreSQL Maven REST API
# Запуск API на Java с PostgreSQL

Этот проект представляет собой пример простого RESTful API на Java с использованием PostgreSQL в качестве базы данных.

## Требования

- Java Development Kit (JDK)
- Apache Maven
- PostgreSQL

## Установка и настройка

1. **Клонирование репозитория:**

   ```bash
   git clone https://github.com/oleg-olegi/BankOperationService 

2. **Создание базы данных:**

Создайте базу данных PostgreSQL и настройте соединение в файле application.properties.

Сборка проекта:

Перейдите в корневую директорию проекта и выполните: 
```bash
mvn clean install

## Запуск

После успешной сборки приложения выполните:

```bash
Copy code
java -jar target/BankOperationService.jar
 

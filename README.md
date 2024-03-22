## Проєкт "Java_Site" - це веб-додаток, розроблений на Java за допомогою фреймворка Spring Boot. 
Додаток призначений для керування продуктами, фірмами та групами продуктів, що реалізовані як таблиці сервіса MySQL. 
Користувачі можуть додавати, переглядати, редагувати та видаляти продукти, фірми та групи продуктів через веб-інтерфейс.

## Налаштування MySQL

1. **Встановіть MySQL Server** на вашому комп'ютері, якщо він ще не встановлений.
2. **Створіть нову базу даних** та таблиці, виконавши скрипт з файлу "initSQL.sql", що знаходиться у папці "src/main/resources/database/initSQL.sql".
3. **Виконайте скрипт** файлу "addFields.sql" із тієї ж папки, щоб заповнити їх таблиці початковими даними.

## Запуск проєкту
1. Клонуйте репозиторій проекту на свій локальний комп'ютер.
2. Відкрийте проєкт у вашому редакторі коду.
3. Відкрийте файл "application.properties" у папці "src/main/resources".
4. Замініть значення "spring.datasource.username" та "spring.datasource.password" на свої дані для підключення до вашої бази даних MySQL.
5. Запустіть додаток, виконавши клас "SiteApplication.java".
6. Відкрийте веб-браузер і перейдіть за адресою http://localhost:8080/, щоб переглянути та використовувати додаток.

## Використання

**На головній сторінці можна побачити 3 кнопки для перегляду 3 списків: Продукції, Фірм та Груп продукцій.**

![image](https://github.com/kateryna-paz/Java_site/assets/111423929/05d98e9a-b4a7-4c5b-a5ca-4e8a9556ff0b)


**Натиснувши на першу кнопку, можна побачити перелік продуктів, редагувати їх, видаляти та додавати нові. Також можна здійснювати пошук за певною фірмою або групою продукції.**

![image](https://github.com/kateryna-paz/Java_site/assets/111423929/8a519ef1-d754-4828-bf5b-062a63b24410)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/645b5b6d-6fb7-4265-bd96-fc2ba62b08a7)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/d36394d6-3663-4b84-8053-2c768c3554df)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/2119bfb0-7ba6-491e-bdfc-3d0bb7a3861e)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/7d7fc78d-3616-45ab-aea4-d61bbcdafde1)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/e0d7f81c-d6e6-416a-9c81-a184d2c2a8d0)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/3331c5e7-6b4f-4a94-82e1-8c8ccd0dc291)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/722f71c5-742a-4138-8b42-cb054f9ac62e)


**Аналогічно, можна побачити перелік груп та фірм продукції, додавати їх та видаляти записи.**

![image](https://github.com/kateryna-paz/Java_site/assets/111423929/2f4d965f-3218-4a6a-9d2e-e42b804d441d)
![image](https://github.com/kateryna-paz/Java_site/assets/111423929/932d0ac6-64ad-4e06-9cf5-85965ee82ff1)




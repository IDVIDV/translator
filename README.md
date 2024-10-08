# Описание
Translator - это backend приложение, созданное в рамках прохождения вступительного теста на курс от Т-банка Java-разработчик, Осень 2024

Приложение предоставляет API для перевода текста. 
Перевод текста осуществляется с помощью запросов к стороннему ресурсу [Free Translate API](https://ftapi.pythonanywhere.com/). 
При переводе задействуется многопоточность (каждое слово переводится в отдельном потоке, количество потоков не превышает 10).

Просмотреть доступные эндпоинты можно по адресу http://localhost:8080/swagger-ui/index.html при запущенном приложении.

___
# Использованные технологии и инструменты
- Java 17
- Spring Boot
- PostgreSQL
- JDBC
- Flyway
- RestTemplate
- Maven
- Docker
- OpenAPI 

___
# Инструкция к запуску
## Способ 1
- Склонировать репозиторий
- Скачать и установить Docker Engine, Docker CLI и плагин Docker Compose
- По желанию изменить параметры в файлах .env, application.properties, compose.yaml
- В корневой папке репозитория выполнить команду `docker compose up`
- Дождаться поднятия контейнеров
- Проверить работоспособность приложения (просмотреть документацию / проверить контейнеры с помощью средств Docker / зайти в pgAdmin (http://localhost:8088))

Инорфмация из БД сохраняется при стандартных параметрах в папку `data` в корне репозитория.

## Способ 2
Смотреть вкладку Releases

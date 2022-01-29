## Запуск проекта
### С Docker
Создание docker image:
```
    docker image build -t test .
```
Запуск:
```
    docker run --name test-container -d --rm test
```

### Локально
Сборка:
```
    mvn clean install -DskipTests
```
Запуск:
```
    java -jar target/TestProject-1.0-SNAPSHOT-jar-with-dependencies.jar
```
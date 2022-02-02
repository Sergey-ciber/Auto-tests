FROM maven:3.8.4-openjdk-11
#Создаем директорию на виртуальной машине
RUN mkdir test
# Устанавливаем рабочую директорию
WORKDIR /test
#Копирую все java файлы на виртуальную машину
COPY . /test
#Запускаю мавен для скачивания зависимостей
RUN mvn clean install -DskipTests
#Запуск jar
CMD java -jar target/TestProject-1.0-SNAPSHOT-jar-with-dependencies.jar
# flightsTimeCounter
Программа читает файл tickets.json и
рассчитает:
- среднее время полета между городами Владивосток
и Тель-Авив
- 90-й процентиль времени полета между городами
Владивосток и Тель-Авив
Выводит полученный результат в консоль и записывает в текстовый файл output.txt 

Для сборки  в IntelliJ Idea нужно выполнить assembly:single. 
Полученный jar файл запускается через командную строку с помощью команды java -jar путь_к_файлу/flightsTimeCounter-1.0-SNAPSHOT-jar-with-dependencies.jar.
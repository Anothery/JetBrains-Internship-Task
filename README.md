# JetBrains Internship Task
Задание на стажировку
REST сервис, работающий со Stepik API и кэширующий ответы.
Кэшируются шаги (массив шагов), обновлённые в текущем месяце хотя-бы 1 раз.

Пример:
`http://localhost:8080/api?lesson=500`

Ответ:
`[2551,2573,2580,2582,2583,2606,2609,2611,2612,2621,2642,2673]`

Для запуска клонируйте репозиторий, перейдите в корневую папку и введите в консоли:
'mvn spring-boot:run'

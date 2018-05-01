#Test RestFull Service for Individuals

##Wrote on springBoot, maven
##All data stored inside the application

###Full description of task (in Russian, because I'm too ;azy for translation)

Для нужд районной поликлиники необходимо разработать сервис по учету обслуживаемых физических лиц (ФЛ).
Сервис должен предоставлять 3 метода: создание, поиск и редактирование (замена) данных ФЛ.
Набор данных ФЛ:
- СНИЛС
- ФИО (одной строкой)
- дата рождения

Для упрощения реализации хранение данных in memory, с помощью классов, предоставляемых Java SE,
без использования каких-либо посторонних систем хранения данных.

Поиск только по СНИЛС.

По каждому 100-му факту изменения хранимых сервисом данных должен запускаться некий долгий и ресурсоемкий процесс
(например это может быть формирование и отправка какого-то отчета, бекап и т. п.).
Такой процесс нужно имитировать вызовом Thread.sleep( 60000 ).

В качестве результата нужно предоставить законченный Maven-проект (напр. ссылка на GitHub).
Пояснения в коде, почему выбрано именно такое решение - приветствуются.

Ограничения:
- внешний интерфейс сервиса HTTP/REST, используемая платформа — Spring
- Java SE любой версии
- Spring (Spring Boot) любой версии
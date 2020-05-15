***
HW-01
---

Написать манифесты для деплоя в k8s для сервиса из прошлого ДЗ. 
- Манифесты должны описывать сущности Deployment, Service, Ingress. 
- В Deployment обязательно должны быть указаны Liveness, Readiness пробы. 
- Количество реплик должно быть не меньше 2. 
- Image контейнера должен быть указан с Dockerhub. 
- В Ingress-е должно быть правило, которое форвардит все запросы с /otusapp/* на сервис с rewrite-ом пути. 
- Хост в ингрессе должен быть arch.homework. 
- В итоге после применения манифестов GET запрос на http://arch.homework/otusapp/health должен отдавать {“status”: “OK”}. 
- На выходе предоставить ссылку на github c манифестами. 
- Манифесты должны лежать в одной директории, так чтобы можно было их все применить одной командой kubectl apply -f .
***

Installing & Testing: 
~~~
kubectl apply -f deploy/ingress/
curl -H 'Host: arch.homework' http://$(minikube ip)/otusapp/health
~~~

***
HW-02
---

+ Добавить к развернутому приложению БД.
+ Сделать простейший RESTful CRUD по созданию, удалению, просмотру и обновлению пользователей.
Пример API - https://app.swaggerhub.com/apis/otus55/users/1.0.0
+ Добавить базу данных с persistent volume для приложения.
+ Docker-Image базы данных должен использоваться из официального докер-репозитория.
+ Под с БД должен запускаться StatefulSet-ом с количеством реплик - 1.
+ Конфигурация приложения должна хранится в Configmaps.
+ Доступы к БД должны храниться в Secrets.
+ Первоначальные миграции должны быть оформлены в качестве Pod-ы (или Job-ы).
+ Ingress-ы должны также вести на url arch.homework/otusapp/* (как и в прошлом занятии)

На выходе должны быть предоставлена
1) ссылка на директорию в github, где находится директория с манифестами кубернетеса
2) команда kubectl apply -f, которая запускает в правильном порядке манифесты кубернетеса.
3) Postman коллекция, в которой будут представлены примеры запросов к сервису на создание, получение, изменение и удаление пользователя. 
Запросы из коллекции должны работать сразу после применения манифестов, без каких-то дополнительных подготовительных действий.

Задание со звездочкой (необязательное, но дает дополнительные баллы):
- +3 балла за шаблонизацию приложения в helm 3 чартах
- +2 балла за использование официального helm чарта для БД и подключение его в чарт приложения в качестве зависимости.

Installing & Testing: 
~~~
helm install my deploy/helm-dep/hello-chart/

# Configure your etc host in MacOS by executing:
# echo "$(minikube ip) arch.homework" | sudo tee -a /etc/hosts

newman run Otus_Software_Architect.postman_collection.json
~~~



***
HW-03
---

+ Инструментировать сервис метриками и алертами.
+ Инструментировать сервис из прошлого занятия метриками в формате Prometheus с помощью библиотеки для вашего фреймворка и ЯП.

+ Сделать дашборд в Графане, в котором были бы метрики с разбивкой по API методам:
  1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
  2. RPS
  3. Error Rate - количество 500ых ответов

+ Добавить в дашборд графики с метрикам в целом по сервису, взятые с nginx-ingress-controller:
  1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
  2. RPS
  3. Error Rate - количество 500ых ответов

+ Настроить алертинг в графане на Error Rate и Latency.

На выходе должно быть:
1) хелм чарт или директория с манифестами для запуска приложения с нуля. В этой же директории должны быть servicemonitor-ы. В хелм чарт в качестве зависимостей не надо устанавливать nginx-ingress-controller и прометеус-оператор. Считаем, что они уже в кубике установлены.
В случае использования хелма без шаблонизации, отдельно про это написать и указать команду установки через хелм и имя релиза.
2) в этой же директории dashboard.yaml - манифест с конфигмапом дашборды для графаны, в формате, который умеет автоматически применять prometheus-operator
3) отдельно stresstest.yaml - манифсет с Job-ой, которая производит стабильную (не больше 20 и не меньше 5 рпс), нагрузку на все API методы, в бесконечном цикле. Для нагрузки надо делать запросы на ingress-controller, передавая значение имени сервиса, на котором живет ингресс-контроллер в переменной окружения, и в случае использования helm в качестве value-значения.
4) скриншоты дашборды в момент стресс-тестирования сервиса. Например, после 5-10 минут нагрузки.


Задание со звездочкой (+5 баллов)
+ Инструментировать базу данных с помощью экспортера для prometheus для этой БД.
+ Добавить в общий дашборд графики с метриками работы БД.

+ Используя существующие системные метрики из кубернетеса, добавить на дашборд графики с метриками:
  1. Потребление подами приложения памяти
  2. Потребление подами приолжения CPU
---
[Optional] Configuring cluster:
   
    minikube start --vm-driver=virtualbox
    eval $(minikube docker-env)
    kubectl create namespace monitoring
    kubectl config set-context --current --namespace=monitoring

[Optional] Install prometheus & nginx:

    helm install prom stable/prometheus-operator -f monitoring/prometheus.yaml --atomic
    helm install nginx stable/nginx-ingress -f monitoring/nginx-ingress.yaml --atomic
    
    **Looking in browser:**
    kubectl port-forward service/prom-grafana 9000:80
    kubectl port-forward service/prom-prometheus-operator-prometheus 9090

Create Grafana monitoring dashboard:

    kubectl apply -f monitoring/dashboard.yaml

[Optional] Configure etc hosts:

    minikube ip
    sudo vim /etc/hosts
    
Testing:

    START TESTING:
    kubectl apply -f deploy/testing/stresstest.yaml
    
    STOP TESTING:
    kubectl delete -f deploy/testing/stresstest.yaml
    
    PAST TESTING SCREENSOTS:
    monitoring/screenshoots

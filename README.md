## *Lecko test*
* ***sujet 1: msgdata***   
Interroger l’API Microsoft Graph pour récupérer les mails et les sauvegarder dans une BDD.

* ***sujet 2: indicator-ui***   
Afficher l’évolution du nombre de mails envoyés sur un graphe.

## Application *msgdata*
La récupération de mails et leurs sauvegarde en BDD, ce fait au lancement de l'application.   
Une meilleur approche sera de céer un **endpoint**, permettant de récupérer les mails en spécifiant le **clientId**, **clientSecret** et le **tenant** via une interface administrateur.

| service | rôle |
--------- |----- |
|**EmailService** | *insérer un mail dans la base de données et récupérer le nombre de mail envoyés par date.* |
|**GraphAPIService** | *intéroger l'API Microsoft Graph pour récupérer les mails; dépend du service **EmailService*** |

URL = **/api/v1**

| endpoint | description |
---------- |------------ |
| **/emails/number-of-emails-evolution** | *récupérer le nombre de mails envoyés par date.*

## *Configuration*
Ajout du ficher **.env** à la racine du projet **msgdata**
```
# DB
DB_USERNAME=
DB_PASSWORD=
DB_NAME=
DB_AUTH=admin
DB_PORT=27017
DB_HOST=127.0.0.1
DB_URI=mongodb://${DB_USERNAME}:${DB_PASSWORD}@${DB_HOST}:${DB_PORT}/${DB_AUTH}

# microsoft graph api
GRAPH_API_CLIENT_ID=
GRAPH_API_CLIENT_SECRET=
GRAPH_API_TENANT=
```

## *Utilisation*   
```console
$ cd msgdata
$ docker compose up -d

# linux
$ ./mvnw spring-boot:run

# windows
$ mvnw spring-boot:run

$ cd indicator-ui
$ npm install
$ npm run dev
```

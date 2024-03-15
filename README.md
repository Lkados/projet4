# ChâTop: Optimisez vos Opérations de Chat et de Location

## Description
L'API ChâTop est une application Spring Boot qui fournit une API RESTful pour la gestion des opérations de chat et de location.

## Prérequis

Avant de commencer, assurez-vous d'avoir rempli les conditions préalables suivantes :
- Vous avez installé la dernière version de Java et Maven.
- Vous avez un serveur MySQL en cours d'exécution. Sinon, vous pouvez le télécharger [ici](https://dev.mysql.com/downloads/installer/).

## Installation et Lancement Local

### 1. Clonez le dépôt :

```bash
git clone LIEN_SSH_OU_HTTP_ICI
```

Accédez au répertoire du projet :

```bash
cd chatop_api
```
Initialisez Votre Base de Données MySQL :

Exécutez la commande suivante dans votre terminal :

```bash
$ mysql -u username -p.
mysql> CREATE DATABASE my_database;
mysql> USE my_database;
mysql> SOURCE /chemin/vers/chatop.sql.
```
Note : Remplacez username par votre nom d'utilisateur MySQL.

Configurez les Propriétés de Votre Application

Mettez à jour le fichier application.properties avec les détails de votre serveur, de votre base de données et de votre sécurité. Vous pouvez soit utiliser la configuration du projet dans IntelliJ, soit modifier directement le fichier application.properties.

```bash
server.port=${SERVER_PORT} # par exemple, 3000
server.url=${SERVER_URL} # par exemple, http://localhost:3000

# Utilisez l'URL, le nom d'utilisateur et le mot de passe de votre serveur MySQL
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.key=${JWT_KEY} # Une chaîne aléatoire et sécurisée (UUID recommandé)
client.url=${CLIENT_URL} # par exemple, http://localhost:4200
springdoc.swagger-ui.oauth.clientSecret=${JWT_KEY} # La même chaîne aléatoire et sécurisée (UUID recommandé) que jwt.key
```

Installez les Dépendances.

```bash
mvn clean install
```

Lancez l'Application

```bash
mvn spring-boot:run
```

Une fois le serveur démarré localement, accédez aux points d'API à l'adresse http://localhost:3000/api/. Remplacez 3000 par le port de serveur configuré.

### Documentation Swagger

Après le lancement de l'application localement, accédez à la documentation Swagger à l'adresse http://localhost:3000/swagger-ui/index.html. Encore une fois, remplacez 3000 par le port de serveur configuré.

À partir de là, vous pouvez explorer toutes les routes qui ne nécessitent pas d'authentification.

Pour accéder aux routes authentifiées :

Connectez-vous avec l'utilisateur de test ou enregistrez un nouveau utilisateur et connectez-vous.

Copiez le jeton JWT retourné, cliquez sur le bouton autoriser dans Swagger UI, collez le jeton dans le formulaire et autorisez.

Vous aurez maintenant accès aux routes authentifiées jusqu'à ce que vous vous déconnectiez.

Note : Vous pouvez trouver les identifiants de l'utilisateur de test dans le script chatop.sql et également dans la collection Postman.

Essayez-le !

Pour tester les routes de l'application :

Utilisez Swagger UI, l'application frontend Angular, Postman, ou les trois !
Installez l'application frontend Angular correspondante et connectez-vous avec l'utilisateur de test.
Importez la collection Postman dans Postman et suivez les instructions de la description pour configurer votre variable jeton JWT.
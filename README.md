# TheTribunal

Videogame Rating System

## About

The Tribunal is a Videogame Rating System. Users can rate a game once they have finished a game. Users and guests can
see the ratings of a user and the average scores of a game or a studio.

### Public funcionalities

- Search for an user, a game or a studio.
- See the information of an user, a game or a studio.
- See the current most popular games.
- Create a new account.
- Log-in into an account.

### Private functionalities

- See a custom feed based on your followed studios and users.
- Post or delete a review of a game.
- Modify the account.
- Change the style of the webpage.
- Follow and unfollow another user.
- Follow and unfollow a studio.
- Log-out from the account.

### Admin (Private) funcionalities

- Create a new game or a studio.
- Delete a game or a studio.
- Ban an account.
- Delete a review.

## Database entities

- **User:** defines an user's account. Contains the username, the password's hash, the name and information about the
  user and the user's configuration. It also defines whether a user is an admin.
- **Studio:** defines a game studio. Contains the name of the studio, a small description about them, the amount of
  employees and the location of their headquarters. A studio can have worked in several **games**.
- **Game**: defines a studio's product. Contains the name, a small description, the studio that has created it and the
  release date. A game can have zero or several **reviews**.
- **Review**: defines a game's review. Contains the game, the user that created the review, the review date, the score
  and an optional comment.
- **UsersFollow**: defines an user's follow. It is defined by the following user and the followed user.
- **StudioFollow**: defines a studio's follow. It is defined by the following user and the followed studio.

## Web Server's functionalities

- Provides a way to create, log-in and log-out from an account.
- Provides a feed to be shown to the user. If the user is logged-in, it may be a custom feed.
- Provides a way to search for games and studios.
- Provides a way for the user to create or delete a review for a game.
- Provides a way for the user to follow or unfollow another user or a studio.
- Provides a way to configure an user's account and its settings.
- Provides a way to administrate the webpage.

## Internal Server's functionalities

- Provides the generation of the most popular games feed.
- Provides the notification system, allowing to send notifications about follows to the offline users.

## Installation on Ubuntu 20.04 LTS (English)

### Step 1: configure the virtual machine.

- Update the package manager executing 'sudo apt update'
- Install java executing 'sudo apt install openjdk-17-jre'
- Install MySQL executing 'sudo apt install mysql-server'
- Start the MySQL service executing 'sudo service mysql start'
- Configure MySQL executing 'sudo mysql_secure_installation'. An installation wizard will be open.
- Enter the MySQL console executing 'sudo -u root -p'
- Create a new database executing 'create database <database_name>;'
- Create a new user executing 'CREATE USER '<database_user>'@'localhost' IDENTIFIED BY '<database_password>';'
- Add permissions to the new user executing 'GRANT ALL PRIVILEGES ON `<database_name>` . * TO '<database_user>'
  @'localhost';'
- Execute 'exit' to exit the MySQL console.

### step 2: compile the application.

- Clone all three projects in different folders.
- Execute 'gradlew build' ('sudo bash gradlew build') in the root of all three project folders. Gradle will generate the
  binaries at build/libs.

### Step 3: run TheTribunal

- make sure the folder /run/screen has the required permissions executing 'sudo chmod 777 /run/screen'
- Execute 'screen -S TheTribunal' to open a new shell.
- Go to the folder where the main application's binary is located.
- Create a new file named 'application.properties' with the following parameters:

```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<database_user>
spring.datasource.password=<database_password>
server.ssl.key-store=<keystore_path>
server.ssl.key-store-password=<keystore_password>
```

- If you don't have a keystore, you can use the keystore located inside the binary (not recommended).

```properties
server.ssl.key-store=classpath:keystore.jks
server.ssl.key-store-password=password
  ```

- Run the binary executing 'java -jar <PATH.jar> --spring.jpa.hibernate.ddl-auto=create' Delete the parameter '
  spring.jpa.hibernate.ddl-auto' if you don't have to create the database's tables.
- Exit the shell using 'Ctrl+A' and then 'D'.

### Step 4: run TheTribunalEmail

- Execute 'screen -S TheTribunalEmail' to open a new shell.
- Go to the folder where the service's binary is located.
- Create a new file named 'email.properties' with the following parameters:

```properties
spring.mail.username=<application_email>
spring.mail.password=<application_email_password>
```

- Run the binary executing 'java -jar <PATH.jar>'
- Exit the shell using 'Ctrl+A' and then 'D'.

### Step 4: run TheTribunalFeed

- Execute 'screen -S TheTribunalFeed' to open a new shell.
- Go to the folder where the service's binary is located.
- Run the binary executing 'java -jar <PATH.jar>'
- Exit the shell using 'Ctrl+A' and then 'D'.

## Instalación en una máquina virtual Ubuntu 20.04 LTS (Spanish)

### Paso 1: preparar la máquina virtual.

- Actualizar el gestor de paquetes usando 'sudo apt update'
- Instalar Java usando el comando 'sudo apt install openjdk-17-jre'
- Instalar MySQL usando el comando 'sudo apt install mysql-server'
- Inicializar el servicio MySQL usando el comando 'sudo service mysql start'
- Configurar MySQL usando el comando 'sudo mysql_secure_installation'. Se abrirá un pequeño script para configurar los
  aspectos básicos de la base de datos.
- Acceder a MySQL usando el comando 'sudo -u root -p'
- Crear una base de datos usando el comando 'create database <database_name>;'
- Crear un usuario usando el comando 'CREATE USER '<database_user>'@'localhost' IDENTIFIED BY '<database_password>';'
- Dar permisos al usuario para usar la base de datos usando el comando: 'GRANT ALL PRIVILEGES ON `<database_name>` . *
  TO '<database_user>'@'localhost';'
- Usar el comando 'exit' para salir de MySQL.

Paso 2: compilar la aplicación.

- Clonar los tres proyectos en carpetas separadas.
- Ejecutar el script 'gradlew build' ('sudo bash gradlew build') en el directorio raiz de cada proyecto. Gradle generará
  automáticamente los binarios en build/libs.

### Paso 3: ejecutar TheTribunal.

- Asegurarse que la carpeta /run/screen tiene los permisos necesarios con 'sudo chmod 777 /run/screen'
- Ejecutar el comando 'screen -S TheTribunal' para abrir una nueva terminal.
- Dirigirse a la carpeta donde el binario de la aplicación principal está situado.
- Crear un archivo 'application.properties' e insertar los siguientes parámetros:

```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<database_user>
spring.datasource.password=<database_password>
server.ssl.key-store=<keystore_path>
server.ssl.key-store-password=<keystore_password>
```

- Si no se tiene una keystore, se puede usar la keystore integrada en el binario (no seguro):

```properties
server.ssl.key-store=classpath:keystore.jks
server.ssl.key-store-password=password
  ```

- Ejecutar el binario usando el comando 'java -jar <PATH.jar> --spring.jpa.hibernate.ddl-auto=create'. Eliminar el
  parámetro 'spring.jpa.hibernate.ddl-auto' si no es necesario crear las tablas.
- Salir de la terminal usando la combinación de teclas 'Ctrl+A' y luego pulsando 'D'.

### Paso 4: ejecutar TheTribunalEmail.

- Ejecutar el comando 'screen -S TheTribunalEmail' para abrir una nueva terminal.
- Dirigirse a la carpeta donde el binario del servicio está situado.
- Crear un archivo llamado 'mail.properties' e insertar los siguientes parámetros:

```properties
spring.mail.username=<application_email>
spring.mail.password=<application_email_password>
```

- Ejecutar el binario usando el comando 'java -jar <PATH.jar>'
- Salir de la terminal usando la combinación de teclas 'Ctrl+A' y luego pulsando 'D'.

### Paso 5: ejecutar TheTribunalFeed.

- Ejecutar el comando 'screen -S TheTribunalFeed' para abrir una nueva terminal.
- Dirigirse a la carpeta donde el binario del servicio está situado.
- Ejecutar el binario usando el comando 'java -jar <PATH.jar>'
- Salir de la terminal usando la combinación de teclas 'Ctrl+A' y luego pulsando 'D'.

## Diagrams

![ER](/pictures/the_tribunal_er.png)
![UML](/pictures/uml_properties.png)
![UML Properties](/pictures/uml_relations.png)

## Web layout

### Main page

![Main Page](/pictures/pages/main.png)

The webpage that welcomes us to The Tribunal. The navbar will be present in every place of the application.

### Ranking

![Ranking](/pictures/pages/ranking.png)

Here you can see the best games in the application. Here you can see the average score of the game. The average is
calculated using the score of every review of the game.

### Search

![Search](/pictures/pages/search.png)

You can use the search bar to search for games, users and studios. The result will appear in a list of hyperlinks.

### Game information

![Game information](/pictures/pages/game.png)

Here you can see the information about a game and its reviews. You can also create a new review or delete the game.

### Add review

![Add review](/pictures/pages/review.png)

Here you can add a new review for a game. You must provide a comment and a score.

### Studio information

![Studio information](/pictures/pages/studio.png)

Here you can see the information about a studio. You can follow the studio.

### User information

![User information](/pictures/pages/user.png)

Here you can see the information about a user. You can follow the user.

### Login

![Login](/pictures/pages/login.png)

Here you can log in into your account. This functionality is not yet fully implemented.

### Signup

![Signup](/pictures/pages/signup.png)

Here you can create a new user. This functionality is not yet fully implemented.

## Flow diagram

![Flow](/pictures/pages/diagram.png)

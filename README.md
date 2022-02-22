``# TheTribunal

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

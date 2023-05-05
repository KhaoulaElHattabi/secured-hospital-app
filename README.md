##  Activité Pratique N° 4 : Spring Security
L'objectif de cette activite est securiser l'acces à l'application web avec un systeme d'authetification, ce projet est une suite de l'activité  3  https://github.com/KhaoulaElHattabi/patients-mvc.git
- Pour atteindre cet objectif on a adopté plusieurs stategie comme: InlineMemoryAuthentication, JDBCauthentication et UserDetail en utilisant une methode stateless lors de l'authentification du user dans le browser et cette methode sert à la generation et la verification d'un JWToken et aussi arreter les failles de securité (CSRF) par un CSRF synchronized Token qui est un champ caché dans le formulaire envoyé contient un token et ce dernier sera vérifié par le service pour assurer sa correspondance avec l'autre token qui se trouve dans la session  
- Commençant cette activité par la stratégie *InlineMemoryAuthentication*

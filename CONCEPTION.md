#Conception 
#étape 1

Nous avons modifié la méthode register actor en lui faisant admettre comme paramètre une ellipse afin de pouvoir ajouter tous les acteurs en une ligne de code dans les Aires de jeu plus complexes dans la suite du projet.

#étape 4

Lors de l'étape 4, nous avons choisi de faire des classes abstraites Collectable et Switch représentant respectivement les objets à ramasser tels que les pommes ou les clefs et les boutons ayant vocation à s'activer et se désactiver. 
Ces deux classes héritent de la classe AreaEntity puisque nous considèrons qu'elles nécessitent aussi bien l'une que l'autre une aire d'appartenance ainsi q'une position au sein de cette aire, et que ces objets ont vocation à être des interactables.

Nous avons choisi de rendre les portes dont le signal est désactivé non traversable plutôt qu'insensibles aux interactions(il nous semblait faire plus de sens de procéder ainsi)


Toutefois nous avons par la suite décidé de diviser les switchs en deux catégories, les ViewSwitch, et les CellSwitch, les premiers étant des boutons acceptant les interactions à distance ( les leviers et les torches) et les seconds acceptant les interactions de contact (torches et leviers ).
Cela nous a permis de pouvoir définir les caractéristiques communes à ces objets dans des super classes abstraites. Nous avon également ressenti le besoin de les séparer aussi pour pouvoir gérer la problématique des interacrtions de contact qui se répètent à chaque update si on adopte le même patron de conception que pour les boutons à distance.

#étape 5 

Liste des extensions implémentées : Animations, Dialogues,Pause du jeu,  Suiveur, Course, Roche poussable, contrôles avancés (help et course), coffre

Animations : 

Pour les animations, première extension, nous avons choisi de faire une classe abstraite MovableAreaEntityAnimated regroupant tous nos acteurs futurs allant être animés par la suite, malheureusement nous n'avons pas trouvé d'alternative à la création d'une classe d'animé spécifique au Player car nous ne voulions pas ne plus pouvoir jouer avec le fantôme de base. 
Nous avons fait le choix de suivre le pattern proposé dans le 7.2 avec des arrays de Sprites pour chaque direction.

Dialogues : 

Pour les dialogues, nous nous sommes contentés d'utiliser la classe fournie pour afficher un texte guidant l'utilisateur dans certaines phase du jeu. 

Suiveur : 

Nous avons également fait un petit compagnon capable de suivre le héros pricipal, nous avons décidé de le mettre en attribut de ce dernier, ainsi pour éviter de casser l'encapsulation réalisée auparavant, c'est le héros qui dicte la conduite à son suiveur et non le suiveur qui a accès aux informations du héros.

Courir : 

Pour faire courir notre personnage nous avons fait le choix d'une implémentation relativement naïve passant simplement par le nombre de FramesForMove de notre player, de ce fait le joueur est capable de se déplacer plus vite lorsque le nombre de frames par mouvement est réduit et les animations s'adaptent automatiquement à la vitesse du joueur

Pause :
 
Pour mettre le jeu en pause, nous avons ici aussi pris une approche directe en arrêtant simplement de mettre à jour les acteur sans pour autant arrêter de les dessiner et de faire apparaître une icône de pause en bas de la fenêtre pour montrer à l'utilisateur que le jeux est bien en pause. tout se mécanisme s'active donc par le biais d'un simple boolean modifié  par la pression d'une touche qui bloque la mise à jour des acteurs et permet au sprite pause d'être dessiné en bas à gauche de l'écran

MovableRock : 

Pour revenir à nos débuts (étape 1), nous avons implémenté cette classe  MovableRock qui est la seule à implémenter l'interface Interactor avec le joueur principal. Cette classe dispose donc de son propre Handler lui permettant de gérer les interactions avec les plaques de pression

Points de vie : 

Nous avons également doté notre personnage d'un attribut de la classe Vie, cette classe à pour but d'afficher un texte de la classe Textgraphics qui sera accroché au joueur et de gérer l'évolution de ce texte ainsi que celle de la variable qu'elle contient ( le nombre de points de vie) . Nous avons procédé ainsi pour que les interactions du joueur avec les différents autres acteurs puissent facilement avoir un impact sur sa vie. Pour ce faire il suffit simplement de doter les acteurs voulu d'un entier en attribut  indiquant le nombre de dommages qu'ils infligent et de faire un constructeur par défaut et un constructeur précisant cette valeur si nécessaire. Pour accompagner cette fonctionalité nous avons créé une classe health potion rendant des points de vie à notre personnage et fait que pousser un rocher fasse perdre des points de vie à notre héros.
Par ailleurs, lorsque le personnage meurt, le jeu recommence au début.

Coffre : 

Faute de temps pour cette extension, sa conception est loin d'être optimale, la classe Safe hérite d'AreaEntity et implémente l'interface logic, un coffre prend en attribut un signal qui indique s'il peut être dévérouillé ou non et renvoie par sa fonction is on un boolean qui indique si le joueur a interagi avec lui ou non. Malheureusement nous n'avons pas trouvé d'autre moyen de faire apparaître des objet qu'en modifiant la classe abstraite collectable et en lui rajoutant un logic qui implique que les objets ramassables sont lié par défaut avec un Logic.True sauf si on leur passe en argument un coffre. On retourne alors le boolean isOn() de ce logic pour savoir si l'objet est traversable et si il accepte les interactions. Il faut cependant aller dans chaque classe pour changer la méthode draw et faire que l'acteur ne soit dessiné que si le coffre est ouvert. 




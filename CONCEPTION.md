#Conception 
#étape 1

Nous avons modifié la méthode register actor en lui faisant admettre comme paramètre une ellipse afin de pouvoir ajouter tous les acteurs en une ligne de code dans les Aires de jeu plus complexes dans la suite du projet.

#étape 4

Lors de l'étape 4, nous avons choisi de faire des classes abstraites Collectable et Switch représentant respectivement les objets à ramasser tels que les pommes ou les clefs et les boutons ayant vocation à s'activer et se désactiver. 
Ces deux classes héritent de la classe AreaEntity puisque nous considèrons qu'elles nécessitent aussi bien l'une que l'autre une aire d'appartenance ainsi q'une position au sein de cette aire, et que ces objets ont vocation à être des interactables.

Toutefois nous avons par la suite décidé de diviser les switchs en deux catégories, les ViewSwitch, et les CellSwitch, les premiers étant des boutons acceptant les interactions à distance ( les leviers et les torches) et les seconds acceptant les interactions de contact (torches et leviers ).
Cela nous a permis de pouvoir définir les caractéristiques communes à ces objets dans des super classes abstraites. Nous avon également ressenti le besoin de les séparer aussi pour pouvoir gérer la problématique des interacrtions de contact qui se répètent à chaque update si on adopte le même patron de conception que pour les boutons à distance.

#étape 5
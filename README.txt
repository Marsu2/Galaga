  BARBIER Elouan
  ESNAULT Damien

Voici les fonctionnalités implémentées dans votre projet Galaga :

Système du jeu
Boucle de jeu en temps réel (30ms par frame)
Canvas de jeu 700x700 pixels
Gestion des états de jeu (en cours, victoire, défaite)

Entity :
Classe-Mère de Player et de Enemy

 Player
Limitation à 3 missiles simultanés à l'écran
Système de points de vie (3 HP max)
Système de respawn avec arrêt de jeu pendant le respawn
Affichage visuel des HP restants en bas de l'écran
Clignotement du HP en cours de respawn
Repositionnement au centre après avoir été touché

 Enemy
Types d'ennemis :
Bee : mouvement en zigzag avec changement de cible aléatoire
Butterfly : mouvement vertical en descente
Moth : mouvement vertical puis capture d’un vaisseau
Boss : tire 2 missiles côte à côte simultanément + 15 HP
Comportements :
Mouvement en formation horizontale (gauche/droite)
Mode solo : les ennemis peuvent quitter la formation pour attaquer individuellement
Tir automatique de missiles vers le joueur selon la configuration du niveau
Gestion des collisions joueur/ennemis 
Cooldown de tir configurable par niveau
Les ennemis ne tirent que s'il n'y a pas d'allié en dessous
Attribution de points à la destruction
Missile toujours présent même après la mort de l’ennemie

Missile:
Direction UP (joueur) ou DOWN (ennemis)
Collision avec les entités
Suppression automatique quand hors écran

Level :
3 niveaux avec difficulté croissante
Chargement dynamique depuis des fichiers .lvl
Configuration par niveau :
Formation d'ennemis personnalisée
Sélection aléatoire d'ennemis pour les attaques solo (parmi ceux du bas)
Affichage du nom du niveau au démarrage
Cooldown de 3 secondes avant chaque début de niveau,
Affichage du nombre de niveaux complétés

Score
Attribution de points à la destruction d'ennemis
Affichage du score en temps réel
Sauvegarde du highscore

SpriteLoader :
Lecture de sprite .spr
Transformation du sprite en tableau de Color 
Dessine un sprite grace à un tableau de Color

LevelManager :
Gestionnaire du jeu en cours 
Coordination Player/Enemy

Gestion de fin de partie
"Game Over" en cas de défaite
"You Win!" en cas de victoire
Propositon de rejouer restart avec la barre espace
Affichage du score final
Sauvegarde du highscore à la fin

Guide d'exécution du projet :

Pour exécuter le projet, exécuter le fichier app.java dans le dossier src/engine .
Le niveau 1 se chargera automatiquement. Pour changer le niveau par défaut, il faut aller dans LevelManager.java puis dans le constructeur et modifier la valeur de l’attribut currentLevelIndex .


Description de l'interface pour jouer avec votre projet :

Déplacement horizontal avec les flèches ← et →
Tir de missiles avec la barre espace
Pour relancer après un Game Over, presser la barre espace

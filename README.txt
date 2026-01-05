  BARBIER Elouan
  ESNAULT Damien

Voici les fonctionnalités implémentées dans votre projet Galaga :

🎮 Système de jeu principal
Boucle de jeu en temps réel (30ms par frame)
Canvas de jeu 700x700 pixels
Double buffering pour un affichage fluide
Gestion des états de jeu (en cours, victoire, défaite)
🚀 Joueur (Player)
Déplacement horizontal avec les flèches ← et →
Tir de missiles avec la barre espace
Limitation à 3 missiles simultanés à l'écran
Cooldown de tir (6 frames)
Système de points de vie (3 HP max)
Système de respawn avec invulnérabilité temporaire (60 frames)
Affichage visuel des HP restants en bas de l'écran
Clignotement du HP en cours de respawn
Repositionnement au centre après avoir été touché
Reset complet du joueur entre les niveaux
👾 Ennemis
Types d'ennemis :
Bee (Abeille) : mouvement en zigzag avec changement de cible aléatoire
Butterfly (Papillon) : mouvement vertical en descente
Boss : tire 2 missiles côte à côte simultanément
Comportements :
Mouvement en formation horizontale (gauche/droite)
Mode solo : les ennemis peuvent quitter la formation pour attaquer individuellement
Tir automatique de missiles vers le joueur
Cooldown de tir configurable par niveau
Les ennemis ne tirent que s'il n'y a pas d'allié en dessous
Système de santé et de dégâts
Attribution de points à la destruction
Sortie automatique de l'écran quand détruits
🎯 Système de missiles
Direction UP (joueur) ou DOWN (ennemis)
Détection de collision précise avec les entités
Suppression automatique quand hors écran
Affichage visuel (rectangle blanc)
📊 Niveaux et progression
3 niveaux avec difficulté croissante
Chargement dynamique depuis des fichiers .lvl
Configuration par niveau :
Formation d'ennemis personnalisée
Vitesse de formation
Cooldown d'attaque
Cooldown de tir des ennemis
Sélection aléatoire d'ennemis pour les attaques solo
Affichage du nom du niveau au démarrage
Timer de démarrage (3 secondes)
Affichage du nombre de niveaux complétés
🏆 Score
Attribution de points à la destruction d'ennemis
Affichage du score en temps réel
Sauvegarde du highscore
🎨 Graphismes
Système de sprites pixelisés chargés depuis des fichiers .spr
Support de 6 couleurs : Rouge, Vert, Bleu, Jaune, Blanc, Violet
Sprites pour : joueur, abeille, papillon, boss, niveaux
Interface avec lignes de séparation (zone de jeu / zone d'infos)
🔄 Gestion de fin de partie
Écran "Game Over" en cas de défaite
Écran "You Win!" en cas de victoire
Affichage du score final
Restart avec la barre espace
Sauvegarde du highscore à la fin
⚙️ Système technique
Architecture orientée objet avec héritage (Entity → Player/Enemy)
Chargement de ressources depuis fichiers externes
Gestion des collisions joueur/ennemis et missiles/entités
Nettoyage automatique des missiles hors limites
Reset complet du jeu pour rejouer

Guide d'exécution du projet :

...

Description de l'interface pour jouer avec votre projet :
...

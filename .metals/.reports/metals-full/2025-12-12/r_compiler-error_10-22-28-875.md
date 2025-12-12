file://<WORKSPACE>/src/game/Game.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 3324
uri: file://<WORKSPACE>/src/game/Game.java
text:
```scala
package game;

import engine.StdDraw;
import game.actors.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe principale du jeu.
 * Gère la boucle de jeu, les collisions, le rendu et la logique principale.
 */
public class Game {

    /**
     * Retourne la liste des missiles ennemis.
     * 
     * @return liste des missiles ennemis
     */
    public List<Missile> getMissilesEnemies() {
        return missilesEnemies;
    }

    private Player player; // Jouer, seul éléments actuellement dans notre jeu
    private List<Missile> missilesPlayers;
    private LevelManager manager;
    private Score score;

        /**
     * Retourne la liste des missiles du joueur.
     * 
     * @return liste des missiles joueurs
     */
    public List<Missile> getMissilesPlayers() {
        return missilesPlayers;
    }

    private List<Missile> missilesEnemies;
    private List<Enemy> enemies;

        /**
     * Ajoute un missile joueur à la liste active.
     * 
     * @param m missile à ajouter
     */
    public void addMissilesPlayers(Missile m) {
        missilesPlayers.add(m);
    }

        /**
     * Ajoute un missile ennemi à la liste active.
     * 
     * @param m missile à ajouter
     */
    public void addMissilesEnemies(Missile m) {
        missilesEnemies.add(m);
    }
    /**
     * Initialise le jeu avec joueur, ennemis, score et premier niveau.
     */
    public Game() {
        player = new Player(0.5, 0.1, 0.08, 5, 0.02, this);
        enemies = new LinkedList<>();
        score = new Score();
        manager = new LevelManager(this);

        enemies = (manager.getCurrentLevel().getEnemiesFormation());

        missilesPlayers = new LinkedList<>();
        missilesEnemies = new LinkedList<>();
    }

    /**
     * Initialise le jeu avec joueur, ennemis, score et premier niveau.
     */
    private void init() {
        int canvaHeight = 700;
        int canvasWidth = 700;
        StdDraw.setCanvasSize(canvaHeight, canvasWidth);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Initialise le jeu et lance la boucle de jeu en temps réel
     */
    public void launch() {
        init();

        while (isGameRunning()) {
            StdDraw.clear(StdDraw.BLACK); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer
        }
    }

    /**
     * Condition d'arrêt du jeu
     * 
     * @return true car on n'as pas encore de conidtions d'arrêt
     */
    private boolean isGameRunning() {
        return true;
    }

    /**
     * Dessin tous les éléments du jeu
     */
    public void draw() {
        player.drawSprite();
        for (Entity e : enemies) {
            e.drawSprite();
        }
       
        StdDraw.setPenColor(StdDraw.WHITE);
        // Position du pixel de départ pour la ligne du bas
        double DOWNpx = 0 ;
        double py = 0.1;
        // Position du pixel d'arrivé pour la ligne du bas
        double pxEnd = 700 ;
        double pyEnd = 0.1;

        // On dessine kle pixel en fonction de la position
        StdDraw.line(p@@x, py,pxEnd,pyEnd);

    
        
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        player.update();
        for (Entity e : enemies) {
            e.update();
        }

        List<Missile> hiddenMissiles = new ArrayList<>();
        for (Missile m : missilesPlayers) {
            if (m.isOutOfBound()) {
                hiddenMissiles.add(m);
            }
            m.update();
        }

        for (Missile m : missilesEnemies) {
            if (m.isOutOfBound()) {
                hiddenMissiles.add(m);
            }
            m.update();
        }
        missilesPlayers.removeAll(hiddenMissiles);
        missilesEnemies.removeAll(hiddenMissiles);
        checkHit();

        
        score.saveHighscore();
    }
        /**
     * Détecte les collisions missile-ennemi et met à jour score/santé.
     */
    private void checkHit() {
        List<Missile> missilesDead = new ArrayList<>();
        List<Entity> enemiesDead = new ArrayList<>();
        for (Missile m : missilesPlayers) {
            for (Enemy e : enemies) {
                if (m.hitEntity(e)) {
                    e.setHealth(e.getHealth() - 1);
                    if (e.isDead()) {
                        enemiesDead.add(e);
                        score.addScore(e);
                    }
                    missilesDead.add(m);
                }
            }
        }
        enemies.removeAll(enemiesDead);
        missilesPlayers.removeAll(missilesDead);
    }

        /**
     * Retourne le gestionnaire de niveaux.
     * 
     * @return gestionnaire de niveaux actuel
     */
    public LevelManager getLevelManager() {
        return manager;
    }

}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:935)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:164)
	dotty.tools.pc.CachingDriver.run(CachingDriver.scala:45)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:389)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator
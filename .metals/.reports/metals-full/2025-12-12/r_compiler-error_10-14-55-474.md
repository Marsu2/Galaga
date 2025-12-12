file://<WORKSPACE>/src/game/Game.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
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
        for (Missile m : missilesPlayers) {
            m.drawSprite();
        }
        for (Missile m : missilesEnemies) {
            m.drawSprite();
        }
        for (Entity e : enemies) {
            e.drawSprite();
        }
        System.out.printf("Score : %d  || HighScore : %d", score.getScore(), score.getHighscore());
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
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:351)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:88)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:111)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator
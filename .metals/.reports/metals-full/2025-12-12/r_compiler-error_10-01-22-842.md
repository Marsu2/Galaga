file://<WORKSPACE>/src/game/Score.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1273
uri: file://<WORKSPACE>/src/game/Score.java
text:
```scala
package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import game.actors.*;

/**
 * Gère le score actuel et le highscore persistant du joueur.
 * Sauvegarde le highscore dans le fichier highscore/highscore.sc.
 */
public class Score {
    /**
     * @param: score, highscore
     * 
     **/
    private int score;
    private int highscore;

        /**
     * Initialise le score à 0 et charge le highscore depuis le fichier.
     */
    public Score() {
        this.score = 0;
        System.out.println(readHighscore());
        this.highscore = readHighscore();
    }

        /**
     * Lit le highscore depuis le fichier highscore/highscore.sc.
     * Retourne 0 si le fichier n'existe pas ou lecture échouée.
     * 
     * @return highscore lu ou 0 par défaut
     */
    private int readHighscore() {
        Path cheminDuFichier = Paths.get("highscore/highscore.sc");
        try (BufferedReader reader = Files.newBufferedReader(cheminDuFichier)) {
            String line = null;
            System.out.println("ca l:it");
            while (((line = reader.readLine()) != null)) {
                return Integer.parseInt(lin@@e);
            }
        } catch (IOException e) {
        }
        return 0;

    }

    /**
     * Ajoute le score d'un ennemi vaincu au score actuel.
     * 
     * @param e l'ennemi vaincu dont on récupère le score
     */
    public void addScore(Enemy e) {
        score += e.getScore();
    }

    /**
     * Sauvegarde le meilleur score dans le fichier.
     * 
     *
     */

    public void saveHighscore() {
        System.out.println("ca rentre dedans");
        Path cheminDuFichier = Paths.get("highscore/highscore.sc");
        try (BufferedWriter writer = Files.newBufferedWriter(cheminDuFichier)) {
            writer.write(score + "\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

        /**
     * Retourne le score actuel de la partie.
     * 
     * @return score en cours
     */
    public int getScore() {
        return score;
    }


    /**
     * Retourne le highscore persistant.
     * 
     * @return meilleur score historique
     */
    public int getHighscore() {
        return highscore;
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
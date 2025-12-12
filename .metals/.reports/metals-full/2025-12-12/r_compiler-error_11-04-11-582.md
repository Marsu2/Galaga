file://<WORKSPACE>/src/game/Level.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 0
uri: file://<WORKSPACE>/src/game/Level.java
text:
```scala
@@package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.actors.*;

/**
 * Représente un niveau de jeu chargé depuis un fichier.
 * Contient la formation d'ennemis et les paramètres de difficulté.
 */
public class Level {
    private String levelName;
    private double formationSpeed;
    private List<Enemy> enemiesFormation;
    private int attackCooldown;
    private int shootCooldown;

    /**
     * Définit le nom du niveau.
     * 
     * @param levelName nouveau nom
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Retourne la vitesse de la formation d'ennemis.
     * 
     * @return vitesse de formation
     */
    public double getFormationSpeed() {
        return formationSpeed;
    }

    /**
     * Définit la vitesse de la formation d'ennemis.
     * 
     * @param formationSpeed nouvelle vitesse
     */
    public void setFormationSpeed(double formationSpeed) {
        this.formationSpeed = formationSpeed;
    }

    /**
     * Définit la formation d'ennemis du niveau.
     * 
     * @param enemiesFormation nouvelle liste d'ennemis
     */
    public void setEnemiesFormation(List<Enemy> enemiesFormation) {
        this.enemiesFormation = enemiesFormation;
    }

    /**
     * Retourne le délai d'attaque de la formation.
     * 
     * @return cooldown d'attaque (ms)
     */
    public double getAttackCooldown() {
        return attackCooldown;
    }

    /**
     * Définit le délai d'attaque de la formation.
     * 
     * @param attackCooldown nouveau cooldown (ms)
     */
    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    /**
     * Retourne le délai de tir des ennemis.
     * 
     * @return cooldown de tir (ms)
     */
    public double getShootCooldown() {
        return shootCooldown;
    }

    /**
     * Définit le délai de tir des ennemis.
     * 
     * @param shootCooldown nouveau cooldown (ms)
     */
    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    /**
     * Retourne le nom du niveau.
     * 
     * @return nom du niveau
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Retourne la liste des ennemis du niveau.
     * 
     * @return formation d'ennemis
     */
    public List<Enemy> getEnemiesFormation() {
        return enemiesFormation;
    }

    /**
     * Charge un niveau depuis un fichier de configuration.
     * 
     * @param filePath chemin vers le fichier de niveau
     */
    public Level(String filePath) {
        loadLevel(filePath);
    }

    /**
     * Charge les données du niveau depuis un fichier texte.
     * Format : première ligne = nom vitesse attack shoot ; lignes suivantes = type
     * x y size score speed
     */
    private void loadLevel(String filePath) {

        // Lecture d'un fichier vu durant la séance du CM10
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(filePath);
        // Initialisation des ennemis
        enemiesFormation = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
            // Initalisition du niveau
            String firstLine = lines.get(0);
            String[] firstsplit = firstLine.split(" ");
            this.levelName = firstsplit[0];
            this.formationSpeed = Double.parseDouble(firstsplit[1]);
            this.attackCooldown = Integer.parseInt(firstsplit[2]);
            this.shootCooldown = Integer.parseInt(firstsplit[3]);

            for (int i = 1; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                String[] split = currentLine.split(" ");

                String type = split[0];
                double positionx = Double.parseDouble(split[1]);
                double positiony = Double.parseDouble(split[2]);
                double size = Double.parseDouble(split[3]);
                int score = Integer.parseInt(split[4]);
                double speed = Double.parseDouble(split[5]);

                switch (type) {
                    case "Moth":
                        enemiesFormation.add(new Moth(positionx, positiony, size, score, speed, shootCooldown));
                        break;
                    case "Butterfly":
                        enemiesFormation.add(new Butterfly(positionx, positiony, size, score, speed, shootCooldown));
                        break;
                    case "Bee":
                        enemiesFormation.add(new Bee(positionx, positiony, size, score, speed, shootCooldown));
                        break;
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
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
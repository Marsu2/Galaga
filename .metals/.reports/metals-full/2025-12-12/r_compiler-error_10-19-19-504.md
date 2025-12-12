file://<WORKSPACE>/src/game/actors/Entity.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/actors/Entity.java
text:
```scala
package game.actors;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Color;

import engine.StdDraw;
import game.Game;

/**
 * Classe abstraite mère de toutes les entités du jeu (joueur, ennemis).
 * Gère les propriétés communes et le rendu de sprites pixelisés.
 */
public abstract class Entity {
    protected double positionx;
    protected double positiony;
    protected double size;
    protected int health;
    protected double speed;
    protected List<Missile> missiles;
    protected Game game;

    /**
     * Crée une nouvelle entité avec ses propriétés de base.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'entité
     * @param health    points de vie initiaux
     * @param speed     vitesse de déplacement
     * @param game      référence au jeu principal
     */
    public Entity(double positionx, double positiony, double size, int health, double speed, Game game) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.size = size;
        this.health = health;
        this.speed = speed;
        this.game = game;
        this.missiles = new ArrayList<>();

    }

    /**
     * Retourne les points de vie actuels.
     * 
     * @return points de vie restants
     */

    public int getHealth() {
        return health;
    }

    /**
     * Met à jour les points de vie (jamais en négatif).
     * 
     * @param health nouveaux points de vie
     */
    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    /**
     * Retourne la position horizontale.
     * 
     * @return coordonnée x
     */
    public double getPositionx() {
        return positionx;
    }

    /**
     * Retourne la position verticale.
     * 
     * @return coordonnée y
     */
    public double getPositiony() {
        return positiony;
    }

    /**
     * Dessine le sprite spécifique de l'entité (à implémenter).
     */
    public abstract void drawSprite();

    /**
     * Met à jour l'état de l'entité (mouvement, tirs, etc.).
     */
    public abstract void update();

    /**
     * Déplace l'entité selon sa logique.
     */
    public abstract void move();

    /**
     * Fait tirer l'entité.
     */
    public abstract void shoot();

    /**
     * Vérifie si l'entité est morte.
     * 
     * @return true si health inferirieure a 0 ce quyi pose probleme
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Dessine un sprite pixelisé depuis un fichier .spr.
     * 
     * @param positionx position horizontale du sprite
     * @param positiony position verticale du sprite
     * @param size      taille du sprite affiché
     * @param fileName  chemin vers le fichier sprite (.spr)
     */

    public void drawSpriteV2(double positionx, double positiony, double size, String fileName) {
        // Lecture d'un fichier vu durant la séance du CM9
        Path p = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(p)) {
            List<String> lines = new ArrayList<>();
            String line;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
            double height = lines.size();
            double width = lines.get(0).length();

            double pixelSize = size / width;
            double startX = positionx - (size / 2);
            double startY = positiony + (size / 2);

            for (int row = 0; row < lines.size(); row++) {
                String currentLine = lines.get(row);
                for (int column = 0; column < currentLine.length(); column++) {
                    char c = currentLine.charAt(column);
                    Color color = decodeColor(c);
                    // Position du pixel actuel en se basant sur le point de départ
                    double px = startX + (column * pixelSize) + (pixelSize / 2);
                    double py = startY - (row * pixelSize) - (pixelSize / 2);

                    // On dessine kle pixel en fonction de la position
                    StdDraw.setPenColor(color);
                    StdDraw.filledSquare(px, py, pixelSize / 2);

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Convertit un caractère en couleur pour les sprites pixelisés.
     * 
     * @param c caractère représentant la couleur (R,G,B,Y,W)
     * @return couleur correspondante
     */

    private Color decodeColor(char c) {
        switch (c) {
            case 'R':
                return StdDraw.RED;
            case 'G':
                return StdDraw.GREEN;
            case 'B':
                return StdDraw.BLUE;
            case 'Y':
                return StdDraw.YELLOW;
            case 'W':
                return StdDraw.WHITE;
            default:
                return StdDraw.BLACK;
        }
    }

    /**
     * Vérifie si l'entité peut tirer (à implémenter).
     * 
     * @return true si autorisé à tirer
     */

    public abstract boolean canShoot();

    public void drawMissiles(){
        for (Missile missile : missiles) {
            missile.drawSprite();
        }
    }

    public void removeMissilesOOB(){
        List<Missile> rmMissiles = new ArrayList<>();
        for (Missile missile : missiles) {
            if(missile.isOutOfBound()){
                missile.a
            }
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
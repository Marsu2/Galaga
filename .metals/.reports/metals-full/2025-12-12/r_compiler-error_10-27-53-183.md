file://<WORKSPACE>/src/game/actors/Player.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/actors/Player.java
text:
```scala
package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Représente le joueur contrôlé par l'utilisateur.
 * Se déplace horizontalement avec les flèches et tire avec la barre espace.
 */
public class Player extends Entity {
    private int coolDownShoot;
    private int coolDownShootMax;

    /**
     * Crée un nouveau joueur avec ses paramètres initiaux.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille du vaisseau
     * @param health    points de vie initiaux
     * @param speed     vitesse de déplacement horizontal
     * @param game      référence au jeu principal
     */
    public Player(double positionx, double positiony, double size, int health, double speed, Game game) {
        super(positionx, positiony, size, health, speed, game);
        coolDownShoot = 0;
        coolDownShootMax = 7;
    }

    /**
     * Met à jour le joueur : mouvement et gestion des tirs.
     */
    public void update() {
        move();
        shoot();
        removeMissilesOOB();
        for (Missile missile : missiles) {
            missile.update();
        }
        


        if (coolDownShoot > 0) {
            coolDownShoot--;
        }
    }

    /**
     * Déplace le joueur horizontalement avec les flèches gauche/droite.
     * Respecte les limites de l'écran (0 à 1).
     */
    public void move() {
        // Si la flèche gauche est pressé
        if (StdDraw.isKeyPressed(37)) {
            if (positionx > 0) {
                positionx -= speed;
            }
        }
        // Si la flèche droite est pressé
        if (StdDraw.isKeyPressed(39)) {
            if (positionx < 1) {
                positionx += speed;

            }
        }
    }

    /**
     * Vérifie si le joueur peut tirer.
     * Limite à 3 missiles simultanés et respecte le cooldown.
     * 
     * @return true si autorisé à tirer
     */
    public boolean canShoot() {
        return missiles.size() < 3 && coolDownShoot == 0;
    }

    /**
     * Gère le tir du joueur avec la barre espace (code 32).
     * Crée un missile montant avec cooldown.
     */
    public void shoot() {
        if (StdDraw.isKeyPressed(32) && canShoot()) {
            coolDownShoot = coolDownShootMax;
            Missile m1 = new Missile(speed * 3, positionx, positiony + size / 2, EDirectionMissile.UP);
            missiles.add(m1);
        }

    }

    /**
     * Dessine le sprite du vaisseau joueur depuis le fichier ship.spr.
     */
    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/ship.spr");
        drawMissiles();
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
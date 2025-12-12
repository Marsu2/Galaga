file://<WORKSPACE>/src/game/actors/Enemy.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 881
uri: file://<WORKSPACE>/src/game/actors/Enemy.java
text:
```scala
package game.actors;

import engine.StdDraw;
import game.Game;
import game.LevelManager;

/**
 * Classe abstraite représentant un ennemi générique dans le jeu.
 * Gère le comportement de base : mouvement, tir et cooldown d'attaque.
 */
public abstract class Enemy extends Entity {
    protected int score;
    protected int coolDownShootMax;
    protected int coolDownShoot;

    /**
     * Crée un nouvel ennemi avec ses paramètres de base.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'ennemi
     * @param score     points attribués à la destruction
     * @param speed     vitesse de déplacement
     * @param game      référence au jeu principal
     */

    public Enemy(double positionx, double positiony, double size, int score, double speed, Game game) {
        this.@@
        super(positionx, positiony, size, 1, speed, game, missiles);
        this.score = score;
        this.coolDownShootMax = 0;
        this.coolDownShoot = coolDownShootMax;

    }

    /**
     * Retourne les points de l'ennemi.
     * 
     * @return points accordés à la destruction
     */
    public int getScore() {
        return score;
    }

    /**
     * Met à jour l'ennemi : mouvement, tir automatique et gestion du cooldown.
     */

    public void update() {
        move();
        if (canShoot()) {
            shoot();

        }

        if (coolDownShoot > 0) {
            coolDownShoot--;
        }

    }

    /**
     * Déplace l'ennemi selon sa logique spécifique (à implémenter).
     */

    public void move() {
    }

    /**
     * Tire un missile vers le bas (vers le joueur).
     */
    public void shoot() {
        Missile m1 = new Missile(speed * 2, positionx, positiony - size / 2, EDirectionMissile.DOWN);
        game.addMissilesEnemies(m1);

    }

    /**
     * Dessine le sprite spécifique de l'ennemi
     */

    public abstract void drawSprite();

    /**
     * Vérifie si l'ennemi peut tirer en fonction de son cooldown.
     * Initialise automatiquement le cooldown depuis le niveau courant.
     * 
     * @return true si l'ennemi peut tirer, false sinon
     */
    public boolean canShoot() {
        if (coolDownShootMax == 0) {
            coolDownShootMax = (int) game.getLevelManager().getCurrentLevel().getShootCooldown() / 100;
            coolDownShoot = coolDownShootMax;
        }
        if (coolDownShoot == 0) {
            coolDownShoot = coolDownShootMax;
            return true;
        }

        return false;
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
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:72)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:150)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator
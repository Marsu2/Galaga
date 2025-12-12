file://<WORKSPACE>/src/game/actors/Bee.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/actors/Bee.java
text:
```scala
package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Abeille ennemie qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Bee extends Enemy {

    /**
     * Crée une nouvelle abeille ennemie aux coordonnées spécifiées.
     * 
     * @param positionx position horizontale de l'abeille
     * @param positiony position verticale de l'abeille
     * @param size      taille de l'abeille
     * @param score     points accordés quand l'abeille est détruite
     * @param speed     vitesse de déplacement de l'abeille
     * @param game      référence au jeu principal
     */

    public Bee(double positionx, double positiony, double size, int score, double speed) {
        super(positionx, positiony, size, score, speed,);
    }

    /**
     * Dessine le sprite spécifique de l'abeille à sa position actuelle.
     */
    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/bee.spr");
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
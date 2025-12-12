file://<WORKSPACE>/src/game/actors/Moth.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/actors/Moth.java
text:
```scala
package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Sous class de enemy pour MOTH qui ca pouvoir attaquer
 **/
public class Moth extends Enemy {
    /**
     * Initialise un nouveau papillon de nuit ennemi.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'entité
     * @param score     points attribués à la destruction
     * @param speed     vitesse de déplacement
     * @param game      référence à l'objet principal du jeu
     */

    public Moth(double positionx, double positiony, double size, int score, double speed) {
        super(positionx, positiony, size, score, speed);
    }

    /**
     * Dessine le sprite personnalisé du papillon de nuit.
     */
    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/catcher.spr");
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
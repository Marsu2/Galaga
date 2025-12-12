file://<WORKSPACE>/src/game/actors/Butterfly.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/actors/Butterfly.java
text:
```scala
package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Papillon ennemi qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Butterfly extends Enemy {

    /**
     * Crée un nouveau papillon ennemi aux coordonnées spécifiées.
     * 
     * @param positionx position horizontale du papillon
     * @param positiony position verticale du papillon
     * @param size      taille du papillon
     * @param score     points accordés quand le papillon est détruit
     * @param speed     vitesse de déplacement du papillon
     * @param game      référence au jeu principal
     */

    public Butterfly(double positionx, double positiony, double size, int score, double speed, Game game) {
        super(positionx, positiony, size, score, speed, game);
    }

    /**
     * Dessine le sprite spécifique du papillon à sa position actuelle.
     */
    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/butterfly.spr");
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
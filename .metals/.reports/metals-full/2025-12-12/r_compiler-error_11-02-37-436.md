file://<WORKSPACE>/src/engine/App.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 141
uri: file://<WORKSPACE>/src/engine/App.java
text:
```scala
package engine;

import game.Game;

/**
 * Classe de lancement du projet
 * 
 * @author nom prenom binôme 1
 * @author nom prenom bin@@ôme 2
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Création d'un nouveau jeu et lancement de celui-ci
        // javadoc -d ../docs -sourcepath . -subpackages engine:game
        // Ligne javadoc pour faire la docu a bien faire dans src
        Game g = new Game();
        g.launch();
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
file://<WORKSPACE>/src/game/LevelManager.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/game/LevelManager.java
text:
```scala
package game;


/**
 * Gère le chargement et la progression entre les niveaux du jeu.
 * Charge dynamiquement les niveaux depuis des fichiers .lvl.
 */
public class LevelManager {
    private Level[] levels;
    private int currentLevelIndex;
    public int nbLevels = 2;

        /**
     * Initialise le gestionnaire de niveaux et charge tous les niveaux.
     * 
     * @param game référence au jeu principal
     */
    public LevelManager(Game game) {
        this.currentLevelIndex = 0;
        loadLevels(game);
    }

        /**
     * Retourne le niveau actuellement actif.
     * 
     * @return niveau courant ou null si aucun niveau
     */
    public Level getCurrentLevel() {
        if (levels.length == 0) {
            return null;
        }
        return levels[currentLevelIndex];
    }
    
        /**
     * Charge tous les niveaux depuis les fichiers ressources/levels/levelX.lvl.
     * 
     * @param game référence au jeu principal
     */
    private void loadLevels(Game game){
        this.levels = new Level[nbLevels];
        for(int i = 0; i < nbLevels; i++){
            String filePath = "ressources/levels/level" + (i+1) + ".lvl";
            this.levels[i] = new Level(filePath, game);
        }
    }

        /**
     * Passe au niveau suivant si disponible.
     * Ignore la demande si déjà au dernier niveau.
     */
    public void toNextLevel(){
        if(currentLevelIndex < nbLevels -1){
            currentLevelIndex++;
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
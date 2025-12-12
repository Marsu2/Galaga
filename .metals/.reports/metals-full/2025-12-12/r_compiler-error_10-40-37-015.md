file://<WORKSPACE>/src/game/LevelManager.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1754
uri: file://<WORKSPACE>/src/game/LevelManager.java
text:
```scala
package game;

import java.nio.file.Files;
import java.nio.file.Path;

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

    public void drawSpriteV2(double positionx, double positiony, double size, String fileName) {
        // Lecture d'un fichier vu durant la séance du CM9
        Path p = Paths.get(fileName);
        try (Buffere@@dReader reader = Files.newBufferedReader(p)) {
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
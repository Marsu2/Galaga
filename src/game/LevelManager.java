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
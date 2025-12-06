package game;

public class LevelManager {
    private Level[] levels;
    private int currentLevelIndex;
    public int nbLevels = 2;

    public LevelManager(Game game) {
        this.currentLevelIndex = 0;
        loadLevels(game);
    }

    public Level getCurrentLevel() {
        if (levels.length == 0) {
            return null;
        }
        return levels[currentLevelIndex];
    }
    
    private void loadLevels(Game game){
        this.levels = new Level[nbLevels];
        for(int i = 0; i < nbLevels; i++){
            String filePath = "ressources/levels/level" + (i+1) + ".lvl";
            this.levels[i] = new Level(filePath, game);
        }
    }

    public void toNextLevel(){
        if(currentLevelIndex < nbLevels -1){
            currentLevelIndex++;
        }
    }
}
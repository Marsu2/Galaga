package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.actors.*;

public class Level {
    private String levelName;
    private double formationSpeed;
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public double getFormationSpeed() {
        return formationSpeed;
    }

    public void setFormationSpeed(double formationSpeed) {
        this.formationSpeed = formationSpeed;
    }

    public void setEnemiesFormation(List<Enemy> enemiesFormation) {
        this.enemiesFormation = enemiesFormation;
    }

    public double getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(double attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public double getShootCooldown() {
        return shootCooldown;
    }

    public void setShootCooldown(double shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    private List<Enemy> enemiesFormation;
    private double attackCooldown;
    private double shootCooldown;

    public Level(String filePath, Game game) {
        loadLevel(filePath, game);
    }

    public String getLevelName() {
        return levelName;
    }

    public List<Enemy> getEnemiesFormation() {
        return enemiesFormation;
    }

    private void loadLevel(String filePath, Game game) {
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
            //Initalisition du niveau
            String firstLine = lines.get(0);
            String[] firstsplit = firstLine.split(" ");
            this.levelName = firstsplit[0];
            this.formationSpeed = Double.parseDouble(firstsplit[1]);
            this.attackCooldown = Double.parseDouble(firstsplit[2]);
            this.shootCooldown = Double.parseDouble(firstsplit[3]);
            //Init(ialisation des ennemis
            enemiesFormation = new ArrayList<>();
            for(int i =1; i < lines.size(); i++){
                String currentLine = lines.get(i);
                String[] split = currentLine.split(" ");
                String type = split[0];
                double positionx = Double.parseDouble(split[1]);
                double positiony = Double.parseDouble(split[2]);
                double size = Double.parseDouble(split[3]);
                int score = Integer.parseInt(split[4]);
                double speed = Double.parseDouble(split[5]);
                switch (type) {
                    case "Moth":
                        enemiesFormation.add(new Moth(positionx, positiony, size, score, speed, game));
                    case "Bee":
                        enemiesFormation.add(new Bee(positionx, positiony, size, score, speed, game));
                    case "Butterfly":
                        enemiesFormation.add(new Butterfly(positionx, positiony, size, score, speed, game));
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

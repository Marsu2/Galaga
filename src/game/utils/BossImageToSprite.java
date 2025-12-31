package game.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.awt.Color;

public class BossImageToSprite {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("ressources/sprites/boss.png"));
        int width = img.getWidth();
        int height = img.getHeight();

        FileWriter writer = new FileWriter("ressources/sprites/boss.spr");

        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < width; x++) {
                Color c = new Color(img.getRGB(x, y), true);
                if (c.getAlpha() < 128)
                    line.append('N'); // Transparent = noir
                else if (c.getRed() > 200 && c.getGreen() < 100 && c.getBlue() < 100)
                    line.append('R'); // Rouge
                else if (c.getRed() > 200 && c.getGreen() > 200 && c.getBlue() > 200)
                    line.append('W'); // Blanc
                else if (c.getBlue() > 200 && c.getRed() < 100 && c.getGreen() < 100)
                    line.append('B'); // Bleu
                else if (c.getGreen() > 200 && c.getRed() < 100 && c.getBlue() < 100)
                    line.append('G'); // Vert
                else if (c.getRed() > 200 && c.getGreen() > 200 && c.getBlue() < 100)
                    line.append('Y'); // Jaune
                else if (c.getRed() > 60 && c.getBlue() > 60 && c.getGreen() < 80 &&
                        (c.getRed() + c.getBlue()) / 2 > c.getGreen() * 2)
                    line.append('V'); // Violet
                else
                    line.append('N'); // Noir par défaut
            }
            writer.write(line.toString() + "\n");
        }
        writer.close();
        System.out.println("Conversion terminée !");
    }
}
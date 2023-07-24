/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;

public class BitmapItem extends SlideItem {

    private static final Logger logger = LogManager.getLogger("BitmapItem");
    private BufferedImage bufferedImage;
    private String imageName;

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";

    public BitmapItem(int level, String name) {
        super(level);

        imageName = name;

        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            logger.error("{}{}{}", FILE, imageName, NOTFOUND);
        }

    }

    public BitmapItem() {
        this(0, null);
    }

    public String getName() {
        return imageName;
    }

    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
        return new Rectangle((int) (myStyle.indent * scale), 0, (int) (bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
    }

    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);

        g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer);
    }

    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}

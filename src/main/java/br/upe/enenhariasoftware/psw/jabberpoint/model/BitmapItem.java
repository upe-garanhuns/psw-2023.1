/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *<p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitmapItem extends SlideItem {

    private BufferedImage bufferedImage;
    private final String imageName;

    private static final Logger logger = LoggerFactory.getLogger(BitmapItem.class);

    protected static final String FILE = "Arquivo ";
    protected static final String NOTFOUND = " não encontrado";

    public BitmapItem(int level, String name) {
        super(level);

        imageName = name;

        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            logger.error(FILE + imageName + NOTFOUND);
        }
    }

    public BitmapItem() {
        this(0, null);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getName() {
        return imageName;
    }

    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
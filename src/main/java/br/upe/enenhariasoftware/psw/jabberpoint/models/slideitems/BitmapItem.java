/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitmapItem extends SlideItem {
	private static final Logger logger = LoggerFactory.getLogger(BitmapItem.class);
	private BufferedImage bufferedImage;
	private final String imageName;

	public BitmapItem(int level, String name) {
		super(level);

		imageName = name; // na verdade representa caminho que a imagem está

		try {
			bufferedImage = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	public String getName() {
		return imageName;
	}

	@Override
	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
}

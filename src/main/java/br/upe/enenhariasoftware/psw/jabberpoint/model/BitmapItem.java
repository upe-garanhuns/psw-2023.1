/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class BitmapItem extends SlideItem {

  // Representa um item de slide que contém uma bufferedImage
  private static final Logger logger = LogManager.getLogger(BitmapItem.class);

  private BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

  // Está sendo realizado duas ações
  // 1 - Inicialização do level e imageName
  // 2 - Leitura da imagem do arquivo
  public BitmapItem(int level, String name) {
    super(level);
    imageName = name;

    readImage();
  }

  public void readImage(){
    try {
      bufferedImage = ImageIO.read(new File(imageName));
    } catch (IOException e) {
      logger.error(FILE + imageName + NOTFOUND);
    }
  }

  public BitmapItem() {
    this(0, null);
  }

  public String getName() {
    return imageName;
  }

  public int getImageWidth(ImageObserver observer, float scale) {
    return (int) (bufferedImage.getWidth(observer) * scale);
  }

  public int getImageHeight(ImageObserver observer, float scale) {
    return (int) (bufferedImage.getHeight(observer) * scale);
  }

  @Override
  public int getStyleIndent(Style myStyle, float scale) {
    return (int) (myStyle.getIndent() * scale);
  }

  @Override
  public int getStyleLeading(Style myStyle, float scale) {
    return (int) (myStyle.getLeading() * scale);
  }

  // Calcula e retorna um retângulo que delimita o bitmapItem
  // Não utiliza o parâmetro graphics
  @Override
  public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style myStyle) {
    int styleIndent = getStyleIndent(myStyle, scale);
    int imageWidth = getImageWidth(observer, scale);
    int height = getStyleLeading(myStyle, scale) + getImageHeight(observer, scale);

    return new Rectangle(styleIndent, 0, imageWidth, height);
  }

  // Creio que faz sentido esse método estar nessa classe, já que desenha a imagem de acordo com as especificações dadas
  @Override
  public void draw(int baseX, int baseY, float scale, Graphics graphics, Style myStyle, ImageObserver observer) {
    int x = baseX + getStyleIndent(myStyle, scale);
    int y = baseY + getStyleLeading(myStyle, scale);
    int width = getImageWidth(observer, scale);
    int height = getImageHeight(observer, scale);

    graphics.drawImage(bufferedImage, x, y, width, height, observer);
  }

  @Override
  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }

}

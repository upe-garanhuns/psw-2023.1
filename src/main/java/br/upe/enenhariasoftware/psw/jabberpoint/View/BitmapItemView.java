/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.View;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.StyleModel;

public class BitmapItemView extends SlideItemView {

  public static final Logger LOGGER = Logger.getLogger(BitmapItemView.class.getName());

  private transient BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

  public BitmapItemView(int level, String name) {
    super(level);

    imageName = name;

    try {
      bufferedImage = ImageIO.read(new File(imageName));
    } catch (IOException e) {
      LOGGER.warning(String.format("%s %s %s", FILE, imageName, NOTFOUND));
    }

  }

  public BitmapItemView() {
    this(0, null);
  }

  public String getName() {
    return imageName;
  }

  @Override
  public Rectangle getBoundingBox(Graphics graphic, ImageObserver observer, float scale, StyleModel myStyle) {
    return new Rectangle((int) (myStyle.getIndent() * scale), 0,
        (int) (bufferedImage.getWidth(observer) * scale),
        ((int) (myStyle.getLeading() * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
  }

  @Override
  public void draw(int x, int y, float scale, Graphics graphic, StyleModel myStyle, ImageObserver observer) {
    int width = x + (int) (myStyle.getIndent() * scale);
    int height = y + (int) (myStyle.getLeading() * scale);

    graphic.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
        (int) (bufferedImage.getHeight(observer) * scale), observer);
  }

  @Override
  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }
}

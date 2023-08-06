/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.Model;

import java.awt.Color;
import java.awt.Font;

public class StyleModel {

  private static StyleModel[] styles;

  private static final String FONTNAME = "Helvetica";
  private int indent;
  private Color color;
  private Font font;
  private int fontSize;
  private int leading;

  public static StyleModel[] getStyles() {
    return styles;
  }

  public static void setStyles(StyleModel[] styles) {
    StyleModel.styles = styles;
  }

  public int getIndent() {
    return indent;
  }

  public void setIndent(int indent) {
    this.indent = indent;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  public int getFontSize() {
    return fontSize;
  }

  public void setFontSize(int fontSize) {
    this.fontSize = fontSize;
  }

  public int getLeading() {
    return leading;
  }

  public void setLeading(int leading) {
    this.leading = leading;
  }

  public static String getFontname() {
    return FONTNAME;
  }

  public static void createStyles() {
    styles = new StyleModel[5];
    styles[0] = new StyleModel(0, Color.red, 48, 20);
    styles[1] = new StyleModel(20, Color.blue, 40, 10);
    styles[2] = new StyleModel(50, Color.black, 36, 10); // Style level 2 - Copyright - Go to File
    styles[3] = new StyleModel(70, Color.black, 30, 10); // Style level 3 - índice de navegação
    styles[4] = new StyleModel(90, Color.black, 24, 10); // Style level 4 - memo demo presentation
  }

  public static StyleModel getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[level];
  }

  public StyleModel(int indent, Color color, int points, int leading) { // Uma fonte é medida em
                                                                        // points
    this.indent = indent;
    this.color = color;
    this.fontSize = points;
    font = new Font(FONTNAME, Font.BOLD, fontSize);
    this.leading = leading;
  }

  @Override
  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }
}

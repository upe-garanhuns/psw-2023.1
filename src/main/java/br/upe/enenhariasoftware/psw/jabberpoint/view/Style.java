/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.view;

import java.awt.Color;
import java.awt.Font;

public class Style {

  private static Style[] styles;

  private static final String FONTNAME = "Helvetica";
  private int indent;
  private Color color;
  Font font;
  int fontSize;
  private int leading;



  public static void createStyles() {
    styles = new Style[5];
    styles[0] = new Style(0, Color.red, 48, 20);
    styles[1] = new Style(20, Color.blue, 40, 10);
    styles[2] = new Style(50, Color.black, 36, 10);
    styles[3] = new Style(70, Color.black, 30, 10);
    styles[4] = new Style(90, Color.black, 24, 10);
  }

  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[level];
  }

  public Style(int indent, Color color, int points, int leading) {
    this.setIndent(indent);
    this.setColor(color);
    font = new Font(FONTNAME, Font.BOLD, fontSize = points);
    this.setLeading(leading);
  }

  public String toString() {
    return "[" + getIndent() + "," + getColor() + "; " + fontSize + " on " + getLeading() + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}

public int getLeading() {
	return leading;
}

public void setLeading(int leading) {
	this.leading = leading;
}

public int getIndent() {
	return indent;
}

public void setIndent(int indent) {
	this.indent = indent;
}
}

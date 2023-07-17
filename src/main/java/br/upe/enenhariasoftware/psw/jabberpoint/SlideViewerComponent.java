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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SlideViewerComponent extends JComponent {
	
  private static final long serialVersionUID = 227L;
  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;
  
  private Slide slide;
  private Font labelFont = null;
  private Presentation presentation = null;
  private JFrame frame = null;

  public SlideViewerComponent(Presentation pres, JFrame frame) {
	setBackground(BGCOLOR);
	presentation = pres;
    labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
	this.frame = frame;
  }

  @Override
  public Dimension getPreferredSize() {
	return new Dimension(Slide.WIDTH, Slide.HEIGHT);
  }

  public void update(Presentation presentation, Slide data) {
	if (data == null) {
      repaint();
	  return;
	}
	this.presentation = presentation;
	this.slide = data;
	repaint();
	frame.setTitle(presentation.getTitle());
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
	g.fillRect(0, 0, getSize().width, getSize().height);
	
	if (presentation.getSlideNumber() < 0 || slide == null) {
	  return;
	}
	
	g.setFont(labelFont);
	g.setColor(COLOR);
	g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(), XPOS, YPOS);
	Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
	slide.draw(g, area, this);
  }

}

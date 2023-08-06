/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.SlideController;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.SlideModel;

public class SlideComponentView extends JComponent {
  private static final long serialVersionUID = 227L;

  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  private SlideController slideController;
  private Font labelFont = null;
  private PresentationModel presentation = null;
  private JFrame frame = null;

  public SlideComponentView(PresentationModel presentation, JFrame frame) {
    setBackground(BGCOLOR);
    this.presentation = presentation;
    labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(SlideModel.WIDTH, SlideModel.HEIGHT);
  }

  public void update(PresentationModel presentation, SlideModel slide) {
    if (slide == null) {
      repaint();
      return;
    }

    this.presentation = presentation;

    this.slideController = new SlideController(slide);
    repaint();
    frame.setTitle(presentation.getTitle());
  }

  @Override
  public void paintComponent(Graphics graphic) {
    graphic.setColor(BGCOLOR);
    graphic.fillRect(0, 0, getSize().width, getSize().height);

    if (presentation.getSlideNumber() < 0 || slideController.getSlide() == null) {
      return;
    }

    graphic.setFont(labelFont);
    graphic.setColor(COLOR);
    if (1 + presentation.getSlideNumber() >= presentation.getSize()) {
      graphic.drawString("Slide " + (presentation.getSize()) + " of " + presentation.getSize(),
          XPOS, YPOS);
    } else {
      graphic.drawString(
          "Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(), XPOS,
          YPOS);
    }
    Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

    slideController.getSlideView().draw(graphic, area, this);
  }

}

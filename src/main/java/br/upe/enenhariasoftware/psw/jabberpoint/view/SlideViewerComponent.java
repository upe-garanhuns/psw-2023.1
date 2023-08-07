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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.PresentationController;
import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;

public class SlideViewerComponent extends JComponent implements Serializable {
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	

	private transient Slide slide;
	private Font labelFont = null;
	private transient PresentationModel presentationModel = null;
	private JFrame frame = null;

	public SlideViewerComponent(PresentationModel pres, JFrame frame) {
		setBackground(BGCOLOR);
		presentationModel = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(PresentationModel presentationModel, Slide data) {
		if (data == null) {
			repaint();
			return;
		}

		this.presentationModel = presentationModel;
		this.slide = data;
		repaint();
		frame.setTitle(presentationModel.getTitle());
	}

	@Override
	public void paintComponent(Graphics slideGraphics) {
		slideGraphics.setColor(BGCOLOR);
		slideGraphics.fillRect(0, 0, getSize().width, getSize().height);

		if (presentationModel.getSlideNumber() < 0 || slide == null) {
			return;
		}

		slideGraphics.setFont(labelFont);
		slideGraphics.setColor(COLOR);
		slideGraphics.drawString("Slide " + (1 + presentationModel.getSlideNumber()) + " of " + presentationModel.getSize(), XPOS, YPOS);

		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

		slide.draw(slideGraphics, area, this);
	}

}

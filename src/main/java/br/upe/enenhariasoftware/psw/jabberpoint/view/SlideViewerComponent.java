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

import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideModel;

public class SlideViewerComponent extends JComponent implements Serializable {
	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

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
		return new Dimension(SlideView.WIDTH, SlideView.HEIGHT);
	}

	public void update(PresentationModel presentationModel, SlideModel data) {
		if (data == null) {
			repaint();
			return;
		}

		new SlideView(data);
		this.presentationModel = presentationModel;
		repaint();
		frame.setTitle(presentationModel.getTitle());
	}

	@Override
	public void paintComponent(Graphics slideGraphics) {
		slideGraphics.setColor(BGCOLOR);
		slideGraphics.fillRect(0, 0, getSize().width, getSize().height);

		if (presentationModel == null || presentationModel.getSlideNumber() < 0) {
			return;
		}

		slideGraphics.setFont(labelFont);
		slideGraphics.setColor(COLOR);
		slideGraphics.drawString(
				"Slide " + (1 + presentationModel.getSlideNumber()) + " of " + presentationModel.getSize(), XPOS, YPOS);

		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

		if (presentationModel.getCurrentSlide() != null) {
			SlideModel slideModel = presentationModel.getCurrentSlide();
			SlideView slideView = new SlideView(slideModel);
			slideView.draw(slideGraphics, area, this);
		}
	}
}

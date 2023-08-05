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
import javax.swing.JComponent;
import javax.swing.JFrame;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.IViewController;
import br.upe.enenhariasoftware.psw.jabberpoint.model.IPresentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.ISlide;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;

public class SlideViewerComponent extends JComponent {

    private static final long serialVersionUID = 227L;
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_HEIGHT = 10;
    private static final int X_POSITION = 1100;
    private static final int Y_POSITION = 20;

    private ISlide slide;
    private final Font labelFont;
    private IPresentation presentation;
    private final JFrame frame;
    private final transient IViewController viewController;

    public SlideViewerComponent(IPresentation presentation, JFrame frame, IViewController viewController) {
        setBackground(BACKGROUND_COLOR);
        this.presentation = presentation;
        this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT);
        this.frame = frame;
        this.viewController = viewController;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(slide.getWidth(), slide.getHeight());
    }

    public void update(IPresentation presentation, ISlide data) {
        if (data == null) {
            repaint();
            return;
        }
        this.presentation = presentation;
        this.slide = data;
        repaint();
        this.frame.setTitle(presentation.getTitle());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, getSize().width, getSize().height);

        if (presentation.getSlideNumber() < 0 || slide == null) {
            return;
        }

        graphics.setFont(labelFont);
        graphics.setColor(COLOR);
        graphics.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(), X_POSITION, Y_POSITION);
        Rectangle area = new Rectangle(0, Y_POSITION, getWidth(), (getHeight() - Y_POSITION));
        viewController.setSlide((Slide) slide);
        viewController.drawSlide(graphics, area, this);
    }
}
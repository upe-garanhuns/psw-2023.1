/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *<p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public interface SlideItemViewer {
    public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

    public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
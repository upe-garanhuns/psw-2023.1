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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextItem extends SlideItem {

	private String text;

	private static final String EMPTYTEXT = "No Text Given";

	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

	public TextItem() {
		this(0, EMPTYTEXT);
	}

	public String getText() {
		return text == null ? "" : text;
	}

	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());

		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

		return attrStr;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);

		int xsize = 0;
		int ysize = (int) (myStyle.leading * scale);

		Iterator<TextLayout> iterator = layouts.iterator();

		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();

			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}

			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}

		return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
	}

	@Override
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver o) {
		if (text == null || text.length() == 0) {
			return;
		}

		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(myStyle.color);

		Iterator<TextLayout> it = layouts.iterator();

		while (it.hasNext()) {
			TextLayout layout = it.next();

			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);

			pen.y += layout.getDescent();
		}
	}

	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<>();

		AttributedString attrStr = getAttributedString(s, scale);
		Graphics2D g2d = (Graphics2D) g;

		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

		float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

		while (measurer.getPosition() < getText().length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}

		return layouts;
	}

	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}

}

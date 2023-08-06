/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *<p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

import br.upe.enenhariasoftware.psw.jabberpoint.view.Style;

public class TextItem extends SlideItem {

    private final String text;

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

    public String toString() {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }

}
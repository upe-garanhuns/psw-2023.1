/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems;

public class TextItem extends SlideItem {
	private final String text;

	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

	public String getText() {
		return text == null ? "" : text;
	}

	@Override
	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}

}

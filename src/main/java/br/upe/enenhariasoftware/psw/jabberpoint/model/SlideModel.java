package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.view.TextItem;

public class SlideModel {
	
	protected TextItem slideTitle;
	protected ArrayList<SlideItem> items;
	
	public SlideModel() {
		items = new ArrayList<>();
	}
	
	public void append(SlideItem anItem) {
		items.add(anItem);
	}

	public String getTitle() {
		return slideTitle.getText();
	}

	public void setTitle(String newTitle) {
		slideTitle = new TextItem(0, newTitle);
	}

	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	public SlideItem getSlideItem(int number) {
		return items.get(number);
	}

	public ArrayList<SlideItem> getSlideItems() {
		return items;
	}

	public int getSize() {
		return items.size();
	}

}
/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.Model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.SlideItemController;
import br.upe.enenhariasoftware.psw.jabberpoint.View.SlideItemView;
import br.upe.enenhariasoftware.psw.jabberpoint.View.TextItemView;

public class SlideModel implements Serializable {

  private static final long serialVersionUID = 210L;

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  protected SlideItemController titleController;
  private List<SlideItemController> itemsControllers;

  public SlideModel() {
    itemsControllers = new ArrayList<>();
  }

  public void append(SlideItemView anItem) {
    SlideItemController newController = new SlideItemController(anItem);
    itemsControllers.add(newController);
  }

  public String getTitle() {

    return ((TextItemView) titleController.getSlideItem()).getText();
  }

  public void setTitle(String newTitle) {
    titleController = new SlideItemController();
    titleController.setTextItem(0, newTitle);
  }

  public void append(int level, String message) {
    append(new TextItemView(level, message));
  }

  public SlideItemView getSlideItem(int number) {
    return itemsControllers.get(number).getSlideItem();
  }

  public List<SlideItemController> getSlideItems() {
    return itemsControllers;
  }

  public int getSize() {
    return itemsControllers.size();
  }


  public float getScale(Rectangle area) {
    return Math.min(((float) area.width) / ((float) WIDTH),
        ((float) area.height) / ((float) HEIGHT));
  }

  public SlideItemController getTitleController() {
    return titleController;
  }

}

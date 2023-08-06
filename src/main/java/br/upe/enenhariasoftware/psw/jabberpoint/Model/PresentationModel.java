/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.SlideComponentController;

public class PresentationModel implements Serializable {

  private static final long serialVersionUID = 197L;

  private String title;
  private List<SlideModel> slides = null;
  private SlideComponentController slideComponentController = null;
  private int currentSlideNumber = 0;

  public PresentationModel() {
    slideComponentController = null;
    clear();
  }

  public int getSize() {
    return slides.size();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String nt) {
    title = nt;
  }

  public void setShowView(SlideComponentController slideComponentController) {
    this.slideComponentController = slideComponentController;
  }

  public int getSlideNumber() {
    return currentSlideNumber;
  }

  public void setSlideNumber(int number) {
    currentSlideNumber = number;
    if (slideComponentController != null) {
      slideComponentController.getSlideViewComponent().update(this, getCurrentSlide());
    }
  }

  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (currentSlideNumber < (slides.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  public void clear() {
    slides = new ArrayList<>();
    setSlideNumber(-1);
  }

  public void append(SlideModel slide) {
    slides.add(slide);
  }

  public SlideModel getSlide(int number) {
    if (number < 0) {
      return null;
    } else if (number >= getSize()) {
      return slides.get(getSize() - 1);
    }
    return slides.get(number);
  }

  public SlideModel getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  public void exit(int n) {
    System.exit(n);
  }
}

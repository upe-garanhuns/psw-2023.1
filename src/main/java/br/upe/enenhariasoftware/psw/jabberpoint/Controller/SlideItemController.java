package br.upe.enenhariasoftware.psw.jabberpoint.Controller;

import br.upe.enenhariasoftware.psw.jabberpoint.View.BitmapItemView;
import br.upe.enenhariasoftware.psw.jabberpoint.View.SlideItemView;
import br.upe.enenhariasoftware.psw.jabberpoint.View.TextItemView;

public class SlideItemController {

  private SlideItemView slideItem;

  public SlideItemController() {

  }

  public SlideItemController(SlideItemView slideItem) {
    this.slideItem = slideItem;
  }

  public SlideItemView getSlideItem() {
    return slideItem;
  }

  public void setSlideItem(SlideItemView slideItem) {
    this.slideItem = slideItem;
  }

  public void setTextItem(int level, String name) {
    this.slideItem = new TextItemView(level, name);
  }

  public void setBitmapItem(int level, String name) {
    this.slideItem = new BitmapItemView(level, name);
  }

}

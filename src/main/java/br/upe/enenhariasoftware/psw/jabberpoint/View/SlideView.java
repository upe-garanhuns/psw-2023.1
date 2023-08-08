package br.upe.enenhariasoftware.psw.jabberpoint.View;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.SlideModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.StyleModel;

public class SlideView {

  private SlideModel slide;

  public SlideView(SlideModel slide) {
    this.slide = slide;
  }

  public void draw(Graphics graphic, Rectangle area, ImageObserver observer) {
    float scale = slide.getScale(area);

    int y = area.y;

    SlideItemView slideItem = this.slide.getTitleController().getSlideItem();
    StyleModel style = StyleModel.getStyle(slideItem.getLevel());
    slideItem.draw(area.x, y, scale, graphic, style, observer);

    y += slideItem.getBoundingBox(graphic, observer, scale, style).height;

    for (int number = 0; number < slide.getSize(); number++) {
      slideItem = slide.getSlideItems().get(number).getSlideItem();

      style = StyleModel.getStyle(slideItem.getLevel());
      slideItem.draw(area.x, y, scale, graphic, style, observer);

      y += slideItem.getBoundingBox(graphic, observer, scale, style).height;
    }
  }

}

package br.upe.enenhariasoftware.psw.jabberpoint.Controller;

import javax.swing.JFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.View.SlideComponentView;

public class SlideComponentController {

  private SlideComponentView slideViewComponent;

  public SlideComponentController(PresentationModel presentation, JFrame frame) {
    slideViewComponent = new SlideComponentView(presentation, frame);
  }


  public SlideComponentView getSlideViewComponent() {
    return slideViewComponent;
  }

  public void setSlideViewComponent(SlideComponentView slideViewComponent) {
    this.slideViewComponent = slideViewComponent;
  }
}

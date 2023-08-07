package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.IPresentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.ISlide;

public interface ISlideViewComponent {

    void update(IPresentation presentation, ISlide slide);

}

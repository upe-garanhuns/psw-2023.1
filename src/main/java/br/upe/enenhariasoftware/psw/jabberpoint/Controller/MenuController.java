/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.Controller;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.AccessorModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.XmlAccessorModel;
import br.upe.enenhariasoftware.psw.jabberpoint.View.AboutView;
import br.upe.enenhariasoftware.psw.jabberpoint.View.MenuView;

public class MenuController extends MenuBar {

  private static final long serialVersionUID = 227L;

  private MenuView menuView;

  public MenuController() {

  }

  public MenuController(MenuView menuView) {
    this.menuView = menuView;
  }

  public void actionOpen() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        menuView.getPresentation().clear();

        AccessorModel xmlAccessor = new XmlAccessorModel();
        try {
          xmlAccessor.loadFile(menuView.getPresentation(),
              new File(menuView.getTestfile()).getAbsolutePath());
          menuView.getPresentation().setSlideNumber(0);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(menuView.getMainParent(), menuView.getIoex() + exc,
              menuView.getLoaderr(), JOptionPane.ERROR_MESSAGE);
        }

        menuView.getMainParent().repaint();
      }
    });
  }

  public void actionNew() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        menuView.getPresentation().clear();
        menuView.getMainParent().repaint();
      }
    });
  }

  public void actionSave() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AccessorModel xmlAccessor = new XmlAccessorModel();
        try {
          xmlAccessor.saveFile(menuView.getPresentation(), menuView.getSavefile());
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(menuView.getMainParent(), menuView.getIoex() + exc,
              menuView.getSaveerr(), JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

  public void actionExit() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        menuView.getPresentation().exit(0);
      }
    });
  }

  public void actionNext() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        menuView.getPresentation().nextSlide();
      }
    });
  }

  public void actionPrev() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        menuView.getPresentation().prevSlide();
      }
    });
  }

  public void actionGoto() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        String pageNumberStr = JOptionPane.showInputDialog(menuView.getPagenr());
        int pageNumber = Integer.parseInt(pageNumberStr);
        if (pageNumber >= menuView.getPresentation().getSize()) {
          menuView.getPresentation().setSlideNumber(menuView.getPresentation().getSize());
        } else if (pageNumber <= 0) {
          menuView.getPresentation().setSlideNumber(0);
        } else {
          menuView.getPresentation().setSlideNumber(pageNumber - 1);
        }

      }
    });
  }

  public void actionAbout() {
    menuView.getMenuItem().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        AboutView.show(menuView.getMainParent());
      }
    });
  }
}

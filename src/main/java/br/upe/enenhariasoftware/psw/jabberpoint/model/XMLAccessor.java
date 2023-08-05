/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class XMLAccessor extends Accessor {

  // Responsável por ler e escrever as apresentações (Presentations) XML
  // Se comunica com os outros models
  // não vejo nada de view ou controller aqui
  private static final Logger logger = LogManager.getLogger(XMLAccessor.class);

  protected static final String DEFAULT_API_TO_USE = "dom";

  protected static final String SHOWTITLE = "showtitle";
  protected static final String SLIDETITLE = "title";
  protected static final String SLIDE = "slide";
  protected static final String ITEM = "item";
  protected static final String LEVEL = "level";
  protected static final String KIND = "kind";
  protected static final String TEXT = "text";
  protected static final String IMAGE = "image";

  protected static final String PCE = "Parser Configuration Exception";
  protected static final String UNKNOWNTYPE = "Unknown Element type";
  protected static final String NFE = "Number Format Exception";

  private String getTitle(Element element, String tagName) {
    NodeList titles = element.getElementsByTagName(tagName);
    return titles.item(0).getTextContent();

  }

  // Responsável por carregar uma apresentação
  // realiza mais de uma função
  // 1 - Abre e lê o arquivo xml
  // 2 - define o título de presentation
  // 3 - a cada elemento do xml, cria um novo slide e o adiciona na apresentação
  // 4 - adiciona os SlideItem(TextItem ou BitMapItem) a cada slide
  public void loadFile(IPresentation presentation, String filename) throws IOException {
    int slideNumber = 0;
    int itemNumber = 0;
    int max = 0;
    int maxItems = 0;

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      // to be compliant, completely disable DOCTYPE declaration:
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

      DocumentBuilder builder = factory.newDocumentBuilder();

      Document document = builder.parse(new File(filename));

      Element doc = document.getDocumentElement();
      presentation.setTitle(getTitle(doc, SHOWTITLE));

      NodeList slides = doc.getElementsByTagName(SLIDE);
      max = slides.getLength();

      for (slideNumber = 0; slideNumber < max; slideNumber++) {
        Element xmlSlide = (Element) slides.item(slideNumber);

        Slide slide = new Slide();
        slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
        presentation.append(slide);

        NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
        maxItems = slideItems.getLength();

        for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
          Element item = (Element) slideItems.item(itemNumber);
          loadSlideItem(slide, item);
        }
      }

    } catch (IOException iox) {
      logger.error(iox.toString());
    } catch (SAXException sax) {
      logger.error(sax.toString());
    } catch (ParserConfigurationException pcx) {
      logger.error(pcx.toString());
    }

  }

  // Responsável por carregar um item do slide
  // Realiza mais de uma função
  // 1 - verifica o tipo do item do slidie, a partir dos atributos
  // 2 - cria objeto TextItem ou BitmapItem, com base no tipo dos atributos do item
  protected void loadSlideItem(Slide slide, Element item) {
    int level = 1;

    NamedNodeMap attributes = item.getAttributes();

    String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

    if (leveltext != null) {
      try {
        level = Integer.parseInt(leveltext);
      } catch (NumberFormatException x) {
        logger.error(x.toString());
      }
    }

    String type = attributes.getNamedItem(KIND).getTextContent();
    if (TEXT.equals(type)) {
      slide.appendText(level, item.getTextContent());
    } else {
      if (IMAGE.equals(type)) {
        slide.appendBitmap(level, item.getTextContent());
      } else {
        logger.error(UNKNOWNTYPE);
      }
    }
  }

  // Responsável por salvarr a apresentação
  // 1 - inicializa o printwriter para gravar as informações do slide
  // 2 - salva cada slidem, e cada item através do printwriter

  public void saveFile(IPresentation presentation, String filename) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(filename));

    out.println("<?xml version=\"1.0\"?>");
    out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
    out.println("<presentation>");

    out.print("<showtitle>");
    out.print(presentation.getTitle());
    out.println("</showtitle>");

    for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
      Slide slide = presentation.getSlide(slideNumber);

      out.println("<slide>");
      out.println("<title>" + slide.getTitle() + "</title>");

      List<SlideItem> slideItems = slide.getSlideItems();
      for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
        SlideItem slideItem = slideItems.get(itemNumber);
        out.print("<item kind=");

        if (slideItem instanceof TextItem) {
          out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
          out.print(((TextItem) slideItem).getText());
        } else {
          if (slideItem instanceof BitmapItem) {
            out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((BitmapItem) slideItem).getName());
          } else {
            logger.info("Ignoring " + slideItem);
          }
        }

        out.println("</item>");
      }

      out.println("</slide>");
    }

    out.println("</presentation>");

    out.close();
  }

}
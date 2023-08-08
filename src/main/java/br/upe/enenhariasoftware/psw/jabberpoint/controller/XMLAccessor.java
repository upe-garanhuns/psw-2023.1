/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.TextItem;

public class XMLAccessor extends Accessor {

	private static final Logger LOGGER = Logger.getLogger(XMLAccessor.class.getName());

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

	@Override
	public void loadFile(PresentationController presentationController, String fileName) throws IOException {
		int slideNumber = 0;
		int itemNumber = 0;
		int max = 0;
		int maxItems = 0;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(fileName));

			Element doc = document.getDocumentElement();
			presentationController.getPresentation().setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();

			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);

				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentationController.append(slide);

				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();

				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}

		} catch (IOException iox) {
			LOGGER.warning(iox.toString());
		} catch (SAXException sax) {
			LOGGER.warning(sax.getMessage());
		} catch (ParserConfigurationException pcx) {
			LOGGER.warning(PCE);
		}
	}

	protected void loadSlideItem(Slide slide, Element item) {
		int level = 1;

		NamedNodeMap attributes = item.getAttributes();

		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			} catch (NumberFormatException x) {
				LOGGER.warning(NFE);
			}
		}

		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		} else {
			if (IMAGE.equals(type)) {
				slide.append(new BitmapItem(level, item.getTextContent()));
			} else {
				LOGGER.warning(UNKNOWNTYPE);
			}
		}
	}

	@Override
	public void saveFile(PresentationController presentationController, String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");

		out.print("<showtitle>");
		out.print(presentationController.getPresentation().getTitle());
		out.println("</showtitle>");

		for (int slideNumber = 0; slideNumber < presentationController.getPresentation().getSize(); slideNumber++) {
			Slide slide = presentationController.getPresentation().getSlide(slideNumber);

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
						LOGGER.info("Ignoring " + slideItem);
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

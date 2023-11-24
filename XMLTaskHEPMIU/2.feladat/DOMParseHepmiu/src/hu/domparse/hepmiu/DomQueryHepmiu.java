package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.Scanner;

public class DomQueryHepmiu {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			
			//LEK�RDEZNI K�V�NT ELEMENT LEK�RDEZ�SE

			Scanner sc = new Scanner(System.in);

			System.out.println("Adja meg az element nevet!");

			String elementName = sc.nextLine();

			System.out.println("Adja meg az element ID-janak erteket!");

			String elementID = sc.nextLine();

			sc.close();

			selectElementByID(doc, elementName, elementID);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void selectElementByID(Document doc, String elementName, String elementID) {
		NodeList nodeList = doc.getElementsByTagName(elementName);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				if (elementName.equalsIgnoreCase("Katon�k") || elementName.equalsIgnoreCase("Tisztek")
						|| elementName.equalsIgnoreCase("F�parancsnok")) {
					if (element.getAttribute(elementName.charAt(0) + "_sorsz�m").equals(elementID)) {
						
						// ELEMENTEK KI�R�SA �RT�KEIKKEL EGY�TT
						
						
						System.out.println("Element Neve: " + element.getNodeName());
						NamedNodeMap attributes = element.getAttributes();
						for (int j = 0; j < attributes.getLength(); j++) {
							Node attribute = attributes.item(j);
							System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
						}

						NodeList children = element.getChildNodes();
						for (int k = 0; k < children.getLength(); k++) {
							Node child = children.item(k);
							if (child.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println(child.getNodeName() + ": " + child.getTextContent());
							}
						}
					}
				}

				else if (elementName.equalsIgnoreCase("Sz�razf�ldi_er�k") || elementName.equalsIgnoreCase("Tenger�szet")
						|| elementName.equalsIgnoreCase("L�gier�")) {
					if (element.getAttribute("SzE_ID").equals(elementID)
							|| element.getAttribute("Teng_ID").equals(elementID)
							|| element.getAttribute("Leg_ID").equals(elementID)) {
						
						
						// ELEMENTEK KI�R�SA �RT�KEIKKEL EGY�TT
						
						
						System.out.println("Element Neve: " + element.getNodeName());
						NamedNodeMap attributes = element.getAttributes();
						for (int j = 0; j < attributes.getLength(); j++) {
							Node attribute = attributes.item(j);
							System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
						}

						NodeList children = element.getChildNodes();
						for (int k = 0; k < children.getLength(); k++) {
							Node child = children.item(k);
							if (child.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println(child.getNodeName() + ": " + child.getTextContent());
							}
						}
					}

				}

				else if (elementName.equalsIgnoreCase("Hadsereg")) {
					if (element.getAttribute("hadseregID").equals(elementID)) {
						
						
						// ELEMENTEK KI�R�SA �RT�KEIKKEL EGY�TT
						
						
						
						System.out.println("Element Neve: " + element.getNodeName());
						NamedNodeMap attributes = element.getAttributes();
						for (int j = 0; j < attributes.getLength(); j++) {
							Node attribute = attributes.item(j);
							System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
						}

						NodeList children = element.getChildNodes();
						for (int k = 0; k < children.getLength(); k++) {
							Node child = children.item(k);
							if (child.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println(child.getNodeName() + ": " + child.getTextContent());
							}
						}
					}

				}

			}

		}
	}

}

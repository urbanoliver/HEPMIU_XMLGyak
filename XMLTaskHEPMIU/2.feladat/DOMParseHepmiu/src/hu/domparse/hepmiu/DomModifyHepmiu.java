package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class DomModifyHepmiu {
	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Scanner sc = new Scanner(System.in);

			System.out.println("Adja meg a m�dos�tani k�v�nt element nev�t!");
			String elementName = sc.nextLine();

			System.out.println("Adja meg a m�dos�tani k�v�nt element ID-j�t!");
			String elementID = sc.nextLine();

			System.out.println("Adja meg a m�dos�tani k�v�nt element attrib�tum�t vagy gyerek�nek nev�t!");
			String attributeNameOrChildName = sc.nextLine();

			System.out.println("Adja meg az �j �rt�k�t");
			String newValue = sc.nextLine();

			modifyElementByID(doc, elementName, elementID, attributeNameOrChildName, newValue);

			sc.close();

			writeToFile(doc, "XMLHepmiu1.xml");

			System.out.println("Adat sikeresen m�dos�tva!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modifyElementByID(Document doc, String elementName, String elementID,
			String attributeNameOrChildName, String newValue) {
		NodeList nodeList = doc.getElementsByTagName(elementName);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				if (elementName.equalsIgnoreCase("Katon�k") || elementName.equalsIgnoreCase("Tisztek")
						|| elementName.equalsIgnoreCase("F�parancsnok")) {
					if (element.getAttribute(elementName.charAt(0) + "_sorsz�m").equals(elementID)) {

						if (element.hasAttribute(attributeNameOrChildName)) {
							element.setAttribute(attributeNameOrChildName, newValue);
						} else {

							NodeList childNodes = element.getElementsByTagName(attributeNameOrChildName);
							if (childNodes.getLength() > 0) {
								Node childNode = childNodes.item(0);
								childNode.setTextContent(newValue);
							} else {
								System.out.println("Adat t�pus nem tal�lhat�: " + attributeNameOrChildName);
							}
						}
					}
				}

				else if (elementName.equalsIgnoreCase("Sz�razf�ldi_er�k") || elementName.equalsIgnoreCase("Tenger�szet")
						|| elementName.equalsIgnoreCase("L�gier�")) {
					if (element.getAttribute("SzE_ID").equals(elementID)
							|| element.getAttribute("Teng_ID").equals(elementID)
							|| element.getAttribute("Leg_ID").equals(elementID)) {

						if (element.hasAttribute(attributeNameOrChildName)) {
							element.setAttribute(attributeNameOrChildName, newValue);
						} else {

							NodeList childNodes = element.getElementsByTagName(attributeNameOrChildName);
							if (childNodes.getLength() > 0) {
								Node childNode = childNodes.item(0);
								childNode.setTextContent(newValue);
							} else {
								System.out.println("Adat t�pus nem tal�lhat�: " + attributeNameOrChildName);
							}
						}
					}

				}

				else if (elementName.equalsIgnoreCase("Hadsereg")) {
					if (element.getAttribute("hadseregID").equals(elementID)) {

						if (element.hasAttribute(attributeNameOrChildName)) {
							element.setAttribute(attributeNameOrChildName, newValue);
						} else {

							NodeList childNodes = element.getElementsByTagName(attributeNameOrChildName);
							if (childNodes.getLength() > 0) {
								Node childNode = childNodes.item(0);
								childNode.setTextContent(newValue);
							} else {
								System.out.println("Adat t�pus nem tal�lhat�: " + attributeNameOrChildName);
							}
						}
					}

				}

			}
		}
	}

	public static void writeToFile(Document doc, String filename) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

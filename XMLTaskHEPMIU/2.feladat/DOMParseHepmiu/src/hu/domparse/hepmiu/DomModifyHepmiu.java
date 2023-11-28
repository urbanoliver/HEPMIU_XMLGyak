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

			System.out.println("Adja meg a módosítani kívánt element nevét!");
			String elementName = sc.nextLine();

			System.out.println("Adja meg a módosítani kívánt element ID-ját!");
			String elementID = sc.nextLine();

			System.out.println("Adja meg a módosítani kívánt element attribútumát vagy gyerekének nevét!");
			String attributeNameOrChildName = sc.nextLine();

			System.out.println("Adja meg az új értékét");
			String newValue = sc.nextLine();

			modifyElementByID(doc, elementName, elementID, attributeNameOrChildName, newValue);

			sc.close();

			writeToFile(doc, "XMLHepmiu1.xml");

			System.out.println("Adat sikeresen módosítva!");

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

				if (elementName.equalsIgnoreCase("Katonák") || elementName.equalsIgnoreCase("Tisztek")
						|| elementName.equalsIgnoreCase("Fõparancsnok")) {
					if (element.getAttribute(elementName.charAt(0) + "_sorszám").equals(elementID)) {

						if (element.hasAttribute(attributeNameOrChildName)) {
							element.setAttribute(attributeNameOrChildName, newValue);
						} else {

							NodeList childNodes = element.getElementsByTagName(attributeNameOrChildName);
							if (childNodes.getLength() > 0) {
								Node childNode = childNodes.item(0);
								childNode.setTextContent(newValue);
							} else {
								System.out.println("Adat típus nem található: " + attributeNameOrChildName);
							}
						}
					}
				}

				else if (elementName.equalsIgnoreCase("Szárazföldi_erõk") || elementName.equalsIgnoreCase("Tengerészet")
						|| elementName.equalsIgnoreCase("Légierõ")) {
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
								System.out.println("Adat típus nem található: " + attributeNameOrChildName);
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
								System.out.println("Adat típus nem található: " + attributeNameOrChildName);
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

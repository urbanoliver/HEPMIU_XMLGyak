package domhepmiu1108;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DomWriteHepmiu {

	public static void main(String[] args) {

		try {

			File xmlFile = new File("Hepmiu_orarend.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Fa struktúra a konzolon:");

			kiirTartalom(doc.getDocumentElement(), "");

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource src = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("Hepmiu_orarend.xml"));

			transformer.transform(src, result);

			System.out.println("Az Hepmiu_orarend1.xml fájl elkészült.");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void kiirTartalom(Node node, String indent) {

		if (node.getNodeType() == Node.ELEMENT_NODE) {

			System.out.println(indent + "Elem: " + node.getNodeName());

			if (node.hasAttributes()) {

				NamedNodeMap attributes = node.getAttributes();

				for (int i = 0; i < attributes.getLength(); i++) {

					Node attribute = attributes.item(i);

					System.out.println(
							indent + " Attribútum: " + attribute.getNodeName() + " = " + attribute.getNodeValue());

				}

			}

			if (node.hasChildNodes()) {

				NodeList children = node.getChildNodes();

				for (int i = 0; i < children.getLength(); i++) {

					Node child = children.item(i);

					kiirTartalom(child, indent + " ");

				}

			}

		} else if (node.getNodeType() == Node.TEXT_NODE) {

			String textContent = node.getNodeValue().trim();

			if (!textContent.isEmpty()) {

				System.out.println(indent + "Tartalom: " + textContent);

			}

		}

	}

}
package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyHepmiu {
	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			// Find the node that you want to modify
			NodeList nodeList = doc.getElementsByTagName("your_tag_name");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					// Modify the elements as needed
					element.getElementsByTagName("name").item(0).setTextContent("New Name");
					element.getElementsByTagName("value").item(0).setTextContent("New Value");
					// Add more lines for other elements as needed
				}
			}

			// Write the updated content to the same XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("XMLHepmiu.xml"));
			transformer.transform(source, result);

			System.out.println("XML file updated successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

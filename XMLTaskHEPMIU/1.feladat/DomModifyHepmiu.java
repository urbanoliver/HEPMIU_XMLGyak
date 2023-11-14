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
			
			System.out.println("Adja meg az element nevet!");
			String e_Nev = sc.nextLine();
			System.out.println("Adja meg az element ID-jat!");
			String e_ID = sc.nextLine();
			System.out.println("Adja meg a modositani kivant attributumot!");
			String e_Attrib = sc.nextLine();
			System.out.println("Adja meg az attributum uj erteket!");
			String e_UjErtek = sc.nextLine();

			// Modify attributes and values based on element ID
			modifyAttributesByID(doc,e_Nev, e_ID, e_Attrib, e_UjErtek);

			// Write the updated content to the same XML file
			writeToFile(doc, "your_modified_xml_file.xml");

			System.out.println("Attributes and values modified successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Modify attributes and values based on element ID
	public static void modifyAttributesByID(Document doc,String elementName, String elementID, String attributeName, String newValue) {
		NodeList nodeList = doc.getElementsByTagName(elementName);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				// Check if the element has the desired ID
				if (element.getAttribute("id").equals(elementID)) {
					// Modify attributes and values
					element.setAttribute("your_attribute_name", attributeName);
					element.getElementsByTagName("your_value_element").item(0).setTextContent(newValue);
					// Add more lines for other attributes or values as needed
				}
			}
		}
	}

	// Write the updated content to a new XML file
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

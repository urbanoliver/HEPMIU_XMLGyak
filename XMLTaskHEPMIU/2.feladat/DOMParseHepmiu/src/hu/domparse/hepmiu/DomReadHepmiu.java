package hu.domparse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadHepmiu {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				File newXMLFile = new File("XMLHepmiu.xml");
				StreamResult newXmlStream = new StreamResult(newXMLFile);

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(new File("./XMLHepmiu.xml"));
				cutEmptyStrings(doc.getDocumentElement());

				writeDoc(doc, newXmlStream);

				System.out.println(makeToXMLFormat(doc));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// A fájlban lévõ üres stringek törlése
		private static void cutEmptyStrings(Node root) {
			NodeList nodeList = root.getChildNodes();
			List<Node> deleteEmptyLists = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.TEXT_NODE
						&& nodeList.item(i).getTextContent().isEmpty()) {
					deleteEmptyLists.add(nodeList.item(i));
				} else {
					cutEmptyStrings(nodeList.item(i));
				}
			}
			for (Node node : deleteEmptyLists) {
				root.removeChild(node);
			}
		}

		// Új XML fájlba írása
		public static void writeDoc(Document document, StreamResult output) {
			try {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(document);
				transformer.transform(source, output);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// XML formátum létrehozása
		public static String makeToXMLFormat(Document document) {
			return "<?xml version=\"" + document.getXmlVersion() + "\" encoding=\"" + document.getXmlEncoding() + "\" ?>\n"
					+ elementsToXMLFormat(document.getDocumentElement(), 0);
		}

		// Tag-ek rendelése az XML elementekhez
		public static String elementsToXMLFormat(Node node, int indent) {
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				return "";
			}
			StringBuilder output = new StringBuilder();
			output.append(getIndent(indent)).append("<").append(((Element) node).getTagName());
			if (node.hasAttributes()) {
				for (int i = 0; i < node.getAttributes().getLength(); i++) {
					Node attribute = node.getAttributes().item(i);
					output.append(" ").append(attribute.getNodeName()).append("=\"").append(attribute.getNodeValue())
							.append("\"");
				}
			}
			NodeList children = node.getChildNodes();
			if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
				output.append(">").append(children.item(0).getTextContent().trim()).append("</")
						.append(((Element) node).getTagName()).append(">\n");
			} else {
				output.append(">\n");
				for (int i = 0; i < children.getLength(); i++) {
					output.append(elementsToXMLFormat(children.item(i), indent + 1));
				}
				output.append(getIndent(indent)).append("</").append(((Element) node).getTagName()).append(">\n");
			}
			return output.toString();
		}

		// Space-ek beiktatása
		private static String getIndent(int indent) {
			StringBuilder indentation = new StringBuilder();
			for (int i = 0; i < indent; i++) {
				indentation.append("    ");
			}
			return indentation.toString();
		}


}

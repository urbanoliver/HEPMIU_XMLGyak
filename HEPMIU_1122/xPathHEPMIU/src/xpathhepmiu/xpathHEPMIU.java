package xpathhepmiu;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class xpathHEPMIU {
	public static void main(String[] args) {
		try {
			
			File xmlFile = new File("studentHepmiu.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			System.out.println("1. feladat=================");
			XPathExpression expr1 = xPath.compile("/class/student");
			NodeList result1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result1.getLength(); i++) {
				Element student = (Element) result1.item(i);
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			}
			System.out.println("\n2. feladat=================");
			String targetId = "02";
			XPathExpression expr2 = xPath.compile("/class/student[@id='" + targetId + "']");
			Node result2 = (Node) expr2.evaluate(doc, XPathConstants.NODE);

			if (result2 != null) {
				Element student = (Element) result2;
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			} else {
				System.out.println(targetId + "ID-jú Student nem található.");
			}

			System.out.println("\n3. feladat=================");
			XPathExpression expr3 = xPath.compile("//student");
			NodeList result3 = (NodeList) expr3.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result3.getLength(); i++) {
				Element student = (Element) result3.item(i);
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			}


			System.out.println("\n4. feladat=================");
			int targetIndex = 1;
			XPathExpression expr4 = xPath.compile("/class/student[position() = " + (targetIndex + 1) + "]");
			Node result4 = (Node) expr4.evaluate(doc, XPathConstants.NODE);

			if (result4 != null) {
				Element student = (Element) result4;
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			} else {
				System.out.println("A második student element nem található.");
			}


			System.out.println("\n5. feladat=================");

			XPathExpression expr5 = xPath.compile("/class/student[last()]");
			Node result5 = (Node) expr5.evaluate(doc, XPathConstants.NODE);

			if (result5 != null) {
				Element student = (Element) result5;
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			} else {
				System.out.println("Az utolsó student elem nem található.");
			}


			System.out.println("\n6. feladat=================");

			XPathExpression expr6 = xPath.compile("/class/student[last()-1]");
			Node result6 = (Node) expr6.evaluate(doc, XPathConstants.NODE);

			if (result6 != null) {
				Element student = (Element) result5;
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			} else {
				System.out.println("Az utolsó student elem nem található.");
			}

			System.out.println("\n7. feladat=================");

			XPathExpression expr7 = xPath.compile("/class/student[position() <= 2]");
			NodeList result7 = (NodeList) expr7.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result7.getLength(); i++) {
				Element student = (Element) result7.item(i);
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			}

			System.out.println("\n8. feladat=================");

			XPathExpression expr8 = xPath.compile("/class/*");
			NodeList result8 = (NodeList) expr8.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result8.getLength(); i++) {
				Element childElement = (Element) result8.item(i);
				System.out.println(childElement.getTagName() + ": " + childElement.getTextContent());
			}


			System.out.println("\n9. feladat=================");

			XPathExpression expr9 = xPath.compile("/class/student[@*]");
			NodeList result9 = (NodeList) expr9.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result9.getLength(); i++) {
				Element student = (Element) result9.item(i);
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			}

			System.out.println("\n10. feladat=================");

			XPathExpression expr10 = xPath.compile("//*");
			NodeList result10 = (NodeList) expr10.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result10.getLength(); i++) {
				Element element = (Element) result10.item(i);
				System.out.println(element.getTagName() + ": " + element.getTextContent());
			}

			System.out.println("\n11. feladat=================");

			XPathExpression expr11 = xPath.compile("/class/student[kor > 20]");
			NodeList result11 = (NodeList) expr11.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result11.getLength(); i++) {
				Element student = (Element) result11.item(i);
				System.out.println("Student ID: " + student.getAttribute("id"));
				System.out.println("Nev: " + student.getElementsByTagName("keresztnev").item(0).getTextContent() + " "
						+ student.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out.println("Becenev: " + student.getElementsByTagName("becenev").item(0).getTextContent());
				System.out.println("Kor: " + student.getElementsByTagName("kor").item(0).getTextContent() + "\n");
			}


			System.out.println("\n12. feladat=================");

			XPathExpression expr12 = xPath.compile("/class/student/keresztnev | /class/student/vezeteknev");
			NodeList result12 = (NodeList) expr12.evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < result12.getLength(); i++) {
				Element node = (Element) result12.item(i);
				System.out.println(node.getTagName() + ": " + node.getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package DADES;

import MODEL.LineaComanda;
import MODEL.Comanda;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

@SuppressWarnings("unused")
public class llegirXML {
	public ArrayList<Comanda> comandes = new ArrayList<Comanda>();

	String idclient;
	String codi;
	int idComanda;
	int unitat;
	int test;
	char status;
	float preuUnitari;
	char statusc;
	public llegirXML() {

		try {
			File inputFile = new File("comanda.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("comanda");

			System.out.println("----------------------------");
			test = 1;

			for (int tempo = 0; tempo < nList.getLength(); tempo++) {

				Node nNode = nList.item(tempo);
				Element eElement2 = (Element) nNode;
				ArrayList<LineaComanda> liniaComanda = new ArrayList<LineaComanda>();
				NodeList nList2 = nList.item(tempo).getChildNodes();

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					for (int temp = 0; temp < nList2.getLength(); temp++) {
						Node nNode2 = nList2.item(temp);
						//System.out.println("\nElement  :" + nNode.getNodeName());

						if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode2;

							//System.out.println(
								//	"Codi article: " + eElement.getElementsByTagName("codi").item(0).getTextContent());
							codi = eElement.getElementsByTagName("codi").item(0).getTextContent();

							status = eElement.getElementsByTagName("status").item(0).getTextContent().charAt(0);

							//System.out.println(
									//"Unitats : " + eElement.getElementsByTagName("unitats").item(0).getTextContent());
							unitat = Integer
									.parseInt(eElement.getElementsByTagName("unitats").item(0).getTextContent());

							preuUnitari = Float
									.parseFloat(eElement.getElementsByTagName("preuUnitat").item(0).getTextContent());

							liniaComanda
									.add(new LineaComanda(temp + 1 / 2, tempo + 1, codi, unitat, preuUnitari, status));
						}
					}
					idComanda = tempo + 1;
					
					//System.out.println(eElement2.getAttribute("idclient"));
					//System.out.println(eElement2.getAttribute("statusComanda"));
					idclient = eElement2.getAttribute("idclient");
					
					
					if (eElement2.getAttribute("statusComanda").equals("0")) {

						comandes.add(new Comanda(eElement2.getAttribute("idclient"),
								eElement2.getAttribute("statusComanda").charAt(0), liniaComanda));
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	

	
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public char getStatus() {
		return status;
	}

	public String getIdClient() {
		return idclient;
	}

	public char getStatusc() {
		return statusc;
	}

	public ArrayList<Comanda> getComandes() {
		return comandes;
	}

	@Override

	public String toString() {
		return "llegirXML [idclient=" + idclient + ", codi=" + codi + ", unitats=" + unitat + ", unitat=" + unitat
				+ ", getIdClient()=" + getIdClient() + "]";
	}

}

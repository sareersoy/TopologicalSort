import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MissionExploration {

    public void printSolarSystems(Galaxy galaxy) {
    galaxy.printSolarSystems(galaxy.getSolarSystems());}

    public Galaxy readXML(String filename) {
        List<Planet> planetList = new ArrayList<>();
        try
        {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Planet");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    String ID= eElement.getElementsByTagName("ID").item(0).getTextContent();
                    int TechnologyLevel= Integer.parseInt(eElement.getElementsByTagName("TechnologyLevel").item(0).getTextContent());
                    List<String> Neighbors = new ArrayList<>();
                    NodeList nodeList2 = eElement.getElementsByTagName("Neighbors");
                    for (int itr2 = 0; itr2 < nodeList2.getLength(); itr2++) {
                        Node node3 = nodeList2.item(itr2);
                        if (node3.getNodeType() == Node.ELEMENT_NODE ) {
                            Element eElement4 = (Element) node3;
                            for (int i = 0; i< eElement4.getElementsByTagName("PlanetID").getLength(); i++){
                                String PlanetID = eElement4.getElementsByTagName("PlanetID").item(i).getTextContent();
                                Neighbors.add(PlanetID);}}}
                Planet pl = new Planet(ID, TechnologyLevel, Neighbors);
                    planetList.add(pl);}}}
        catch (Exception e) {
            e.printStackTrace();}
        return new Galaxy(planetList);}}

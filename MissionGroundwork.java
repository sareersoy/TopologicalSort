import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class MissionGroundwork {

    public void printSchedule(List<Project> projectList) {
        for(Project project:projectList){
        project.printSchedule(project.getEarliestSchedule());}
    }

    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
        {
            try { File file = new File(filename);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nodeListMain = doc.getElementsByTagName("Project");

                for (int itr = 0; itr < nodeListMain.getLength(); itr++) {
                    Node node = nodeListMain.item(itr);
                    List<Task> TaskList = new ArrayList<>();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                        NodeList nodeList = eElement.getElementsByTagName("Tasks");

                        for (int itr1 = 0; itr1<nodeList.getLength(); itr1++){
                            Node node1 = nodeList.item(itr1);
                            if (node1.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement1 = (Element) node1;
                                NodeList nodeList1 = eElement1.getElementsByTagName("Task");

                                for (int itr3 = 0; itr3 < nodeList1.getLength(); itr3++) {
                                    Node node2 = nodeList1.item(itr3);
                                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElement3 = (Element) node2;
                                        int taskID = Integer.parseInt(eElement3.getElementsByTagName("TaskID").item(itr1).getTextContent());
                                        String description = eElement3.getElementsByTagName("Description").item(itr1).getTextContent();
                                        int duration = Integer.parseInt(eElement3.getElementsByTagName("Duration").item(itr1).getTextContent());
                                        List<Integer> Dependencies = new ArrayList<>();
                                        NodeList nodeList2 = eElement3.getElementsByTagName("Dependencies");

                                        for (int itr2 = 0; itr2 < nodeList2.getLength(); itr2++) {
                                            Node node3 = nodeList2.item(itr2);
                                            if (node3.getNodeType() == Node.ELEMENT_NODE ) {
                                                Element eElement4 = (Element) node3;
                                                for (int i = 0; i< eElement4.getElementsByTagName("DependsOnTaskID").getLength(); i++){
                                                    int ID = Integer.parseInt(eElement4.getElementsByTagName("DependsOnTaskID").item(i).getTextContent());
                                                    Dependencies.add(ID);}}}
                                        Task obj = new Task(taskID,description,duration,Dependencies);
                                        TaskList.add(obj);}}}}
                        Project project = new Project(name, TaskList);
                        projectList.add(project);}}}
            catch (Exception e) { e.printStackTrace();}}
        return projectList;}}

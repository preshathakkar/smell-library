package smell;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author Presha Thakkar
 */
public class Smell {private String smcId = null;
    private String smcName = null;
    private String smcDesc = null;
    private String smcChemFormula = null;
    private String smcPin = null;
    private ArrayList<SmellBean> smells = new ArrayList<SmellBean>();
    private SmellBean sb;

    
    public ArrayList<SmellBean> readXML(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse("smell.xml");
            dom.getDocumentElement ().normalize ();
            
            NodeList nl = dom.getElementsByTagName("smell");
            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    sb = new SmellBean();
                    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nl.item(i);
                        if (el.getNodeName().contains("smell")) {
                            
                            sb.setSmcId(el.getElementsByTagName("smcId").item(0).getTextContent());
                            sb.setSmcName(el.getElementsByTagName("smcName").item(0).getTextContent());
                            sb.setSmcDesc(el.getElementsByTagName("smcDesc").item(0).getTextContent());
                            sb.setSmcChemFormula(el.getElementsByTagName("smcChemFormula").item(0).getTextContent());
                            sb.setSmcPin(el.getElementsByTagName("smcPin").item(0).getTextContent());
                            
                        }
                    }
                    
                    boolean ad = smells.add(sb);
                   
                }
            }
            return smells;
        } catch (SAXException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException npr){
        System.out.println(npr.fillInStackTrace());
        npr.printStackTrace();
        
        }
    return smells;
    
    }
    
    public void saveToXML(SmellBean sb) {
    Document dom;
    Element e = null;

    // instance of a DocumentBuilderFactory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
        // use factory to get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        // create instance of DOM
        dom = db.parse("smell.xml");
        Element root = dom.getDocumentElement();
        Element rootEle = dom.getDocumentElement();

        // create the root element
        
        Element se = dom.createElement("smell");
        rootEle.appendChild(se);

        // create data elements and place them under root
        e = dom.createElement("smcId");
        e.appendChild(dom.createTextNode(sb.getSmcId()));
        se.appendChild(e);

        e = dom.createElement("smcName");
        e.appendChild(dom.createTextNode(sb.getSmcName()));
        se.appendChild(e);

        e = dom.createElement("smcDesc");
        e.appendChild(dom.createTextNode(sb.getSmcDesc()));
        se.appendChild(e);

        e = dom.createElement("smcChemFormula");
        e.appendChild(dom.createTextNode(sb.getSmcChemFormula()));
        se.appendChild(e);
        
        e = dom.createElement("smcPin");
        e.appendChild(dom.createTextNode(sb.getSmcPin()));
        se.appendChild(e);
        
        //dom.appendChild(rootEle);

        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "smell.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom), 
                                 new StreamResult(new FileOutputStream("smell.xml")));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }   catch (SAXException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException pce) {
        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
    }
}
    
    public void removeFromXMLByName(String smellName){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse("smell.xml");
                doc.getDocumentElement().normalize();
     
                NodeList nodeList = doc.getElementsByTagName("smell");
                
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Node removeNote = element.getElementsByTagName("smcName").item(0);
                        if(smellName.equals(removeNote.getTextContent())){
                            Node Parent = node.getParentNode();
                            Parent.removeChild(node);
                        }
                    }
                
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("smell.xml"));
 
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (SAXException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeFromXMLById(String smellId){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse("smell.xml");
                doc.getDocumentElement().normalize();
     
                NodeList nodeList = doc.getElementsByTagName("smell");
                
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Node removeNote = element.getElementsByTagName("smcId").item(0);
                        if(smellId.equals(removeNote.getTextContent())){
                            Node Parent = node.getParentNode();
                            Parent.removeChild(node);
                        }
                    }
                
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("smell.xml"));
 
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (SAXException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Smell.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
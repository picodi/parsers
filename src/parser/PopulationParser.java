package parser;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class PopulationParser
{
    public static final String COUNTRY_NAME = "country_name";
    public static final String COUNTRY_POPULATION = "population";
    private String filename;

    public PopulationParser()
    {
        this.setFilename("population.xml");
    }

    public PopulationParser(String filename)
    {
        this.setFilename(filename);
    }

    public PopulationParser setFilename(String filename)
    {
        this.filename = filename;

        return this;
    }

    public String getFilename()
    {
        return this.filename;
    }


    public HashMap<String,String> parse()
    {
        HashMap<String,String> map = new HashMap<String,String>();
        System.out.println("Parsing file: "+ this.getFilename());
        if (this.getFilename() == null) {
            System.out.println("Error: File not found");
            return map;
        }
        try {

            File fXmlFile = new File(this.getFilename());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList resultsList = doc.getElementsByTagName("results");
            Node results = resultsList.item(0);
            NodeList resultList = results.getChildNodes();


            for (int temp = 0; temp < resultList.getLength(); temp++) {

                String name = "";
                String population = "";

                Node result = resultList.item(temp);
                NodeList resultDataList = result.getChildNodes();

                for (int i = 0; i < resultDataList.getLength(); i++) {
                    Node resultData = resultDataList.item(i);

                    if (resultData.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) resultData;

                        if (eElement.getAttribute("name").compareTo(COUNTRY_NAME) == 0) {
                            name = resultData.getFirstChild().getTextContent();
                        } else {
                            population = resultData.getFirstChild().getTextContent();
                        }
                        //System.out.println(eElement.getAttribute("name") + " " + resultData.getFirstChild().getTextContent());

                    }
                }

                if (name.compareTo("") > 0 && population.compareTo("") > 0 ) {
                    map.put(name, population);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

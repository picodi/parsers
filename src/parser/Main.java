package parser;


import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
        PopulationParser parser = new PopulationParser();
        HashMap<String, String> map = parser.parse();
        printMap(map);


    }

    public static void printMap(HashMap<String, String> map)
    {
        for (String key : map.keySet() ) {
            String value = map.get(key);
            System.out.println( key + " = " + value);
        }
    }
}

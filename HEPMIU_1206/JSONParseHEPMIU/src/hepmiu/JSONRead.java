package hepmiu;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
	
	public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new FileReader("orarendHepmiu.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            jsonObject = (JSONObject) jsonObject.get("orarendHepmiu");

            JSONArray jsonArray = (JSONArray) jsonObject.get("ora");

            for (int i = 0; i < jsonArray.size(); i++) {
            	
                JSONObject localObject = (JSONObject) jsonArray.get(i);
                
                System.out.println("\nÓra");
                System.out.println("  Tárgy: " + localObject.get("targy"));
                System.out.println("  Oktató: " + localObject.get("oktato"));
                System.out.println("  Szak: " + localObject.get("szak"));
                System.out.println("  Idõpont: ");

                
                JSONObject time = (JSONObject) localObject.get("idopont");
                
                
                System.out.println("    Nap: " + time.get("nap"));
                System.out.println("    Tól: " + time.get("tol"));
                System.out.println("    Ig: " + time.get("ig"));

                System.out.println("  Helyszín: " + localObject.get("helyszin"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}

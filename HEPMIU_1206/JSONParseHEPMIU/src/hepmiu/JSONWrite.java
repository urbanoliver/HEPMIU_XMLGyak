package hepmiu;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class JSONWrite {
	 public static void main(String[] args) {
    	 JSONParser JSONparser = new JSONParser();

         try (Reader reader = new FileReader("orarendHepmiu.json")) {
            JSONObject JSONObject = new JSONObject();

            JSONArray oraArray = new JSONArray();
            oraArray.add(createLesson(new String[]{"Szoftvertesztelés", "Dr. Hornyák Olivér","Mérnökinformatikus","Hétfõ","10:00","12:00","Inf/103"}));
            oraArray.add(createLesson(new String[]{"Szoftvertesztelés", "Dr. Hornyák Olivér","Hétfõ","12:00","14:00","Inf/103."}));
            oraArray.add(createLesson(new String[]{"WEB technológiák 1", "Agárdi Anita", "Mérnökinformatikus","Hétfõ","14:00","16:00","XXX. elõadó"}));
            oraArray.add(createLesson(new String[]{"WEB technológiák 1", "Agárdi Anita", "Mérnökinformatikus","Hétfõ","16:00","18:00","Inf/202"}));
            oraArray.add(createLesson(new String[]{"Mesterséges intelligencia", "Dr. Fazekas Levente", "Mérnökinformatikus","Kedd","10:00","12:00","XXXII.elõadó"}));
            oraArray.add(createLesson(new String[]{"Adatkezelés XML-ben", "Dr. Bednarik László", "Mérnökinformatikus","Kedd","12:00","14:00","XXXII.elõadó"}));
            oraArray.add(createLesson(new String[]{"WEB-es alkalmazások (C#)", "Árvai László Lajos", "Mérnökinformatikus","Kedd","14:00","16:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"WEB-es alkalmazások (C#)", "Dr. Mileff Péter", "Mérnökinformatikus","Kedd","16:00","18:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"Adatkezelés XML-ben", "Dr. Bednarik László", "Mérnökinformatikus","Szerda","10:00","12:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"Integrált vállalati rendszerek", "Dr. Samad Dadvandipour", "Mérnökinformatikus","Szerda","12:00","14:00","X. A1/218."}));
            oraArray.add(createLesson(new String[]{"Integrált vállalati rendszerek", "Dr. Kulcsárné Dr. Forrai Mónika", "Mérnökinformatikus","Szerda","14:00","16:00","Inf/15"}));
            oraArray.add(createLesson(new String[]{"Mesterséges intelligencia", "Dr. Fazekas Levente", "Mérnökinformatikus","Csütörtök","10:00","12:00","I.elõadó"}));

            for (int i = 0; i < oraArray.size() ;i++) {
                JSONObject localObject = (JSONObject) oraArray.get(i);
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

            JSONObject oraObject = new JSONObject();
            oraObject.put("ora", oraArray);
            JSONObject.put("orarendHepmiu", oraObject);

            FileWriter file = new FileWriter("orarendHepmiu.json");
            file.write(JSONObject.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static JSONObject createLesson(String[] data) {
        JSONObject localObject = new JSONObject();

        localObject.put("targy", data[0]);
        localObject.put("oktato", data[1]);
        localObject.put("szak", data[2]);
        
        
        JSONObject timeObject = new JSONObject();
        
        timeObject.put("nap",data[3]);
        timeObject.put("tol",data[4]);
        timeObject.put("ig",data[5]);
        localObject.put("idopont",timeObject);
        localObject.put("helyszin", data[6]);

        return localObject;
    }

}

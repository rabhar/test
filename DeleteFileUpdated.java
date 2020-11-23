import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class DeleteFile{

    public static void main(String[] args) {
        File directory = new File("allure-results");

        File[] fList = directory.listFiles();
        JSONParser parser = new JSONParser();

        for(File f: fList){

            try { 
                FileReader reader =  new FileReader(f.getAbsolutePath());
                Object obj = parser.parse(reader); 
                reader.close();

                JSONObject jsonObject =  (JSONObject) obj;

                String name = (String) jsonObject.get("status");
                JSONArray steps = (JSONArray) jsonObject.get("steps");
                int stepsSize = steps.size();
                
                if(name.equals("failed") && stepsSize == 0){
                    System.out.println(f.getAbsolutePath());
                    File file = new File(f.getAbsolutePath());
                    file.delete();
                }
            } catch (Exception e) {
                //System.out.println("exception : " + f.getName());
            }        
        }
    }
}


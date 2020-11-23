import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class DeleteFile{

    public static void main(String[] args) {
        File directory = new File("allure-results");

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        for(File f: fList){
            //System.out.println(f.getName());
            JSONParser parser = new JSONParser();
            try { 
                Object obj = parser.parse(new FileReader(f.getAbsolutePath())); 

                JSONObject jsonObject =  (JSONObject) obj;

                String name = (String) jsonObject.get("status");
                //System.out.println(name);  
                JSONArray cars = (JSONArray) jsonObject.get("steps");
                int s = cars.size();
                //System.out.println(s);
                if(name.equals("failed") && s == 0){
                    System.out.println(f.getPath());

                    
                    File file = new File(f.getPath());

                    file.setWritable(true);
                    if (file.exists() && file.isFile()) {
                        System.out.println("file exists");
                        if(file.delete()){
                            System.out.println("File deleted");
                        }else{
                            System.out.println("unable to delete the file");
                        }

                    }
                }
                
            } catch (Exception e) {
                //System.out.println("exception : " + f.getName());
            }
                        
        }
    }
}


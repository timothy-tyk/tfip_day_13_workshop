package workshop13.workshop13.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import workshop13.workshop13.models.Contacts;

public class IOUtils {
  
  public void writeToFile(Contacts contact, ApplicationArguments appArgs){
    String directoryFolder = System.getProperty("user.dir")+ appArgs.getOptionValues("dataDir").get(0);
    try {
      
      BufferedWriter bfw = new BufferedWriter(new FileWriter(directoryFolder+"/"+contact.getId()));
      bfw.write(contact.getName()+"\n");
      bfw.write(contact.getEmail()+"\n");
      bfw.write(contact.getPhoneNumber()+"\n");
      bfw.write(contact.getDateOfBirth().toString()+"\n");
      bfw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void getAllContacts(Model model, ApplicationArguments appArgs){
    String directoryFolder = System.getProperty("user.dir")+ appArgs.getOptionValues("dataDir").get(0);
    Map<String, String> allContacts = new HashMap<String, String>();
    for(File file: new File(directoryFolder).listFiles()){
      String id = file.getName();
      try {
        BufferedReader bfr = new BufferedReader(new FileReader(directoryFolder+"/"+id));
        String name = bfr.readLine();
        allContacts.put(id, name);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    model.addAttribute("allContacts", allContacts);
  }

  public void getContactById(Model model, String id, ApplicationArguments appArgs){
    String directoryFolder = System.getProperty("user.dir")+ appArgs.getOptionValues("dataDir").get(0);
    List<String> contactDetails = new ArrayList<String>();
    try {
      BufferedReader bfr = new BufferedReader(new FileReader(directoryFolder+"/"+id));
      String line;
      while((line = bfr.readLine())!=null){
        contactDetails.add(line);
      }
      model.addAttribute("contactDetails",contactDetails);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

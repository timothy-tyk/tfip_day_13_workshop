package workshop13.workshop13.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import workshop13.workshop13.models.Contacts;
import workshop13.workshop13.utils.IOUtils;

@Controller
@RequestMapping("/contact")
public class ContactController {
  @GetMapping()
  public String showContactForm(Model model){
    Contacts contacts = new Contacts();
    System.out.println(contacts.getId());
    model.addAttribute("contact", contacts);
    return "contact";
  }  

  @Autowired
  IOUtils io;

  @Autowired
  ApplicationArguments appArgs;
  @PostMapping()
  public String postContactForm(Model model, @Valid @ModelAttribute("contact") Contacts contacts, BindingResult bindingResult){
    // System.out.println(contacts.getName());
    if(bindingResult.hasErrors()){
      return "contact";
    }
    // Cannot instantiate new component, autowiring will not work
    // workshop13.workshop13.utils.IOUtils io = new workshop13.workshop13.utils.IOUtils();
    
    io.writeToFile(contacts, appArgs);
    model.addAttribute("contact", contacts);
    return "contact";
  }

  @GetMapping("/all")
  public String showAllContacts(Model model){
    // IOUtils io = new IOUtils(); // Cannot instantiate new component, autowiring will not work

    io.getAllContacts(model, appArgs);
    return "contactList";
  }

  @GetMapping("/{id}")
  public String showContactById(Model model, @PathVariable String id){
    // IOUtils io = new IOUtils(); // Cannot instantiate new component, autowiring will not work
    io.getContactById(model, id, appArgs);
    return "contactDetails";
  }
}

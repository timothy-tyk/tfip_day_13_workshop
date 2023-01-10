package workshop13.workshop13.models;

import java.time.LocalDate;
import java.util.Random;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contacts {

  @NotEmpty(message = "Enter a Name")
  @Size(min = 3, max = 64)
  private String name;

  @Email(message = "Enter a valid email")
  private String email;

  @Min(value = 7, message = "Enter at least 7 digits ")
  @Pattern(regexp="[0-9]{7,}",message = "")
  private String phoneNumber;

  @NotNull(message = "Enter a Date of Birth")
  @Past(message = "Must be born in the past!")
  private LocalDate dateOfBirth;

  private String id;

  // Constructor
  public Contacts() {
    // this.id="abc12345";
    this.id = generateHexString(8);
  }

  public Contacts(String name,String email,String phoneNumber,LocalDate dateOfBirth) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
  }
  
  

  // Getters and Setters
  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  // Methods
  public String generateHexString(int numChars){
    String hexString="";
    for(Integer i=0;i<numChars;i++){
      Random random = new Random();
      Boolean letterOrDigit = random.nextBoolean();
      if(letterOrDigit){
        //letter
        Integer ascii = random.nextInt(97, 123); //122 + 1 to get 122 inclusive
        hexString+=Character.toString(ascii);
        
      }else{
        Integer ascii = random.nextInt(48, 58); //57+1 to get 58 inclusive
        hexString+=Character.toString(ascii);
      }
    }
    return hexString;
  }
}

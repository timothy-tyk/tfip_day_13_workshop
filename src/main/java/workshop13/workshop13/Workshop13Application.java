package workshop13.workshop13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop13Application {

	public static void main(String[] args) {
		SpringApplication.run(Workshop13Application.class, args);
		// mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=/opt/tmp/data
		String dataDirOption = args[0];
		if(dataDirOption==null){
			System.err.println("Please Specify Data Directory Option as such: mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=/opt/tmp/data");
			System.exit(1);
		}

		String fileDirectory = args[0].replace("--dataDir=", "").concat("/");
		// System.getProperty("user.dir") -> get present working directory
		Path path = Paths.get(System.getProperty("user.dir")+fileDirectory);
		if(!Files.isDirectory(path)){
			try {
				Files.createDirectories(path);				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}

}

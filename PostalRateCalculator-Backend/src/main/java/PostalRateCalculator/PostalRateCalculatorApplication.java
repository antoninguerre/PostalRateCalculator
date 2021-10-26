package PostalRateCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PostalRateCalculatorApplication {

    public static void main(String[] args) { SpringApplication.run(PostalRateCalculatorApplication.class, args); }

    // Use a request mapping to indicate that when the user navigates to this page
    // that the user will see this. Typically this is used to return information
    @RequestMapping("/")
    public String greeting(){
        return "Hello world!";
    }
}

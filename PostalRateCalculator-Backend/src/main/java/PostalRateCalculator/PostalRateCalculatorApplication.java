package PostalRateCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PostalRateCalculatorApplication {

    public static void main(String[] args) { SpringApplication.run(PostalRateCalculatorApplication.class, args); }

    @RequestMapping("/")
    public String greeting(){
        return "The Postal Rate Calculator application's backend is running!";
    }
}

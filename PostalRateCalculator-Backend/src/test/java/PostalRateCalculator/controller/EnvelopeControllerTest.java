package PostalRateCalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("IntegrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EnvelopeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculatePostalRateInvalidWidthSyntax() throws Exception{
        // Initialize variables including an invalid width syntax
        String width = "invalidSyntax@#$", length = "100", weight = "23", sizeUnit = "mm", weightUnit = "in";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid syntax"));
    }

    @Test
    public void testCalculatePostalRateInvalidLengthSyntax() throws Exception{
        // Initialize variables including an invalid width syntax
        String width = "200", length = "invalidSyntax@#$", weight = "23", sizeUnit = "mm", weightUnit = "in";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid syntax"));
    }

    @Test
    public void testCalculatePostalRateInvalidWeightSyntax() throws Exception{
        // Initialize variables including an invalid width syntax
        String width = "200", length = "100", weight = "invalidSyntax@#$", sizeUnit = "mm", weightUnit = "in";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid syntax"));
    }



    @Test
    private void testCreateEnvelope () throws Exception {
        String width = "200", length = "100", weight = "100", sizeUnit = "mm", weightUnit = "g";

        mockMvc.perform(post("/envelope")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"width\":200.0,\"length\":100.0,\"weight\":100.0,\"sizeUnit\":\"Millimeters\",\"weightUnit\":\"Grams\"}"));
    }
}

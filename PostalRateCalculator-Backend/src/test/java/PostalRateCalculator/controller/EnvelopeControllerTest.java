package PostalRateCalculator.controller;

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

    @Test
    public void testCalculatePostalRateInvalidWidthSyntax() throws Exception{
        // Initialize variables including an invalid width syntax
        String width = "invalidSyntax@#$", length = "200", weight = "23", sizeUnit = "mm", weightUnit = "in";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid width syntax."));
    }

    @Test
    public void testCalculatePostalRateInvalidLengthSyntax() throws Exception{
        // Initialize variables including an invalid length syntax
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
                .andExpect(status().reason("Invalid length syntax."));
    }


    @Test
    public void testCalculatePostalRateInvalidWeightSyntax() throws Exception{
        // Initialize variables including an invalid weight syntax
        String width = "200", length = "200", weight = "invalidSyntax@#$", sizeUnit = "mm", weightUnit = "in";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid weight syntax."));
    }


    @Test
    public void testInvalidOrNoSelectedSizeUnit() throws Exception{
        // Initialize variables including an empty size unit
        String width = "200", length = "200", weight = "200", emptySizeUnit = "", weightUnit = "g";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", emptySizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid or empty size unit."));

        // Initialize an invalid size unit
        String invalidSizeUnit = "this is not a size unit";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", invalidSizeUnit)
                .param("weightUnit", weightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid or empty size unit."));
    }


    @Test
    public void testInvalidOrNoSelectedWeightUnit() throws Exception{
        // Initialize variables including an empty weight unit
        String width = "200", length = "200", weight = "200", sizeUnit = "mm", emptyWeightUnit = "";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", emptyWeightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid or empty weight unit."));

        // Initialize an invalid weight unit
        String invalidWeightUnit = "this is not a weight unit";

        // Try to calculate the postal rate, and expect an error to be thrown
        mockMvc.perform(post("/postal_rate_calculator")
                .param("width", width)
                .param("length", length)
                .param("weight", weight)
                .param("sizeUnit", sizeUnit)
                .param("weightUnit", invalidWeightUnit)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid or empty weight unit."));
    }
}

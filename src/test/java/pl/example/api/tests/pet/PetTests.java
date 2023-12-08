package pl.example.api.tests.pet;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;

public class PetTests {
    private static Logger logger = LogManager.getLogger(PetTests.class);
    private AddPetResponseDto actualPet;
    private Response response;

    @Test
    public void addPetTestWithPetDto() {
        AddPetRequestDto pet = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + pet);

        actualPet = AddANewPetToTheStoreRequest.addANewPetToTheStore(pet, 200);
        logger.info("Actual Pet: " + actualPet);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualPet).describedAs("Response Pet should be equal to request Pet")
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(pet);

            softly.assertThat(actualPet.getId()).describedAs("Wrong value in response Pet id").isNotNull();
            softly.assertThat(actualPet.getId()).describedAs("Wrong value in response Pet id").isGreaterThan(0);
        });
    }

}

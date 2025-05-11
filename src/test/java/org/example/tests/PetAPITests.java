package org.example.tests;


import com.example.petstore.model.Pet;
import org.assertj.core.api.SoftAssertions;
import org.example.RestClient;
import org.example.enums.PetStoreEndpoints;
import org.example.extensions.RestClientResolver;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@ExtendWith({
        RestClientResolver.class,
})
public class PetAPITests {
    @ParameterizedTest
    @MethodSource("getPetIds")
    void testGetPetById(int petId, RestClient client) {
        Pet pet = client.get(PetStoreEndpoints.FIND_PET_BY_ID.buildUrl(petId), 200).as(Pet.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(pet.getId())
                .as("Check pet ID")
                .isEqualTo(petId);
        softly.assertThat(pet.getStatus().getValue())
                .as("Check pet status")
                .isIn("available", "pending", "sold");
        softly.assertThat(pet.getCategory())
                .as("Category should not be null")
                .isNotNull();
        softly.assertThat(pet.getPhotoUrls())
                .as("Photo URLs")
                .isNotEmpty();
        softly.assertThat(pet.getTags())
                .as("Tags list")
                .isNotEmpty();
        softly.assertAll();
    }

    static Stream<Arguments> getPetIds() {
        return Stream.of(
                Arguments.of(111),
                Arguments.of(12345),
                Arguments.of(3)
        );
    }
}


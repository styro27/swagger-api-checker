package org.example.tests;

import com.example.petstore.model.Order;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.example.RestClient;
import org.example.enums.PetStoreEndpoints;
import org.example.extensions.RestClientResolver;
import org.example.tests.utils.OrderExpectedResult;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith({
        RestClientResolver.class,
})
public class StoreAPITests {
    @ParameterizedTest(name = "Check placing order: {0}")
    @MethodSource("provideTestData")
    void testPlaceOrder(Order input, OrderExpectedResult expected, RestClient client) {
        Order order = client
                .post(input, PetStoreEndpoints.PLACE_ORDER.getPath(), HttpStatus.SC_OK, Order.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(order.getId())
                .as("Check ID")
                .isEqualTo(expected.id);
        softly.assertThat(order.getPetId())
                .as("Pet ID does not match")
                .isEqualTo(expected.petId);
        softly.assertThat(order.getQuantity())
                .as("Quantity does not match")
                .isEqualTo(expected.quantity);
        softly.assertThat(order.getStatus())
                .as("Status does not match")
                .isEqualTo(expected.status);
        softly.assertThat(order.getComplete())
                .as("Check complete")
                .isEqualTo(expected.complete);
        softly.assertAll();
    }

    static Stream<Arguments> provideTestData() {
        Faker faker = new Faker();
        Random random = new Random();
        Order.StatusEnum[] statusOptions = Order.StatusEnum.values();
        return IntStream.range(0, 5)
                .mapToObj(i -> {
                    Order.StatusEnum randomStatus = statusOptions[random.nextInt(statusOptions.length)];
                    Order order = new Order()
                            .id(faker.number().numberBetween(1L, 100L))
                            .petId(faker.number().numberBetween(10L, 50L))
                            .quantity(faker.number().numberBetween(1, 10))
                            .shipDate(new Date())
                            .status(randomStatus)
                            .complete(faker.bool().bool());

                    return Arguments.of(order, OrderExpectedResult.from(order));
                });
    }

}

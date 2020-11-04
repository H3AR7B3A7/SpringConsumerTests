package be.dog.d.steven.springconsumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
public class SpringConsumerApplicationTests {

    @Autowired
    private ReservationClient client;

    @Test
    public void contextLoads() {

        var json = " [  {  \"id\":   \"1\"   ,    \"name\":   \"Jane\"   }  ]  ";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/reservations"))
                .willReturn(WireMock
                        .aResponse()
                        .withBody(json)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_OK)));

        Flux<Reservation> reservations = this.client.getAllReservations();
        StepVerifier
                .create(reservations)
                .expectNextMatches(r -> r.getId()!=null && r.getName().equalsIgnoreCase("Jane"))
                .verifyComplete();

    }

}

package com.pizza.restcontroller;

import com.pizza.dto.ClientDto;
import com.pizza.entity.Client;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    @Test
    public void saveClient() {
        ClientDto clientDto = new ClientDto.ClientDtoBuilder()
                .setFirstNameForDto("Ion")
                .setLastNameForDto("Zamfirescu")
                .setPhoneNumberForDto("0720111111")
                .setDateOfBirthForDto(LocalDate.of(1988, 9, 11))
                .setAddressForDto("Ploiesti")
                .buildDto();

        HttpEntity<ClientDto> entity = new HttpEntity<>(clientDto, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(createUrlWithPort("/clients"), HttpMethod.POST, entity, String.class);

        String expected = "{\n" +
                "    \"firstName\": \"Ion\",\n" +
                "    \"lastName\": \"Zamfirescu\",\n" +
                "    \"phoneNumber\": \"0720111111\",\n" +
                "    \"dateOfBirth\": \"1988-09-11\",\n" +
                "    \"address\": \"Ploiesti\",\n" +
                "}";

        try {
            JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findAllClients() {
        HttpEntity<Client> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(createUrlWithPort("/clients?all=true"), HttpMethod.GET, entity, String.class);

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"firstName\": \"Andrei\",\n" +
                "        \"lastName\": \"Alexandrescu\",\n" +
                "        \"phoneNumber\": \"0720000000\",\n" +
                "        \"dateOfBirth\": \"1980-08-14\",\n" +
                "        \"address\": \"Ploiesti\",\n" +
                "        \"clientCode\": \"CDEF73DD7FA1911ECA91900059A3C7A00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"firstName\": \"Marin\",\n" +
                "        \"lastName\": \"Stefanescu\",\n" +
                "        \"phoneNumber\": \"0720000050\",\n" +
                "        \"dateOfBirth\": \"1994-02-04\",\n" +
                "        \"address\": \"Ploiesti\",\n" +
                "        \"clientCode\": \"CDEF7437BFA1911ECA91900059A3C7A00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"firstName\": \"Andrei\",\n" +
                "        \"lastName\": \"Albulescu\",\n" +
                "        \"phoneNumber\": \"0720002340\",\n" +
                "        \"dateOfBirth\": \"1999-09-22\",\n" +
                "        \"address\": \"Ploiesti\",\n" +
                "        \"clientCode\": \"CDEF752C9FA1911ECA91900059A3C7A00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"firstName\": \"Ion\",\n" +
                "        \"lastName\": \"Constantinescu\",\n" +
                "        \"phoneNumber\": \"0723001004\",\n" +
                "        \"dateOfBirth\": \"1979-09-22\",\n" +
                "        \"address\": \"Ploiesti\",\n" +
                "        \"clientCode\": \"CDEF75475FA1911ECA91900059A3C7A00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"firstName\": \"Marius\",\n" +
                "        \"lastName\": \"Stefanescu\",\n" +
                "        \"phoneNumber\": \"0724456097\",\n" +
                "        \"dateOfBirth\": \"1975-07-19\",\n" +
                "        \"address\": \"Ploiesti\",\n" +
                "        \"clientCode\": \"CDEF755C5FA1911ECA91900059A3C7A00\"\n" +
                "    }\n" +
                "]";

        try {
            JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findClientByIdTest() {
        HttpEntity<Client> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createUrlWithPort("/clients/single?id=1"), HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Andrei\",\n" +
                "    \"lastName\": \"Alexandrescu\",\n" +
                "    \"phoneNumber\": \"0720000000\",\n" +
                "    \"dateOfBirth\": \"1980-08-14\",\n" +
                "    \"address\": \"Ploiesti\",\n" +
                "    \"clientCode\": \"CDEF73DD7FA1911ECA91900059A3C7A00\"\n" +
                "}";

        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String createUrlWithPort(String uri) {
        return "http://localhost:" + port + "/api" + uri;
    }
}

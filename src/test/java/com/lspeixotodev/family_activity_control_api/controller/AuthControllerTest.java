package com.lspeixotodev.family_activity_control_api.controller;

import com.lspeixotodev.family_activity_control_api.config.TestConfigs;
import com.lspeixotodev.family_activity_control_api.dto.authentication.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.dto.authentication.LoginDTO;
import com.lspeixotodev.family_activity_control_api.integrationtests.AbstractIntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/*
* Integration tests use the same ApplicationContext (unless specifically set not to).
* The issue with that is that one of the tests can make changes in the context that
*  would affect the other integration tests, like changing state of some beans.
* For this reason there is an annotation @DirtiesContext which restores/cleans the
*  effects on the context after this specific test.
* This annotation is computation expensive, therefore you should use it only when necessary.
*
* @author J Asgarov (https://stackoverflow.com/users/12038714/j-asgarov)
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
@ActiveProfiles("it")
@DisplayName("Authentication Controller (Integration Tests)")
public class AuthControllerTest extends AbstractIntegrationTest {

    @LocalServerPort
    int port;

    private static JWTAuthResponse jwtAuthResponse;

    @Test
    @Order(0)
    @DisplayName("Test Given LoginDTO When Sign Then Return JWTAuthResponse Object")
    public void givenAccountCredentials_WhenSign_ThenReturn_JWTAuthResponseObject() {
        LoginDTO user = new LoginDTO("admin", "admin");

        jwtAuthResponse = given()
                .basePath("/api/v1/auth/login")
                .port(this.port)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(JWTAuthResponse.class);

        assertNotNull(jwtAuthResponse.getAccessToken());
        assertNotNull(jwtAuthResponse.getRefreshToken());
        assertEquals("Bearer", jwtAuthResponse.getTokenType());
        assertTrue(jwtAuthResponse.getAuthenticated());

    }
}

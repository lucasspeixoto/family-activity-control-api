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

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
@ActiveProfiles("it")
@DisplayName("Authentication Controller (Integration Tests)")
public class AuthControllerTests extends AbstractIntegrationTest {

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

package com.lspeixotodev.family_activity_control_api.integrationtests.swagger;

import com.lspeixotodev.family_activity_control_api.FamilyActivityControlApiApplication;
import com.lspeixotodev.family_activity_control_api.integrationtests.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = FamilyActivityControlApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @LocalServerPort
    int port;

    @Test
    public void showDisplaySwaggerUiPage() {
        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(this.port)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        assertTrue(content.contains("Swagger UI"));
    }

}

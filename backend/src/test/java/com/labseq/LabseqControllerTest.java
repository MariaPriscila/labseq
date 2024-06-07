package com.labseq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabseqControllerTest {

    @Test
    void testLabseqNegativeIndex() {
        given()
                .when().get("/labseq/-1")
                .then()
                .statusCode(400)
                .body(is("Number must be 0 or greater"));
    }

    @Test
    void testLabseqEndpoint() {
        given()
                .when().get("/labseq/10000")
                .then()
                .statusCode(200)
                .body(is("9062959782884117635"));
    }
}

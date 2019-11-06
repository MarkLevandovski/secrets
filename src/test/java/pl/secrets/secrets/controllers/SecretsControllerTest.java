package pl.secrets.secrets.controllers;

import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecretsControllerTest {

    @Test
    public void testFullFlow() throws JSONException {
        String url = "api/secrets/";
        JSONObject json = new JSONObject();
        json.put("title", "mdy suspder secresddt lnot");

        String id = given().log().everything()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json.toString())
                .post("api/secrets")
                .then()
                .assertThat()
                .statusCode(200).log().everything().extract().path("id");

        get(url + id + "")
                .then()
                .log()
                .all()
                .statusCode(200)
                .contentType("application/json")
                .assertThat()
                .body("id", equalTo(id))
                .body("title", equalTo(json.get("title")))
                .body("createdAt", notNullValue());

        get(url)
                .then()
                .log()
                .all()
                .statusCode(200)
                .contentType("application/json");

        delete(url + id)
                .then()
                .log()
                .all()
                .statusCode(200);

        get(url + id)
                .then()
                .log()
                .all()
                .statusCode(404);

        get(url)
                .then()
                .log()
                .all()
                .statusCode(200)
                .contentType("application/json");
    }
}
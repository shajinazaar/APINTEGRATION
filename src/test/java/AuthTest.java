import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTest extends BaseTest {

    @Test
    public void testLogin() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        String token = response.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);
    }
}

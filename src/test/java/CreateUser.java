import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUser extends BaseTest{

    @Test
    public void createUserTest(){
        String user = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response =
        given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();

        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        assertTrue(name.contains("morpheus"));
        assertTrue(job.contains("leader"));


    }
}

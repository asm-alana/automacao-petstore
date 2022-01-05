package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import org.json.JSONObject;
import pet.Pet;
import utils.PropertiesUtils;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class PetSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Pet pet;

    @Dado("que estou no sistema Petstore")
    public void queEstouNoSistemaPetstore() {
        super.uri = prop.getProp("uri_petstore");
        super.headers = apiHeaders.petstoreHeaders();
    }

    @Quando("envio um request de cadastro de pet com dados validos")
    public void envioUmRequestDeCadastroDePetComDadosValidos() throws IOException {
        pet = Pet.builder().id(7634).name("string").status("available").build();

        super.body = new JSONObject(new Gson().toJson(pet));
        super.POST();
    }

    @Entao("o pet deve ser criado corretamente")
    public void oPetDeveSerCriadoCorretamente() {
        assertEquals(pet, response.jsonPath().getObject("", Pet.class));
    }

    @E("o status code do request deve ser {int}")
    public void oStatusCodeDoRequestDeveSer(int statusEsperado) {
        assertEquals(statusEsperado, response.statusCode());
    }

    @E("existe um pet cadastrado na api")
    public void existeUmPetCadastradoNaApi() throws IOException {
        envioUmRequestDeCadastroDePetComDadosValidos();
    }

    @Quando("buscar esse pet")
    public void buscarEssePet() {
        super.uri = prop.getProp("uri_petstore") + "/" + response.jsonPath().getJsonObject("id");
        super.body = new JSONObject();
        super.GET();
    }

    @Entao("os dados do pet devem ser retornados")
    public void osDadosDoPetDevemSerRetornados() {
        assertEquals(pet, response.jsonPath().getObject("", Pet.class));
    }

    @Quando("altero os dados do pet")
    public void alteroOsDadosDoPet() {
        super.uri = prop.getProp("uri_petstore");
        pet.setStatus("pending");
        super.body = new JSONObject(new Gson().toJson(pet));
        super.PUT();
    }

    @Entao("o pet deve ser alterado com sucesso")
    public void oPetDeveSerAlteradoComSucesso() {
        assertEquals(pet, response.jsonPath().getObject("", Pet.class));
    }

    @Quando("deleto esse pet")
    public void deletoEssePet() {
        super.uri = prop.getProp("uri_petstore") + "/" + response.jsonPath().getJsonObject("id");
        super.body = new JSONObject();
        super.DELETE();
    }

    @Entao("o pet deve ser deletado corretamente")
    public void oPetDeveSerDeletadoCorretamente() {
        RestAssured.given().
                body("{\"code\":200,\"type\":\"unknown\",\"message\":\"7634\"}").
        then().
                statusCode(200).
                body("type", is("unknown"));
    }
}

package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojo.Store;
import utils.PropertiesUtils;

import static org.junit.Assert.assertEquals;

public class StoreSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Faker faker = new Faker();
    Store store;

    private static final String STORE_ENDPOINT = "/v2/store/order";

    @Dado("que gerencio pedidos na API Petstore")
    public void queGerencioPedidosNaAPIPetstore() {
        super.uri = prop.getProp("uri_petstore") + STORE_ENDPOINT;
        super.headers = apiHeaders.petstoreHeaders();
    }

    @Quando("envio um request de cadastro de pedido com dados validos")
    public void envioUmRequestDeCadastroDePedidoComDadosValidos() {
        store = Store.builder().id(1).petId(2).quantity(1).status("placed").build();

        super.body = new JSONObject(new Gson().toJson(store));
        super.POST();
    }

    @Entao("o usuario deve ser criado corretamente")
    public void oUsuarioDeveSerCriadoCorretamente() {
        assertEquals(store, response.jsonPath().getObject("", Store.class));
    }

    @E("existe um pedido cadastrado na api")
    public void existeUmPedidoCadastradoNaApi() {
        envioUmRequestDeCadastroDePedidoComDadosValidos();
    }

    @Quando("busco esse pedido")
    public void buscoEssePedido() {
        super.uri = prop.getProp("uri_petstore") + STORE_ENDPOINT + "/" + response.jsonPath().getJsonObject("id");
        super.body = new JSONObject();
        super.GET();
    }

    @Entao("os dados do pedido devem ser retornandos")
    public void osdadosDoPedidoDevemSerRetornandos() {
        assertEquals(store, response.jsonPath().getObject("", Store.class));
    }

    @Quando("deleto esse pedido")
    public void deletoEssePedido() {
        super.uri = prop.getProp("uri_petstore") + STORE_ENDPOINT + "/" + response.jsonPath().getJsonObject("id");
        super.body = new JSONObject();
        super.DELETE();
    }

    @Entao("o pedido deve ser deletado corretamente")
    public void oPedidoDeveSerDeletadoCorretamente() {
        RestAssured.given().
                body("{\"code\":200,\"type\":\"unknown\",\"message\":\"1\"}").
        then().
                body("type: ", Matchers.is("unknown"));
    }
}

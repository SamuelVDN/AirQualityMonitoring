package API;

import okhttp3.*;
import com.google.gson.*;

import java.util.ArrayList;

public class OpenMeteo {

    private static String BASE_URL = "";

    public String BUILD_URL (String latitude, String longitude, String particules) {
        String Finalurl = "https://air-quality-api.open-meteo.com/v1/air-quality?" +
                "latitude="+ latitude + "&longitude="+longitude+
                "&hourly="+ particules +
                "&timezone=America/Sao_Paulo";

        return Finalurl;
    }

    private final OkHttpClient client = new OkHttpClient();

    public ArrayList<String> findPolution(String latitude, String longitude) {

        ArrayList<String> result = new ArrayList<>();

        BASE_URL = BUILD_URL(latitude, longitude, "pm2_5,pm10,carbon_dioxide");

        try {
            Request request = new Request.Builder().url(BASE_URL).build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                result.add("Error HTTP: " + response.code());
                result.add("Error");
                result.add("Error");
                return result;
            }


            // Lê o corpo da resposta
            String jsonData = response.body().string();

            // Converte em JSON
            JsonObject json = JsonParser.parseString(jsonData).getAsJsonObject();
            JsonObject hourly = json.getAsJsonObject("hourly");

            // Extrai as listas de valores
            String pm25 = hourly.getAsJsonArray("pm2_5").get(0).getAsString();
            String pm10 = hourly.getAsJsonArray("pm10").get(0).getAsString();
            String co2 = hourly.has("carbon_dioxide")
                    ? hourly.getAsJsonArray("carbon_dioxide").get(0).getAsString()
                    : "não disponível";

            // Retorna texto formatado


            result.add(pm25);
            result.add(pm10);
            result.add(co2);

            return result;


        } catch (Exception e) {
            e.printStackTrace();
            result.add("Error: " + e.getMessage());
            result.add("Error");
            result.add("Error");
            return result;
        }

    }
}
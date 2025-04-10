package life.eter.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class CurrencyUtil {
    public static Float getExchangeRate(Date date, String from, String to) {
        try {
            String apiUrl = String.format(
                    "https://api.frankfurter.dev/v1/" + date.toString() +  "?base=" + getName(from) +  "&symbols=" + getName(to)

            );

            //System.out.println(apiUrl);

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Error: HTTP response code = " + status);
                return -1f;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            con.disconnect();

            JSONObject jsonResponse = new JSONObject(content.toString());
            return jsonResponse.getJSONObject("rates").getFloat(getName(to));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1f;
    }


    public static String getName(String pais) {
        Map<String, String> countryToCurrencyMap = new HashMap<>();
        countryToCurrencyMap.put("bra", "BRL");
        countryToCurrencyMap.put("usa", "USD");
        countryToCurrencyMap.put("chn", "CNY");

        return countryToCurrencyMap.getOrDefault(pais.toLowerCase(), pais);
    }
}
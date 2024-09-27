package apiService;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class NaverApiService {
    static NaverApiService naverApiService = null;
    private NaverApiService(){};
    public static NaverApiService getNaverApiService(){
        if (naverApiService ==null){
            naverApiService = new NaverApiService();
        }
        return naverApiService;
    }
    String myId = "myId";
    String myApi = "apiKey";
    public String getTranslate(String msg){
        String translate= "";

        try{
            String text = URLEncoder.encode(msg, StandardCharsets.UTF_8);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID",myId );
            con.setRequestProperty("X-NCP-APIGW-API-KEY", myApi);
            // post request
            String postParams = "source=en&target=ko&text="+msg;
//        System.out.println(postParams);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else{
                System.out.println(responseCode);
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine())!=null){
                response.append(inputLine);
            }
            br.close();
            translate = getTranslatedText(response.toString());
        }catch (Exception e){
            System.out.println(e);
        }
        return translate;
    }
    public String getTranslatedText(String response){
        JSONObject jsonObj = new JSONObject(response);
        JSONObject messageObj = jsonObj.getJSONObject("message");
        JSONObject resultObj = messageObj.getJSONObject("result");
        String translatedText = resultObj.getString("translatedText");

        return translatedText;
    }

}

package apiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GptApiService {
    private static GptApiService apiService =null;
    public static synchronized GptApiService getApiService(){
        if(apiService == null){
            apiService = new GptApiService();
        }
        return apiService;
    }

    public String connectChatGPT(String message) throws IOException {
        String url = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";
        String apiKey = "apikey";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\" : \"" + model + "\", \"messages\": [{\"role\" " +
                    ": \"user\", \"content\":\"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                String jsonData = response.toString();
                return getMessage(jsonData);
            } else {
                System.out.println("API Request failed with HTTP Code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getMessage(String jsonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("choices");
        JSONObject jsonMessage = jsonArray.getJSONObject(0).getJSONObject("message");
        String content = jsonMessage.getString("content");
        return content;
    }

    public String makingPrompt(String englishSentence, String userEnglish){
        String prompt = "너는 나의 영어 선생님이야. " +
                "그리고 나는 지금 영어 대사를 외우기 연습 중이고, " +
                "내가 말해야할 원래 문장은 " +
                "'" + englishSentence +"'이 문장이야 (나는 지금 이 문장을 자동으로 보내서" +
                "어떤 문장을 보냈는지 몰라) 그리고 이건 내가 외운 문장이야 '"+
                userEnglish + "' 틀린게 있거나 더 좋은 표현이 있으면 날 알려줘, " +
                "설명은 한국말로 해주고, 예시는 영어로 알려줘, 100자 이내로." +
                "만약 내가 잘 말 했으면 그냥 잘 말했다고만 해주면 대";
        return prompt;
    }
    public String AITranslatePrompt(ArrayList<String> lines){
        String prompt = "너는 나의 영어 선생님이야. " +
                "그리고 나는 지금 드라마의 한 장면을 연습 중인데 잘모르겠어 ";
        for (int i = 2; i < lines.size(); i++) {
            if(i == (lines.size()-1)){
                prompt += "이 장면 중 마지막 이 대사 '";
                prompt += lines.get(i)+"' 이 부분 자연스러운 한국어 번역과 이 문장에서 배울수있는 문법 알려줘, " +
                        "중요하거나 어려운 영단어나 숙어가있으면 알려줘";
            }
            prompt += lines.get(i);
        }
        return prompt;
    }

}

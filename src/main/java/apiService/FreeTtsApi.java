package apiService;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class FreeTtsApi {
    public void ttsService(String msg){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        VoiceManager voiceManager = VoiceManager.getInstance();
        String voiceName = "kevin";
        Voice voice = voiceManager.getVoice(voiceName);
        if (voice == null) {
            System.err.println("Cannot find a voice named " + voiceName + ". Please specify a different voice.");
            System.exit(1);
        }

        // 음성 활성화
        voice.allocate();
        // 텍스트를 음성으로 변환
        voice.speak(msg);
        // 음성 비활성화
        voice.deallocate();
    }
}

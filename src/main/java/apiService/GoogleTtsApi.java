package apiService;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import javazoom.jl.player.Player;

import java.io.*;

public class GoogleTtsApi {
    /**
     * Demonstrates using the Text to Speech client to synthesize text or ssml.
     *
     * @param text the raw text to be synthesized. (e.g., "Hello there!")
     * @throws Exception on TextToSpeechClient Errors.
     *
     */
    public static ByteString synthesizeText(String text) throws Exception {
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
            // Build the voice request
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                                .setLanguageCode("en-US") // languageCode = "en_us"
                                .setSsmlGender(SsmlVoiceGender.MALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
                                .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder()
                            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
                            .build();

            // Perform the text-to-speech request
            SynthesizeSpeechResponse response =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();

            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("output.wav")) {
                out.write(audioContents.toByteArray());
                return audioContents;
            }
        }
    }
    public static void playSound(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            try {
                Player player = new Player(bis);
                player.play();
            } catch (Exception e) {
                System.out.println("MP3 파일을 재생하는 동안 오류가 발생했습니다: " + e.getMessage());
            } finally {
                fis.close();
                bis.close();
            }
        } catch (Exception e) {
            System.out.println("MP3 파일을 로드하는 동안 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

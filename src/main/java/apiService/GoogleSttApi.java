package apiService;

//import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GoogleSttApi {

//    public static void sttService() throws Exception {
//
//        // 오디오 파일 경로
//        Path path = Paths.get("output.WAV"); // WAV 파일 경로로 변경
//        try (SpeechClient speechClient = SpeechClient.create()) {
//            byte[] data = Files.readAllBytes(path);
//            ByteString audioBytes = ByteString.copyFrom(data);
//            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.FLAC;
//
//            // Configure request with local raw PCM audio
//            RecognitionConfig config =
//                    RecognitionConfig.newBuilder()
//                            .setEncoding(encoding)
//                            .setLanguageCode("en-US")
//                            .setSampleRateHertz(44100)
//                            .build();
//
//            RecognitionAudio audio = RecognitionAudio.newBuilder()
//                    .setUri("gs://bucket_name/file_name.flac")
//                    .setContent(audioBytes).build();
//
//            // Use blocking call to get audio transcript
//            RecognizeResponse response = speechClient.recognize(config, audio);
//            List<SpeechRecognitionResult> results = response.getResultsList();
//
//            for (SpeechRecognitionResult result : results) {
//                // There can be several alternative transcripts for a given chunk of speech. Just use the
//                // first (most likely) one here.
//                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//                System.out.printf("Transcription: %s%n", alternative.getTranscript());
//            }
//        }
//    }
}

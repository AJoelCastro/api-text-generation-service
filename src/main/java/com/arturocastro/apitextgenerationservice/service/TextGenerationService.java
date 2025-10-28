package com.arturocastro.apitextgenerationservice.service;

import com.arturocastro.apitextgenerationservice.model.TGModel;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextGenerationService {

    private final OpenAIClient client;

    public TextGenerationService(@Value("${openai.api.key}") String apiKey) {
        client = new OpenAIOkHttpClient.Builder()
                .apiKey(apiKey)
                .build();
    }

    public Response getQuestion(TGModel tgm){
        if (tgm == null || tgm.getPrompt() == null || tgm.getPrompt().isEmpty()) {
            throw new IllegalArgumentException("Prompt vacío o nulo");
        }

        ResponseCreateParams params = ResponseCreateParams
                .builder()
                .model(ChatModel.GPT_4_1_MINI)
                .temperature(tgm.getTemperature())
                .maxOutputTokens(tgm.getMaxTokens())
                .input(tgm.getPrompt())
                .build();
        return client.responses().create(params);
    }

}
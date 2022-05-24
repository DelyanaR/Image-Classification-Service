package com.vmware.talentboost.ics.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImaggaController {
    private final String credentialsToEncode = "acc_eb9495636001a90" + ":" + "2c935dcbada672a6931eb05f219632cc";
    private final String basicAuth;
    private final String endpoint_url = "https://api.imagga.com/v2/tags";

    public ImaggaController() {
        this.basicAuth=Base64.getEncoder().encodeToString(this.credentialsToEncode.getBytes(StandardCharsets.UTF_8));
    }

    public String getTagsForImage(String imageUrl)  throws IOException{
        String url = this.endpoint_url + "?image_url=" + imageUrl;
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + this.basicAuth);

        BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String jsonResponse = connectionInput.readLine();

        connectionInput.close();

        return jsonResponse;
    }

}

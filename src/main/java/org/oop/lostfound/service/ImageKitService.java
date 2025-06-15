package org.oop.lostfound.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.oop.lostfound.config.ImageKitConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImageKitService {
    public static String uploadImage(File imageFile, String fileName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        try {
            HttpPost uploadFile = new HttpPost(ImageKitConfig.UPLOAD_URL);
            
            // Membuat header authorization 
            String auth = ImageKitConfig.PRIVATE_KEY + ":";
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            uploadFile.addHeader("Authorization", "Basic " + encodedAuth);
            
            // Membaca file sebagai byte array, lalu encode ke Base64
            byte[] fileContent = Files.readAllBytes(imageFile.toPath());
            String base64File = Base64.getEncoder().encodeToString(fileContent);
            
            // Multipart form-data
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("file", base64File);
            builder.addTextBody("fileName", fileName);
            builder.addTextBody("folder", "/lost-found-items/");
            
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            
            // Eksekusi request upload
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            String responseString = EntityUtils.toString(response.getEntity());
            
            // Parsing hasil respon
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseString);
            
            // Cek status sukses
            if (response.getStatusLine().getStatusCode() == 200) {
                return jsonNode.get("url").asText();
            } else {
                throw new IOException("Upload failed: " + responseString);
            }
            
        } finally {
            httpClient.close();
        }
    }
}
package com.wntrinh.assignment4.YelpAPI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class YelpAPI {
	private String apiKey;
	
	public YelpAPI() {
		// STEP #1: Replace API Key with your own!
		
		// EXAMPLE:
		// If your API key was:
		// w2WrLbbvkJbtDdCLeDADcu3J98c1FEDClCGuB4mG8c92DtEjdElUZRG6LGLLdSxyoCT4OYbF-8tnc1_2UbVmpb79pJkKeaSrI3arJyEZaTe4N8m3VqiZ4lZa6MM-ZXYx
		
		// Your code would be:
		// this.apiKey = "w2WrLbbvkJbtDdCLeDADcu3J98c1FEDClCGuB4mG8c92DtEjdElUZRG6LGLLdSxyoCT4OYbF-8tnc1_2UbVmpb79pJkKeaSrI3arJyEZaTe4N8m3VqiZ4lZa6MM-ZXYx";
		
		// UPDATE WITH YOUR API KEY BELOW
		this.apiKey = "";
	}
	
	public String makeAPICall(String term_param, double latitude_param, double longitude_param, String option_param) {
		
		String searchOption;
		if (option_param.equals("best-match")) {
			searchOption = "best_match";
		} else if (option_param.equals("review-count")) {
			searchOption = "review_count";
		} else {
			searchOption = option_param;
		}
	    
		try {
			term_param = URLEncoder.encode(term_param, StandardCharsets.UTF_8.toString())
                    .replace("+", "%20")  // Replace '+' with '%20' (optional)
                    .replace("*", "%2A");  // Replace '*' with '%2A' (optional)
		} catch (UnsupportedEncodingException err) {
			err.printStackTrace();
		}
		String apiUrl = "https://api.yelp.com/v3/businesses/search?latitude=" + latitude_param +
                "&longitude=" + longitude_param + "&term=" + term_param + "&categories=restaurants" + "&sort_by=" + searchOption + "&limit=10";
		
		System.out.println("API URL: " + apiUrl);
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create(apiUrl))
			    .header("accept", "application/json")
			    .header("Authorization", "Bearer " + apiKey)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
		
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 0 || (response.statusCode() >= 200 && response.statusCode() < 400)) {
                String responseBody = response.body();
                // convertToJson(responseBody, (some filename));
                return responseBody;
            } else {
                System.err.println("Request failed with code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException err) {
            err.printStackTrace();
        }
		return "";
	}
	
	public void convertToJson(String body, String filename) {
		try {
            // Open the file for writing (create or overwrite)
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            // Write the response content to the file
            writer.write(body);

            // Close the writer
            writer.close();

            // åSystem.out.println("Response saved to: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

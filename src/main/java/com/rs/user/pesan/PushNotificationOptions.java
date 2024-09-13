package com.rs.user.pesan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PushNotificationOptions {
    public static final String REST_API_KEY = "NDQxMThkMTctNzg5Zi00OWZjLWI3NTctZjIzYjk5Y2RhZmQy";
    public static final String APP_ID = "c924aa9d-5cc5-4264-9a48-62a6b17573b0";

    public static void sendMessageToAllUsers(String title, String message) {
        try {
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic " + REST_API_KEY);

            String strJsonBody = "{"
                    + "\"app_id\": \"" + APP_ID + "\","
                    + "\"included_segments\": [\"All\"],"
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"headings\": {\"en\": \"" + title + "\"},"
                    + "\"contents\": {\"en\": \"" + message + "\"}"
                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            try (OutputStream outputStream = con.getOutputStream()) {
                outputStream.write(sendBytes);
            }

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            String jsonResponse = mountResponseRequest(con, httpResponse);
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void sendMessageToUser(String title, String message, String userId) {
        try {
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic " + REST_API_KEY);

            String strJsonBody = "{"
                    + "\"app_id\": \"" + APP_ID + "\","
                    + "\"filters\": ["
                    + "{\"field\": \"external_user_id\", \"key\": \"external_id\", \"relation\": \"=\", \"value\": \"" + userId + "\"}"
                    + "],"
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"headings\": {\"en\": \"" + title + "\"},"
                    + "\"contents\": {\"en\": \"" + message + "\"}"
                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            try (OutputStream outputStream = con.getOutputStream()) {
                outputStream.write(sendBytes);
            }

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            String jsonResponse = mountResponseRequest(con, httpResponse);
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String mountResponseRequest(HttpURLConnection con, int httpResponse) throws IOException {
        BufferedReader bufferedReader;
        if (httpResponse >= HttpURLConnection.HTTP_OK && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
        }
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        return response.toString();
    }
}

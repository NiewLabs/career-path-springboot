package com.niewlabs.careerpath;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dash on 5/12/2015.
 */
public class HelperRW {

    public static HashMap<String, List<String>> readIn(String fileName) {

        URL fileUrl = MainController.class.getClassLoader().getResource(fileName);

        Gson gson = new Gson();
        Type ourMap = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        String codes_to_titles = null;
        try {
            codes_to_titles = new String(Files.readAllBytes(Paths.get(fileUrl.toURI())), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String, List<String>> jsonData = gson.fromJson(codes_to_titles, ourMap);
        return jsonData;
    }
}

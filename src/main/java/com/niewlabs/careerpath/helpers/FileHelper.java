package com.niewlabs.careerpath.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niewlabs.careerpath.MainController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dash on 5/12/2015.
 */
public class FileHelper {

    public static HashMap<String, List<String>> readIn(String fileName) {

        URL fileUrl = MainController.class.getClassLoader().getResource(fileName);

        Gson gson = new Gson();
        Type ourMap = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        String codes_to_titles = null;


        try {
            URI uri = fileUrl.toURI();

            Path path = null;

            if(uri.toString().contains("jar")) {
                FileSystem fs = null;
                try {
                    final Map<String, String> env = new HashMap<>();
                    final String[] array = uri.toString().split("!");
                    fs = FileSystems.newFileSystem(URI.create(array[0]), env);
                    path = fs.getPath(array[1]);
                }  finally {
                    // FIXME: Clean this up...Oh java...
                    try {
                        if(fs!=null) {
                            fs.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                path = Paths.get(uri);
            }

            codes_to_titles = new String(Files.readAllBytes(path), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String, List<String>> jsonData = gson.fromJson(codes_to_titles, ourMap);
        return jsonData;
    }
}

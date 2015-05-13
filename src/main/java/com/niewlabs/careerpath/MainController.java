package com.niewlabs.careerpath;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;


/**
 * Created by dash on 11/15/2014.
 */

@Controller
@EnableAutoConfiguration
public class MainController {

    String courseraUrl = "https://api.coursera.org/api/catalog.v1/courses?q=search&query={{nocCode}}&fields=language,links";
    // FIXME: Fix the path
    static Map<String, List<String>> nocToCoursera = HelperRW.readIn("./src/main/resources/public/client/data/groupings_to_coursera.json");

    @RequestMapping("/api")
    @ResponseBody
    String home() {
        // TODO: Return a formal definition of API here.
        String input = "Hi! Please use 'coursera' resources. Blueprint API definition will go here shortly.";
        return input;
    }

    @RequestMapping("/api/coursera")
    @ResponseBody
    String tag(@RequestParam(value = "nocCode") String noc) {
        if(noc.isEmpty()){
            return "Pass 'nocCode' request parameter.";
        }

        String result = "";
        // FIXME: Use all query items... or not.
        String query = nocToCoursera.get(noc).get(0);

        try {
            result = sendGet(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String sendGet(String noc) throws Exception {

        String url = courseraUrl.replace("{{nocCode}}",noc);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //private final String USER_AGENT = "Mozilla/5.0";
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }
}


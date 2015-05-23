package com.niewlabs.careerpath;

import com.niewlabs.careerpath.helpers.CourseraHelper;
import com.niewlabs.careerpath.helpers.FileHelper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * Created by dash on 11/15/2014.
 */

@Controller
@EnableAutoConfiguration
public class MainController {

    static Map<String, List<String>> nocToCoursera = FileHelper.readIn("public/client/data/groupings_to_coursera.json");

    @RequestMapping("/api")
    @ResponseBody
    String home() {
        // TODO: Return a formal definition of API here.
        String input = "Hi! Please use 'coursera' resources. Blueprint API definition will go here shortly.";
        return input;
    }

    @RequestMapping("/api/coursera")
    @ResponseBody
    String courseraGet(@RequestParam(value = "nocCode") String noc) {
        if(noc.isEmpty()){
            return "Pass 'nocCode' request parameter.";
        }

        String result = "";

        try {
            result = CourseraHelper.searchCoursera(noc, nocToCoursera);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}


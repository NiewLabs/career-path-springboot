package com.niewlabs.careerpath.helpers;

import java.util.List;
import java.util.Map;

/**
 * Created by dash on 5/23/2015.
 */
public class CourseraHelper {
    static String courseraUrl = "https://api.coursera.org/api/catalog.v1/courses?q=search&query={{queryTerm}}&fields=language,links";

    public static String searchCoursera(String noc, Map<String, List<String>> nocToCoursera) throws Exception {
        String query = "";
        // FIXME: Use all query items... or not.
        if(nocToCoursera.containsKey(noc)) {
            query = nocToCoursera.get(noc).get(0);
        } else {
            return "Bad nocCode!";
        }

        String url = courseraUrl.replace("{{queryTerm}}",query);

        StringBuffer response = HttpHelper.sendGetRequest(url);

        return response.toString();

    }
}

package com.niewlabs.careerpath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by dash on 11/15/2014.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
        openGui("http://localhost:80");
    }

    private static void openGui(String webUri) {
        URI theUri = null;
        try {
            theUri = new URI(webUri);

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop.getDesktop().browse(theUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // TODO: Show a popup asking to open the browser manually
        }
    }
}

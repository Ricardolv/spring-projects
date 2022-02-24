package com.richard.infrastructure.client;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Client4Seg {

    public static final String URL = "https://4seg.com.br/";
    public static final String DASH = "admin/dashboard";

    public static void main(String[] args) {

        try {

            final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

            Map<String, String> mapParamsLogin = new HashMap<>();
            mapParamsLogin.put("FormName", "existing");
            mapParamsLogin.put("seclogin", "on");
            mapParamsLogin.put("email", "marcos@hsjimoveis.com.br");
            mapParamsLogin.put("passwd", "#Hsjmarcos70");
            mapParamsLogin.put("checkbox", "1");
            mapParamsLogin.put("submit", "Iniciar sessão");

            Connection.Response loginResponse = Jsoup.connect(URL)
                    .userAgent(USER_AGENT)
                    .data(mapParamsLogin)
                    .execute();

            //out.println("loginResponse: " + loginResponse.body());


            Document documentDash = Jsoup.connect(URL + DASH)
                    .userAgent(USER_AGENT)
                    .cookies(loginResponse.cookies())
                    .get();

            //out.println("documentDash: " + documentDash.html());

            Elements newsHeadlines = documentDash.select("#mp-itn b a");



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

package com.garage48.kigaliair.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

@Controller
public class KigaliairController {
    private String appMode;

    @Autowired
    public KigaliairController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model) throws IOException {
        JSONObject json = readJsonFromUrl("https://api.thingspeak.com/channels/778383/fields/1.json?results=1");
        JSONArray jArr = json.getJSONArray("feeds");
        String aqi  = (String) jArr.getJSONObject(0).get("field1");

        json = readJsonFromUrl("https://api.thingspeak.com/channels/778383/fields/2.json?results=1");
        jArr = json.getJSONArray("feeds");
        String temp  = (String) jArr.getJSONObject(0).get("field2");

        json = readJsonFromUrl("https://api.thingspeak.com/channels/778383/fields/3.json?results=1");
        jArr = json.getJSONArray("feeds");
        String humidity  = (String) jArr.getJSONObject(0).get("field3");

        json = readJsonFromUrl("https://api.thingspeak.com/channels/778383/fields/4.json?results=1");
        jArr = json.getJSONArray("feeds");
        String co2  = (String) jArr.getJSONObject(0).get("field4");

        // System.out.println("Blah Blah " + json.toString());
        // System.out.println(json.get("field1"));
        model.addAttribute("aqi", aqi);
        model.addAttribute("temp", temp);
        model.addAttribute("humidity", humidity);
        model.addAttribute("CO2", co2);
        model.addAttribute("createdAt", json.getJSONObject("channel").get("created_at"));
        model.addAttribute("updatedAt", json.getJSONObject("channel").get("updated_at"));

        model.addAttribute("datetime", new Date());
        model.addAttribute("projectName", "KigaliAir");
        model.addAttribute("mode", appMode);

        return "index";
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

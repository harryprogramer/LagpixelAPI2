package com.http;


import org.json.JSONObject;
import util.Logger;

import javax.servlet.http.HttpServletRequest;
import static spark.Spark.*;

public class HTTPCore {
    public void initEndpoints(){
        post("/api", ((request, response) -> {
            JSONObject recvJSON;
            String json = request.body();
            if(json.length() <= 0){
                response.status(400);
                return "body is null";
            }
            try{
                recvJSON = new JSONObject(json);
            }catch (Exception e){
                e.printStackTrace();
                response.status(500);
                return e.getMessage();
            }
            Logger.Log_ln("Request from " + request.host(), Logger.Level.INFO, Logger.Type.HTTP);
            return Parser.idParser(recvJSON);
        }));
    }

    /*
    @GetMapping("/error")
    public String error(@RequestParam(value = "name", defaultValue = "World") String name, HttpEntity<String> httpEntity, HttpServletRequest request) {
        return "error test";
    }

    @PostMapping(path = "/api", produces= MediaType.APPLICATION_JSON_VALUE)
    public String api(String name, HttpEntity<String> httpEntity, HttpServletRequest request) {
        JSONObject recvJSON;
        String json = httpEntity.getBody();
        if(json == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "json structure not valid");
        }
        try{
            recvJSON = new JSONObject(json);
        }catch (Exception ignored){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "json structure not valid");
        }
        Logger.Log_ln("Request from " + request.getRemoteHost(), Logger.Level.INFO, Logger.Type.HTTP);
        return Parser.idParser(recvJSON);
    }

     */
}

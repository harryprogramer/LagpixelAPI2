package com.api;

import org.json.JSONObject;
import util.Logger;

class KickPlayer {
    static SocketCore getServer = SocketCore.getServer();
    public static String kick(String targetPlayer, String kickReason, String fromUser){
        JSONObject responseJSON;
        String message = getServer.sendMessage(ResponseJSON.SendQuestionToPaper("7", new JSONObject().put("player", targetPlayer).put("reason", kickReason).put("user", fromUser)));
        try{
            responseJSON = new JSONObject(message);
        }catch (Exception e){
            e.printStackTrace();
            Logger.Log_ln(e.getMessage(), Logger.Level.CRIT, Logger.Type.SYSTEM);
            return ResponseJSON.ERRORResponseToClientPaper("3", "incorrect paper response", "6");
        }
        if(responseJSON.getString("status").equalsIgnoreCase("OK")){
            return ResponseJSON.OKResponseToClient(null);
        }else{
            String errcode;
            String errorreason;
            try{
                errcode = responseJSON.getString("errorCode");
                errorreason = responseJSON.getString("reason");
            }catch (Exception e){
                e.printStackTrace();
                Logger.Log_ln(e.getMessage(), Logger.Level.CRIT, Logger.Type.SYSTEM);
                return ResponseJSON.ERRORResponseToClientPaper("4", "paper and response structure error", null);
            }
            return ResponseJSON.ERRORResponseToClientPaper(errcode, errorreason, "6");
        }

    }
}

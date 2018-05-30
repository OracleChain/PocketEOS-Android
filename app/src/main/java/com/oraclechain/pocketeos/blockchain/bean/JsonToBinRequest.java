package com.oraclechain.pocketeos.blockchain.bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;

/**
 * Created by pocketEos on 2018/4/26.
 */

public class JsonToBinRequest {
    @Expose
    private String code;

    @Expose
    private String action;

    @Expose
    private JsonElement args;

    public JsonToBinRequest(String code, String action, String args) {
        this.code = code;
        this.action = action;
        this.args = new JsonParser().parse(args);
    }

    public String getArgsString() {
        return args.getAsString();
    }

    public void putArgs(String args) {
        this.args = new JsonParser().parse(args);
    }
}

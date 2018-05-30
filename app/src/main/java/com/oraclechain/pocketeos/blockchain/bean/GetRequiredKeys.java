package com.oraclechain.pocketeos.blockchain.bean;


import com.google.gson.annotations.Expose;
import com.oraclechain.pocketeos.blockchain.chain.SignedTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/4/26.
 */

public class GetRequiredKeys {
    @Override
    public String toString() {
        return "GetRequiredKeys{" +
                "transaction=" + transaction +
                ", available_keys=" + available_keys +
                '}';
    }
    @Expose
    private SignedTransaction transaction;
    @Expose
    private List<String> available_keys ;

    public GetRequiredKeys(SignedTransaction transaction, List<String> keys ) {
        this.transaction = transaction;

        if ( null != keys ) {
            available_keys = new ArrayList<>(keys);
        }
        else {
            available_keys = new ArrayList<>();
        }
    }

    public SignedTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(SignedTransaction transaction) {
        this.transaction = transaction;
    }

    public List<String> getAvailable_keys() {
        if (available_keys == null) {
            return new ArrayList<>();
        }
        return available_keys;
    }

    public void setAvailable_keys(List<String> available_keys) {
        this.available_keys = available_keys;
    }
}
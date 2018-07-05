package com.oraclechain.pocketeos.blockchain.api;

import com.google.gson.annotations.Expose;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by swapnibble on 2017-09-12.
 */

public class EosChainInfo {

    @Expose
    private String server_version;

    @Expose
    private Integer head_block_num;

    @Expose
    private Integer last_irreversible_block_num;

    @Expose
    private String head_block_id;

    @Expose
    private String head_block_time;

    @Expose
    private String head_block_producer;

    @Expose
    private String chain_id;

    @Expose
    private String last_irreversible_block_id;

    @Expose
    private long virtual_block_cpu_limit;

    @Expose
    private long virtual_block_net_limit;

    @Expose
    private long block_cpu_limit;

    @Expose
    private long block_net_limit;


    public Integer getHeadBlockNum() {
        return head_block_num;
    }

    public void setHeadBlockNum(Integer headBlockNum) {
        this.head_block_num = headBlockNum;
    }

    public Integer getLastIrreversibleBlockNum() {
        return last_irreversible_block_num;
    }

    public String getLast_irreversible_block_id() {
        return last_irreversible_block_id == null ? "" : last_irreversible_block_id;
    }

    public void setLast_irreversible_block_id(String last_irreversible_block_id) {
        this.last_irreversible_block_id = last_irreversible_block_id;
    }

    public void setLastIrreversibleBlockNum(Integer lastIrreversibleBlockNum) {
        this.last_irreversible_block_num = lastIrreversibleBlockNum;
    }

    public String getHeadBlockId() {
        return head_block_id;
    }

    public void setHeadBlockId(String headBlockId) {
        this.head_block_id = headBlockId;
    }

    public String getHeadBlockTime() {
        return head_block_time;
    }

    public void setHeadBlockTime(String headBlockTime) {
        this.head_block_time = headBlockTime;
    }

    public String getTimeAfterHeadBlockTime(int diffInMilSec) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse( this.head_block_time);

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add( Calendar.MILLISECOND, diffInMilSec);
            date = c.getTime();

            return sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return this.head_block_time;
        }
    }

    public String getHeadBlockProducer() {
        return head_block_producer;
    }

    public void setHeadBlockProducer(String headBlockProducer) {
        this.head_block_producer = headBlockProducer;
    }


    public String getBrief(){
        return    "server_version: "  + server_version
                + "\nhead block num: " + head_block_num
                + "\nlast irreversible block: " + last_irreversible_block_num
                + "\nhead block time: " + head_block_time
                + "\nhead block producer: " + head_block_producer ;
    }

    public String getChain_id() {
        return chain_id;
    }

    public void setChain_id(String chain_id) {
        this.chain_id = chain_id;
    }

    @Override
    public String toString() {
        return "EosChainInfo{" +
                "server_version='" + server_version + '\'' +
                ", head_block_num=" + head_block_num +
                ", last_irreversible_block_num=" + last_irreversible_block_num +
                ", head_block_id='" + head_block_id + '\'' +
                ", head_block_time='" + head_block_time + '\'' +
                ", head_block_producer='" + head_block_producer + '\'' +
                ", chain_id='" + chain_id + '\'' +
                ", virtual_block_cpu_limit=" + virtual_block_cpu_limit +
                ", virtual_block_net_limit=" + virtual_block_net_limit +
                ", block_cpu_limit=" + block_cpu_limit +
                ", block_net_limit=" + block_net_limit +
                '}';
    }
}


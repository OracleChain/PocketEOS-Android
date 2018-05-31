package com.oraclechain.pocketeos.blockchain.types;

import com.oraclechain.pocketeos.blockchain.cypto.digest.Sha256;

/**
 * Created by pocketEos on 2018/4/26.
 */
public class TypeChainId {
    private final Sha256 mId;

    public TypeChainId() {
        mId = Sha256.ZERO_HASH;
    }

    byte [] getSha256FromHexStr(String str){
        int len = str.length();
        byte [] bytes = new byte[32];
        for(int i=0;i<len;i+=2){
            String strIte = str.substring(i, i+2);
            Integer n =Integer.parseInt(strIte, 16) & 0xFF;;
            bytes[i/2] = n.byteValue();
        }
        return bytes;
    }
    public TypeChainId(String str){
        mId = new Sha256(getSha256FromHexStr(str));
    }

    public byte[] getBytes() {
        return mId.getBytes();
    }
}
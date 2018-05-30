package com.oraclechain.pocketeos.app;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by pocketEos on 2018/4/2.
 */
public class SafeHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        //验证主机名是否匹配
        //return hostname.equals("xxxxxxxxx");
        return true;
    }
}

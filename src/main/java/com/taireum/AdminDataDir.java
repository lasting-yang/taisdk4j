package com.taireum;

import org.web3j.protocol.core.Response;

public class AdminDataDir extends Response<String> {
    public String getDataDir() {
        return getResult();
    }

}

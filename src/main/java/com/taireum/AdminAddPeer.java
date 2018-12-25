package com.taireum;

import org.web3j.protocol.core.Response;

public class AdminAddPeer extends Response<Boolean>  {
    public Boolean addPeer() {
        return getResult();
    }
}

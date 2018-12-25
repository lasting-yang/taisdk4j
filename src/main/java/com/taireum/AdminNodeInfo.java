package com.taireum;

import org.web3j.protocol.core.Response;

public class AdminNodeInfo extends Response<NodeInfo> {
    public NodeInfo getNodeInfo() {
        return getResult();
    }
}

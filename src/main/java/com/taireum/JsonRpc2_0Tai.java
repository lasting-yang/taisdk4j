package com.taireum;

import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.JsonRpc2_0Admin;
import org.web3j.protocol.core.Request;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

    public class JsonRpc2_0Tai extends JsonRpc2_0Admin {
    public JsonRpc2_0Tai(Web3jService web3jService) {
        super(web3jService);
    }

    public JsonRpc2_0Tai(Web3jService web3jService, long pollingInterval, ScheduledExecutorService scheduledExecutorService) {
        super(web3jService, pollingInterval, scheduledExecutorService);
    }
    public Request<?, AdminDataDir> getDataDir() {
        return new Request<>(
                "admin_datadir",
                Collections.<String>emptyList(),
                web3jService,
                AdminDataDir.class);
    }
    public Request<?, AdminNodeInfo> getNodeInfo() {
        return new Request<>(
                "admin_nodeInfo",
                Collections.<String>emptyList(),
                web3jService,
                AdminNodeInfo.class);
    }

    public Request<?, AdminAddPeer> addPeer(String enode) {
        return new Request<>(
                "admin_addPeer",
                Arrays.asList(enode),
                web3jService,
                AdminAddPeer.class);
    }
}

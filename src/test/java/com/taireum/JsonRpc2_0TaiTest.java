package com.taireum;

import org.junit.Test;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonRpc2_0TaiTest {

    @Test
    public void getNodeInfo() {
        try {
            JsonRpc2_0Tai tai = new JsonRpc2_0Tai(new HttpService("http://localhost:8555"));
            String user_account_key = "/home/dev/go/src/github.com/lasting-yang/go-taireum/tai/ccc/tai_data_dir/keystore/UTC--2018-12-14T08-22-19.744708418Z--ce439e8f2b733bcabf19f8cd2cd296f9a9b421e9";
            Credentials credentials = WalletUtils.loadCredentials("123", user_account_key);
            AdminNodeInfo adminNodeInfo = tai.getNodeInfo().send();
            System.out.println(adminNodeInfo.getNodeInfo());
            AdminDataDir adminDataDir = tai.getDataDir().send();
            System.out.println(adminDataDir.getDataDir());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
}
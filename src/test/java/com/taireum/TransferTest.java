package com.taireum;

import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;

public class TransferTest {

    @Test
    public void sendFunds() {
        try{
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8555"));
            String user_account_key = "/home/dev/keystore/UTC--2018-11-21T06-27-35.438273011Z--43a6de588d507c0a2ea7e0f6dc4b4c5cb91159c9";
            Credentials credentials = WalletUtils.loadCredentials("123", user_account_key);

            String src_data = "~!@#$%^&*()_+`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
            String data = Numeric.toHexString(src_data.getBytes());
            System.out.println(src_data + " -> " + data);
            TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, "0x9d7286bab9844ca10d45e53c4bae1afd096a1bc5", data, BigDecimal.ONE, Convert.Unit.WEI).send();

            EthTransaction ethTransactionRequest = web3j.ethGetTransactionByHash(transactionReceipt.getTransactionHash()).send();
            String input = ethTransactionRequest.getResult().getInput();
            String de_data = new String(Numeric.hexStringToByteArray(input));
            System.out.println(de_data);
            assert src_data.equals(de_data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void sendFundsAsync() {
        try {
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8555"));
            String user_account_key = "C:\\Users\\yang\\Desktop\\taireum\\tai_data_dir\\keystore\\UTC--2018-11-27T01-50-12.189800000Z--a9feb0bb1f96b105ae0fbb1e5a709f4365757ec4";
            Credentials credentials = WalletUtils.loadCredentials("123", user_account_key);

            String src_data = "~!@#$%^&*()_+`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
            String data = Numeric.toHexString(src_data.getBytes());
            System.out.println(src_data + " -> " + data);
            System.out.println("[" + System.currentTimeMillis() + "] sendFundsAsync:");

            EthSendTransaction ethSendTransaction = Transfer.
                    sendFundsAsync(web3j, credentials, "0xebabf2a2f294b75e95794170e8613cc384164d7c", data, BigDecimal.ONE, Convert.Unit.WEI);

            EthTransaction ethTransactionRequest = web3j.ethGetTransactionByHash(ethSendTransaction.getTransactionHash()).send();
            System.out.println("[" + System.currentTimeMillis() + "] get hash:" + ethSendTransaction.getTransactionHash());
            String input = ethTransactionRequest.getResult().getInput();
            String de_data = new String(Numeric.hexStringToByteArray(input));
            System.out.println(de_data);
            assert src_data.equals(de_data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
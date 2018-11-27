package com.taireum;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class Transfer extends ManagedTransaction {

    // This is the cost to send Ether between parties
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(2100000);

    protected Transfer(Web3j web3j, TransactionManager transactionManager) {
        super(web3j, transactionManager);
    }

    /**
     * Given the duration required to execute a transaction, asyncronous execution is strongly
     * recommended via {@link Transfer#sendFunds(String, String, BigDecimal, Convert.Unit)}.
     *
     * @param toAddress destination address
     * @param value amount to send
     * @param unit of specified send
     *
     * @return {@link Optional} containing our transaction receipt
     * @throws ExecutionException if the computation threw an
     *                            exception
     * @throws InterruptedException if the current thread was interrupted
     *                              while waiting
     * @throws TransactionException if the transaction was not mined while waiting
     */
    private TransactionReceipt send(String toAddress, String data, BigDecimal value, Convert.Unit unit)
            throws IOException, InterruptedException,
            TransactionException {

        BigInteger gasPrice = requestCurrentGasPrice();
        return send(toAddress, data, value, unit, gasPrice, GAS_LIMIT);
    }

    private TransactionReceipt send(
            String toAddress, String data, BigDecimal value, Convert.Unit unit, BigInteger gasPrice,
            BigInteger gasLimit) throws IOException, InterruptedException,
            TransactionException {

        BigDecimal weiValue = Convert.toWei(value, unit);
        if (!Numeric.isIntegerValue(weiValue)) {
            throw new UnsupportedOperationException(
                    "Non decimal Wei value provided: " + value + " " + unit.toString()
                            + " = " + weiValue + " Wei");
        }

        String resolvedAddress = ensResolver.resolve(toAddress);
        return send(resolvedAddress, data, weiValue.toBigIntegerExact(), gasPrice, gasLimit);
    }

    private EthSendTransaction sendAsync(
            String toAddress, String data, BigDecimal value, Convert.Unit unit, BigInteger gasPrice,
            BigInteger gasLimit) throws IOException{

        BigDecimal weiValue = Convert.toWei(value, unit);
        if (!Numeric.isIntegerValue(weiValue)) {
            throw new UnsupportedOperationException(
                    "Non decimal Wei value provided: " + value + " " + unit.toString()
                            + " = " + weiValue + " Wei");
        }

        String resolvedAddress = ensResolver.resolve(toAddress);
        return transactionManager.sendTransaction(gasPrice, gasLimit, resolvedAddress, data,  weiValue.toBigIntegerExact());

    }
    public static RemoteCall<TransactionReceipt> sendFunds(
            Web3j web3j, Credentials credentials,
            String toAddress,  String data, BigDecimal value, Convert.Unit unit) {

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);

        return new RemoteCall<>(() ->
                new Transfer(web3j, transactionManager).send(toAddress, data, value, unit, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT));
    }

    public static EthSendTransaction sendFundsAsync(Web3j web3j, Credentials credentials,
                                          String toAddress,  String data, BigDecimal value, Convert.Unit unit) throws IOException {

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        return new Transfer(web3j, transactionManager).sendAsync(toAddress, data, value, unit, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
    }

    /**
     * Execute the provided function as a transaction asynchronously. This is intended for one-off
     * fund transfers. For multiple, create an instance.
     *
     * @param toAddress destination address
     * @param value amount to send
     * @param unit of specified send
     *
     * @return {@link RemoteCall} containing executing transaction
     */
    public RemoteCall<TransactionReceipt> sendFunds(
            String toAddress, String data, BigDecimal value, Convert.Unit unit) {
        return new RemoteCall<>(() -> send(toAddress, data, value, unit));
    }

    public RemoteCall<TransactionReceipt> sendFunds(
            String toAddress,  String data, BigDecimal value, Convert.Unit unit, BigInteger gasPrice,
            BigInteger gasLimit) {
        return new RemoteCall<>(() -> send(toAddress, data, value, unit, gasPrice, gasLimit));
    }
}

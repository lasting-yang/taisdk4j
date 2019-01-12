package com.taireum.CCC;

import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class CCCGasProvider extends StaticGasProvider {

    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(80_000_000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);

    public CCCGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
    public CCCGasProvider(BigInteger gasLimit, BigInteger GasPrice) {
        super(gasLimit, GasPrice);
    }

    //动态gas, 自己根据合约的函数返回gas price
    @Override
    public BigInteger getGasPrice(String contractFunc) {
        switch (contractFunc) {
            case CCC_sol_CCC.FUNC_VOTEMINE:
            case CCC_sol_CCC.FUNC_VOTEMEMBER: {
                return BigInteger.valueOf(44_000_000_000L);
            }
        }
        return super.getGasPrice(contractFunc);
    }

    //动态gas, 自己根据合约的函数返回gas limit
    @Override
    public BigInteger getGasLimit(String contractFunc) {
        return super.getGasLimit(contractFunc);
    }
}

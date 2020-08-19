package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        MathContext mathContext = new MathContext(range, RoundingMode.HALF_UP);

        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), mathContext);
    }

    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger currentNum = BigInteger.valueOf(2);
        int primaryCount = 0;

        //System.out.println(BigInteger.valueOf(541));

        while (primaryCount < range) {
            currentNum = currentNum.add(BigInteger.valueOf(1));
            if (currentNum.isProbablePrime(10)) {
                primaryCount++;
            }
        }

        return currentNum;
    }
}

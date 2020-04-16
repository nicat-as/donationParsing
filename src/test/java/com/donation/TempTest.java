package com.donation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TempTest {
    @Test
    void test(){
        Assertions.assertEquals("300000000","300,000,000eur".replaceAll("[,\\sa-zA-Z]",""));
    }

    @Test
    void testCharacterPattern(){
        Assertions.assertEquals("300","300EUR".replaceAll("[a-zA-Z]",""));
    }

    @Test
    void testCurrency(){
//        Assertions.assertEquals("eur","300eur".replaceAll("[0-9]",""));
        String amount = "300s";
        amount = amount.replaceAll("[0-9]","");
        if (!amount.isEmpty()){
            System.out.println(amount);
        }else {
            System.out.println("AZN");
        }
    }
}

package br.com.lwbaleeiro.eng_software.top_150;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.BuyAndSellStocks.maxProfit;
import static br.com.lwbaleeiro.eng_software.top_150.BuyAndSellStocks.maxProfit2;

public class BuyAndSellStocksTest {

    @Test
    void test() {
        int[] prices = {7,1,5,3,6,4};

        assert(5 == maxProfit(prices));
    }

    @Test
    void test2() {
        int[] prices = {7,1,5,3,6,4};

        assert(5 == maxProfit2(prices));
    }
}

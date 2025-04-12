package br.com.lwbaleeiro.eng_software.top_150;

public class BuyAndSellStocks {

    // My shit solution. Its work, but is dog shit.
    public static int maxProfit(int[] prices) {
        int left = 0;
        int right = prices.length - 1;
        int proft = 0;

        while (left < (prices.length - 1)) {

            if (left == right) {
                left++;
                right = prices.length - 1;
            }

            int sum = prices[right] - prices[left];

            if (sum > proft) {
                proft = sum;
            }

            right--;
        }

        return proft;
    }

    //Best practice
    public static int maxProfit2(int[] prices) {
        int buyPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (buyPrice > currentPrice) buyPrice = currentPrice;
            profit = Math.max(profit, currentPrice - buyPrice);
        }

        return profit;
    }
}

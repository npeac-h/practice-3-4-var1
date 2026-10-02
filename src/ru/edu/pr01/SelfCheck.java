package ru.edu.pr01;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        try {
            boolean baseOk = OrderCalculator.calculateBase(2, 100.0) == 200.0;
            boolean discountOk = Math.abs(OrderCalculator.applyDiscount(200.0, 10.0) - 180.0) < 1e-6;
            boolean vatOk = Math.abs(OrderCalculator.calculateVat(180.0, 10.0) - 18.0) < 1e-6;
            boolean validOk = OrderCalculator.isValid(10, 500.0, 5.0) && !OrderCalculator.isValid(0, 500.0, 5.0);

            ok = baseOk && discountOk && vatOk && validOk;
            System.out.println(ok ? "PASS" : "FAIL complete TODO methods");
        } catch (Exception e) {
            System.out.println("FAIL with exception: " + e.getMessage());
        }
    }
}

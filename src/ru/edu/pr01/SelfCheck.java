package ru.edu.pr01;

public class SelfCheck {

    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК ГРАНИЧНЫХ И ТИПОВЫХ ПРОВЕРОК (Задание 9) ===");

        boolean allPassed = true;

        allPassed &= testCase("Типовое значение", 12, 2490.50, 5.0, true);

        allPassed &= testCase("Нижняя граница (включение)", 1, 0.01, 0.0, true);

        allPassed &= testCase("Нижняя граница (quantity = 0)", 0, 1000.0, 10.0, false);

        allPassed &= testCase("Верхняя граница (включение)", 10000, 5000000.0, 30.0, true);

        allPassed &= testCase("Верхняя граница (discount = 35%)", 3, 999.99, 35.0, false);

        allPassed &= testCase("Отрицательная цена", 5, -20.0, 5.0, false);

        boolean baseOk = OrderCalculator.calculateBase(2, 100.0) == 200.0;
        boolean discountOk = Math.abs(OrderCalculator.applyDiscount(200.0, 10.0) - 180.0) < 1e-6;
        boolean vatOk = Math.abs(OrderCalculator.calculateVat(180.0, 10.0) - 18.0) < 1e-6;

        boolean formulasOk = baseOk && discountOk && vatOk;

        System.out.println("=================================================");
        if (allPassed && formulasOk) {
            System.out.println("ИТОГ: PASS (Все проверки успешно пройдены)");
        } else {
            System.out.println("ИТОГ: FAIL (Некоторые проверки не пройдены)");
        }
    }

    private static boolean testCase(String testName, int quantity, double unitPrice, double discount, boolean expectedValid) {
        boolean actualValid = OrderCalculator.isValid(quantity, unitPrice, discount);
        boolean passed = (actualValid == expectedValid);

        System.out.printf("[%s] %s: quantity=%d, price=%.2f, discount=%.1f%% -> Ожидалось: %s, Получено: %s%n",
                passed ? "OK" : "FAIL",
                testName,
                quantity, unitPrice, discount,
                expectedValid ? "VALID" : "INVALID",
                actualValid ? "VALID" : "INVALID");

        return passed;
    }
}

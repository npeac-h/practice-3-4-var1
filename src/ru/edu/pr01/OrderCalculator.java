package ru.edu.pr01;

import java.util.Locale;

public class OrderCalculator {

    public static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 10000;
    
    public static final double MIN_PRICE = 0.01;
    public static final double MAX_PRICE = 5000000.0;
    
    public static final double MIN_DISCOUNT = 0.0;
    public static final double MAX_DISCOUNT = 30.0;

    public static final double VAT_RATE = 10.0; 

    public static boolean isValid(int quantity, double unitPrice, double discountPercent) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            return false;
        }
        if (unitPrice < MIN_PRICE || unitPrice > MAX_PRICE) {
            return false;
        }
        if (discountPercent < MIN_DISCOUNT || discountPercent > MAX_DISCOUNT) {
            return false;
        }
        return true;
    }

    public static double calculateBase(int quantity, double unitPrice) {
        return quantity * unitPrice;
    }

    public static double applyDiscount(double base, double discountPercent) {
        return base * (1.0 - discountPercent / 100.0);
    }

    public static double calculateVat(double discounted, double vatPercent) {
        return discounted * (vatPercent / 100.0);
    }

    public static double calculateTotal(int quantity, double unitPrice, double discountPercent) {
        if (!isValid(quantity, unitPrice, discountPercent)) {
            throw new IllegalArgumentException("Некорректные входные данные");
        }
        double base = calculateBase(quantity, unitPrice);
        double discounted = applyDiscount(base, discountPercent);
        double vat = calculateVat(discounted, VAT_RATE);
        return discounted + vat;
    }

    public static void printOrderSummary(int quantity, double unitPrice, double discountPercent) {
        if (!isValid(quantity, unitPrice, discountPercent)) {
            System.err.println("Ошибка: Введённые данные выходят за допустимые диапазоны!");
            return;
        }

        double base = calculateBase(quantity, unitPrice);
        double discounted = applyDiscount(base, discountPercent);
        double vat = calculateVat(discounted, VAT_RATE);
        double total = discounted + vat;

        System.out.printf(Locale.US, "Базовая стоимость: %.2f руб.%n", base);
        System.out.printf(Locale.US, "Стоимость со скидкой (%.1f%%): %.2f руб.%n", discountPercent, discounted);
        System.out.printf(Locale.US, "НДС (%.1f%%): %.2f руб.%n", VAT_RATE, vat);
        System.out.printf(Locale.US, "Итого к оплате: %.2f руб.%n", total);
    }

    public static void main(String[] args) {
        int quantity = 12;
        double unitPrice = 2490.50;
        double discountPercent = 5.0;

        printOrderSummary(quantity, unitPrice, discountPercent);
    }
}

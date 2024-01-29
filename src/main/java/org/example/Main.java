package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Добрый день, дорогой пользователь!");
        System.out.println("Выберете, что вы хотите сделать: \n" +
                "1. Проверка на совершеннолетие(можно pivo или нет)\n" +
                "2. Посчитать P/S/R");

        String vibor = sc.next();

        switch (vibor) {
            case "1": {
                System.out.println("Введите FIO");
                sc.nextLine(); // Consume the newline left by previous next() call
                String fullName = sc.nextLine();

                String phoneNumber;
                do {
                    System.out.println("Введите свой номер телефона");
                    phoneNumber = sc.nextLine();
                } while (!isValidPhoneNumber(phoneNumber));

                Date birthDate;
                do {
                    System.out.println("Введите дату рождения в формате гггг-мм-дд");
                    String dateStr = sc.next();
                    birthDate = parseDate(dateStr);
                } while (birthDate == null);

                if (isAdult(birthDate)) {
                    // Вывод введенной информации
                    System.out.println("\nВведенная информация:");
                    System.out.println("FIO: " + fullName);
                    System.out.println("Номер телефона: " + phoneNumber);
                    System.out.println("Дата рождения: " + formatDate(birthDate));
                    System.out.println("Пользователь совершеннолетний.");
                } else {
                    System.out.println("Sorry, piva segonya ne budet.");
                }
                break;
            }
            case "2": {
                System.out.println("Введите диаметр для нахождения P/S/R");
                float D = sc.nextFloat();

                float R = D / 2;
                float P = 2 * (float) Math.PI * R;
                float S = (float) (Math.PI * R * R);

                // Вывод результатов
                System.out.println("\nРезультаты:");
                System.out.println("Радиус: " + R);
                System.out.println("Периметр: " + P);
                System.out.println("Площадь: " + S);
                break;
            }
            default:
                System.out.println("Ты чет не то жмал");
        }
    }

    private static String formatDate(Date birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(birthDate);
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberStr = String.valueOf(phoneNumber);
        return phoneNumberStr.length() == 11;
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Ошибка в формате даты. Повторите ввод.");
            return null;
        }
    }

    private static boolean isAdult(Date birthDate) {
        Date currentDate = new Date();
        long timeDiff = currentDate.getTime() - birthDate.getTime();
        int years = (int) (timeDiff / (1000L * 60 * 60 * 24 * 365.25));
        return years >= 18;
    }
}
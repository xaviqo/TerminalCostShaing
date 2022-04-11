package utils;

import java.util.Scanner;

public class UtilConsole {

    private static Scanner kbd = new Scanner(System.in);

    public static int inputInt() {

        int i = 0;
        boolean isInt = false;

        do {

            try {
                i = kbd.nextInt();
                isInt = true;
                kbd.nextLine();

            } catch (Exception e) {

                System.err.println("Introduce un valor numérico");
                System.out.print("Valor: ");
                kbd.nextLine();
            }

        } while (!isInt);

        return i;

    }

    public static float inputFloat() {

        float f = 0;
        boolean isFloat = false;

        do {

            try {

                f = kbd.nextFloat();
                isFloat = true;
                kbd.nextLine();

            } catch (Exception e) {

                System.err.println("Introduce un valor numérico");
                System.out.print("Valor: ");
                kbd.nextLine();
            }

        } while (!isFloat);

        return f;

    }

    public static double inputDouble() {

        double d = 0;
        boolean isDouble = false;

        do {

            try {

                d = kbd.nextFloat();
                isDouble = true;
                kbd.nextLine();

            } catch (Exception e) {

                System.err.println("Introduce un valor numérico");
                System.out.print("Valor: ");
                kbd.nextLine();
            }

        } while (!isDouble);

        return d;

    }

    public static String inputString() {

        String string = "";
        boolean isString = false;

        do {
            string = kbd.nextLine();

            if (!string.isBlank()) {
                isString = true;
            } else {
                System.err.println("No válido");
                System.out.print("Valor: ");
                kbd.nextLine();
            }
            return string;

        } while (!isString);

    }

    public static boolean yesOrNo(String showMsg) {

        String userOption = "";
        String yes = "Y";
        String no = "N";
        String yesOrNo = " (" + yes + "/" + no + "): ";
        String error = "Invalid option";

        System.out.println(showMsg + yesOrNo);
        userOption = UtilConsole.inputString().trim().toUpperCase();

        if (userOption.equals(yes)) {
            return true;
        } else if (userOption.equals(no)) {
            return false;
        } else {
            System.err.println(error);
            yesOrNo(showMsg);
        }

        return false;

    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Linux")) {
                System.out.print("\033[H\033[2J");
            }
        } catch (Exception e) {
//            System.out.println(e);
        }
    }
}

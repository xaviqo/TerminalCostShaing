package utils;

public abstract class Generator {

    public static int numbRandom(int minimo, int maximo) {

        return (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));

    }

    public static String stringRandom(int digit){

        String returnString = "";

        for (int j = 0; j < digit; j++) {
             returnString += String.valueOf(charRandom());
        }

        return returnString;

    }

    public static char charRandom() {

        final int FIRST_CHAR_ASCII_NUM = 97;
        final int NUM_OF_CHARS = 25;

        return (char) (FIRST_CHAR_ASCII_NUM + Double.valueOf(Math.random() * NUM_OF_CHARS).intValue());

    }
}

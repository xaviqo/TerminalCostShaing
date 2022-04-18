package view;

import models.Persona;

import java.util.Map;

import static view.EventMenuView.infoIntoTable;
import static view.MainMenuView.theEvent;

public class BalanceView {

    public static void calculate() {

        String balanceMessage = "";
        float balanceCalc = 0f;

        for (Map.Entry<Persona, Float> entry : theEvent.getPersonas().entrySet()) {

            //CALC = TOTAL / PARTICIPANTES - APORTACIÓN
            //NEG = TE DEBEN || POS = DEUDA PENDIENTE
            balanceCalc = theEvent.getTotal() / theEvent.getPersonas().size() - entry.getValue();

            if (balanceCalc > 0f) {
                balanceMessage = "Debe: " + String.format("%.02f", balanceCalc) + "€";
            } else if (balanceCalc < 0f) {
                balanceMessage = "Le deben: " + String.format("%.02f", balanceCalc * -1) + "€";
            } else {
                balanceMessage = "Está equilibrado: " + String.format("%.02f", balanceCalc);
            }

            infoIntoTable(entry.getKey().getName(), balanceMessage);

        }


    }

}


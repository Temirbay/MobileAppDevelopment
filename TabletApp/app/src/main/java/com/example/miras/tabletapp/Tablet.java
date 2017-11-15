package com.example.miras.tabletapp;

import java.io.Serializable;

/**
 * Created by Miras on 14.11.2017.
 */

public class Tablet implements Serializable {
    private static final String[] acronyms = {"OD(once a day)", "BID(twice a day)",
            "TDS(three times a day)", "QDS(four times a day)"};

    public String name;
    public double dose;
    public String type;
    public int duration;
    public int frequency;
    public String description;
    public int quantity;

    public Tablet() {}

    public Tablet(String name, double dose, String type, int duration,
                  int frequency, String description, int quantity) {
        this.name = name;
        this.dose = dose;
        this.type = type;
        this.duration = duration;
        this.frequency = frequency;
        this.description = description;
        this.quantity = quantity;
    }

    public Tablet(String name, String dose, String type, String duration,
                  String frequency, String description, String quantity) {
        this.name = name;
        this.dose = getDoubleFromString(dose);
        this.type = type;
        this.duration = getIntegerFromString(duration);
        this.frequency = getIntegerFromAcronym(frequency);
        this.description = description;
        this.quantity = getIntegerFromString(quantity);
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDose(double dose) {
        this.dose = dose;
    }
    public void setType (String type) {
        this.type = type;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setQuantity(String quantity) {
        this.quantity = getIntegerFromString(quantity);
    }
    public void setFrequency(String frequency) {
        this.frequency = getIntegerFromAcronym(frequency);
    }
    public void setDose(String dose) {
        this.dose = getDoubleFromString(dose);
    }
    public void setDuration(String duration) {
        this.duration = getIntegerFromString(duration);
    }

    private int getIntegerFromString(String str) {
        if (str.equals("-")) return 0;
        int num = 0;
        for (int i = 0; i < str.length(); ++i) {
            int ascii = (int)str.charAt(i);
            if (48 <= ascii && ascii <= 57) {
                num *= 10;
                num += (ascii - 48);
            }
            else break;
        }

        return num;
    }

    private double getDoubleFromString(String str) {
        if (str.equals("-")) return 0;
        double num1 = 0, num2 = 0;
        boolean ok = false;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '.')  {ok = true; continue;}
            int ascii = (int)str.charAt(i);
            if (48 <= ascii && ascii <= 57) {
                if (!ok){num1 *= 10; num1 += (ascii - 48);}
                else {num2 /= 10; num2 += (ascii - 48);}
            }
            else break;
        }

        return num1 + num2;
    }

    private int getIntegerFromAcronym(String frequency) {
        for (int i = 0; i < acronyms.length; ++i)
            if (frequency.equals(acronyms[i]))
                return i+1;

        return 0;
    }
}

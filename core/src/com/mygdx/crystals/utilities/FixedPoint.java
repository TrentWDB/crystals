package com.mygdx.crystals.utilities;

/**
 * Created by Trent on 7/26/2014.
 */
public class FixedPoint extends Number implements Comparable<FixedPoint> {
    //first 10 digits are whole value, last 9 are fraction value
    private long number;

    private static final int DIVISOR = 1000000000;

    public FixedPoint() {
        number = 0;
    }
/*was in the process of changing this to be divide instead of bit shift... but decided to just try double
    public FixedPoint(int number) {
       setWholeValue(number);
    }

    public FixedPoint(String number) {
        String[] numberParts = number.split("\\.");
        if (numberParts.length > 1)
            setWholeFractionValue(Integer.parseInt(numberParts[0]), Integer.parseInt(numberParts[1]));
        else
            setWholeValue(Integer.parseInt(numberParts[0]));
    }

    public FixedPoint(int whole, int fraction) {
        setWholeFractionValue(whole, fraction);
    }

    public FixedPoint(double number) {
        int whole = (int) number;
    }

    public FixedPoint(float number) {
        int whole = (int) number;
        int fraction = (int) number;
    }

    private void setWholeValue(int number) {
        if (number < 0) {
            number = -number;
            //making the sign bit 1
            this.number = (this.number | SIGN_VALUE);
        } else {
            //making the sign bit 0
            this.number = (this.number | MANTISSA_VALUE);
        }

        this.number = ((number & INT_NUMERICAL_VALUE) << 31) | (this.number & FRACTION_VALUE);
    }

    private void setFractionValue(int number) {
        this.number = (this.number & WHOLE_VALUE);
    }

    private void setWholeFractionValue(int whole, int fraction) {
        setWholeValue(whole);
        setFractionValue(fraction);
    }
*/
    @Override
    public int compareTo(FixedPoint o) {
        return 0;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
/*
    public String toString() {
        boolean negative =
        int whole = number >>> 16;
        int fraction = number & 0x0000ffff;

        return "" + whole + "." + fraction;
    }*/
}

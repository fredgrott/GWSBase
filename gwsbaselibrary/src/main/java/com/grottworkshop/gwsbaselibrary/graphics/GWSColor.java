package com.grottworkshop.gwsbaselibrary.graphics;

import android.graphics.Color;

/**
 * GWSColor, modified from Matt York's Colours library to include material design colors.
 * Created by fgrott on 10/7/2014.
 */
@SuppressWarnings("unchecked")
public class GWSColor extends Color {
    /**
     * The enum Color scheme.
     */
//Color Scheme Enumeration (for color scheme generation)
    public enum ColorScheme {
        /**
         * The ColorSchemeAnalagous.
         */
        ColorSchemeAnalagous, /**
         * The ColorSchemeMonochromatic.
         */
        ColorSchemeMonochromatic, /**
         * The ColorSchemeTriad.
         */
        ColorSchemeTriad, /**
         * The ColorSchemeComplementary.
         */
        ColorSchemeComplementary
    }

    /**
     * The enum Color distance formula.
     */
    public enum ColorDistanceFormula {
        /**
         * The ColorDistanceFormulaCIE76.
         */
        ColorDistanceFormulaCIE76, /**
         * The ColorDistanceFormulaCIE94.
         */
        ColorDistanceFormulaCIE94, /**
         * The ColorDistanceFormulaCIE2000.
         */
        ColorDistanceFormulaCIE2000
    }

    // ///////////////////////////////////
    // 4 Color Scheme from Color
    // ///////////////////////////////////

    /**
     * Creates an int[] of 4 Colors that complement the Color.
     *
     * @param color the color
     * @param type ColorSchemeAnalagous, ColorSchemeMonochromatic,
     *             ColorSchemeTriad, ColorSchemeComplementary
     * @return ArrayList<Integer> int [ ]
     */
    public static int[] colorSchemeOfType(int color, ColorScheme type) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        switch (type) {
            case ColorSchemeAnalagous:
                return GWSColor.analagousColors(hsv);
            case ColorSchemeMonochromatic:
                return GWSColor.monochromaticColors(hsv);
            case ColorSchemeTriad:
                return GWSColor.triadColors(hsv);
            case ColorSchemeComplementary:
                return GWSColor.complementaryColors(hsv);
            default:
                return null;
        }
    }

    /**
     * Analagous colors.
     *
     * @param hsv the hsv
     * @return the int [ ]
     */
    public static int[] analagousColors(float[] hsv) {
        float[] CA1 = {GWSColor.addDegrees(hsv[0], 15),
                (float) (hsv[1] - 0.05), (float) (hsv[2] - 0.05)};
        float[] CA2 = {GWSColor.addDegrees(hsv[0], 30),
                (float) (hsv[1] - 0.05), (float) (hsv[2] - 0.1)};
        float[] CB1 = {GWSColor.addDegrees(hsv[0], -15),
                (float) (hsv[1] - 0.05), (float) (hsv[2] - 0.05)};
        float[] CB2 = {GWSColor.addDegrees(hsv[0], -30),
                (float) (hsv[1] - 0.05), (float) (hsv[2] - 0.1)};

        return new int[]{Color.HSVToColor(CA1), Color.HSVToColor(CA2),
                Color.HSVToColor(CB1), Color.HSVToColor(CB2)};
    }

    /**
     * Monochromatic colors.
     *
     * @param hsv the hsv
     * @return the int [ ]
     */
    public static int[] monochromaticColors(float[] hsv) {
        float[] CA1 = {hsv[0], (float) (hsv[1]), (float) (hsv[2] / 2)};
        float[] CA2 = {hsv[0], (float) (hsv[1] / 2), (float) (hsv[2] / 3)};
        float[] CB1 = {hsv[0], (float) (hsv[1] / 3), (float) (hsv[2] * 2 / 3)};
        float[] CB2 = {hsv[0], (float) (hsv[1]), (float) (hsv[2] * 4 / 5)};

        return new int[]{Color.HSVToColor(CA1), Color.HSVToColor(CA2),
                Color.HSVToColor(CB1), Color.HSVToColor(CB2)};
    }

    /**
     * Triad colors.
     *
     * @param hsv the hsv
     * @return the int [ ]
     */
    public static int[] triadColors(float[] hsv) {

        float[] CA1 = {GWSColor.addDegrees(hsv[0], 120), (float) (hsv[1]),
                (float) (hsv[2])};
        float[] CA2 = {GWSColor.addDegrees(hsv[0], 120),
                (float) (hsv[1] * 7 / 6), (float) (hsv[2] - 0.05)};
        float[] CB1 = {GWSColor.addDegrees(hsv[0], 240), (float) (hsv[1]),
                (float) (hsv[2])};
        float[] CB2 = {GWSColor.addDegrees(hsv[0], 240),
                (float) (hsv[1] * 7 / 6), (float) (hsv[2] - 0.05)};

        return new int[]{Color.HSVToColor(CA1), Color.HSVToColor(CA2),
                Color.HSVToColor(CB1), Color.HSVToColor(CB2)};
    }

    /**
     * Complementary colors.
     *
     * @param hsv the hsv
     * @return the int [ ]
     */
    public static int[] complementaryColors(float[] hsv) {
        float[] CA1 = {hsv[0], (float) (hsv[1] * 5 / 7), (float) (hsv[2])};
        float[] CA2 = {hsv[0], (float) (hsv[1]), (float) (hsv[2] * 4 / 5)};
        float[] CB1 = {GWSColor.addDegrees(hsv[0], 180), (float) (hsv[1]),
                (float) (hsv[2])};
        float[] CB2 = {GWSColor.addDegrees(hsv[0], 180),
                (float) (hsv[1] * 5 / 7), (float) (hsv[2])};

        return new int[]{Color.HSVToColor(CA1), Color.HSVToColor(CA2),
                Color.HSVToColor(CB1), Color.HSVToColor(CB2)};
    }

    /**
     * Add degrees.
     *
     * @param addDeg the add deg
     * @param staticDeg the static deg
     * @return the float
     */
    public static float addDegrees(float addDeg, float staticDeg) {
        staticDeg += addDeg;
        if (staticDeg > 360) {
            float offset = staticDeg - 360;
            return offset;
        } else if (staticDeg < 0) {
            return -1 * staticDeg;
        } else {
            return staticDeg;
        }
    }

    /**
     * Returns black or white, depending on which color would contrast best with the provided color.
     *
     * @param color (Color)
     * @return int int
     */
    public static int blackOrWhiteContrastingColor(int color) {
        int[] rgbaArray = new int[]{GWSColor.red(color), GWSColor.green(color), GWSColor.blue(color)};
        double a = 1 - ((0.00299 * (double) rgbaArray[0]) + (0.00587 * (double) rgbaArray[1]) + (0.00114 * (double) rgbaArray[2]));
        return a < 0.5 ? GWSColor.BLACK : GWSColor.WHITE;
    }


    /**
     * This method will create a color instance that is the exact opposite color from another color on the color wheel. The same saturation and brightness are preserved, just the hue is changed.
     *
     * @param color (Color)
     * @return int int
     */
    public static int complementaryColor(int color) {
        float[] hsv = new float[3];
        GWSColor.colorToHSV(color, hsv);
        float newH = GWSColor.addDegrees(180, hsv[0]);
        hsv[0] = newH;

        return GWSColor.HSVToColor(hsv);
    }

    // CMYK

    /**
     * Color to cMYK.
     *
     * @param color the color int
     * @return float [ ]
     */
    public static float[] colorToCMYK(int color) {
        float r = GWSColor.red(color);
        float g = GWSColor.green(color);
        float b = GWSColor.blue(color);
        float c = 1 - r / 255;
        float m = 1 - g / 255;
        float y = 1 - b / 255;
        float k = Math.min(1, Math.min(c, Math.min(m, y)));
        if (k == 1) {
            c = 0;
            m = 0;
            y = 0;
        } else {
            c = (c - k) / (1 - k);
            m = (m - k) / (1 - k);
            y = (y - k) / (1 - k);
        }

        return new float[]{c, m, y, k};
    }


    /**
     * CMYK to color.
     *
     * @param cmyk the cmyk array
     * @return color int
     */
    public static int CMYKtoColor(float[] cmyk) {
        float c = cmyk[0] * (1 - cmyk[3]) + cmyk[3];
        float m = cmyk[1] * (1 - cmyk[3]) + cmyk[3];
        float y = cmyk[2] * (1 - cmyk[3]) + cmyk[3];
        return GWSColor.rgb((int) ((1 - c) * 255), (int) ((1 - m) * 255), (int) ((1 - y) * 255));
    }

    /**
     * Color to cIE _ lAB.
     *
     * @param color the color int
     * @return double[] double [ ]
     */
    public static double[] colorToCIE_LAB(int color) {
        // Convert Color to XYZ format first
        double r = GWSColor.red(color) / 255.0;
        double g = GWSColor.green(color) / 255.0;
        double b = GWSColor.blue(color) / 255.0;

        // Create deltaRGB
        r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.40) : (r / 12.92);
        g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.40) : (g / 12.92);
        b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.40) : (b / 12.92);

        // Create XYZ
        double X = r * 41.24 + g * 35.76 + b * 18.05;
        double Y = r * 21.26 + g * 71.52 + b * 7.22;
        double Z = r * 1.93 + g * 11.92 + b * 95.05;

        // Convert XYZ to L*a*b*
        X = X / 95.047;
        Y = Y / 100.000;
        Z = Z / 108.883;
        X = (X > Math.pow((6.0 / 29.0), 3.0)) ? Math.pow(X, 1.0 / 3.0) : (1 / 3) * Math.pow((29.0 / 6.0), 2.0) * X + 4 / 29.0;
        Y = (Y > Math.pow((6.0 / 29.0), 3.0)) ? Math.pow(Y, 1.0 / 3.0) : (1 / 3) * Math.pow((29.0 / 6.0), 2.0) * Y + 4 / 29.0;
        Z = (Z > Math.pow((6.0 / 29.0), 3.0)) ? Math.pow(Z, 1.0 / 3.0) : (1 / 3) * Math.pow((29.0 / 6.0), 2.0) * Z + 4 / 29.0;
        double CIE_L = 116 * Y - 16;
        double CIE_a = 500 * (X - Y);
        double CIE_b = 200 * (Y - Z);
        return new double[]{CIE_L, CIE_a, CIE_b};
    }

    /**
     * CIE _ lab to color.
     *
     * @param cie_lab the double[]
     * @return color int
     */
    public static int CIE_LabToColor(double[] cie_lab) {
        double L = cie_lab[0];
        double A = cie_lab[1];
        double B = cie_lab[2];
        double Y = (L + 16.0) / 116.0;
        double X = A / 500 + Y;
        double Z = Y - B / 200;
        X = (Math.pow(X, 3.0) > 0.008856) ? Math.pow(X, 3.0) : (X - 4 / 29.0) / 7.787;
        Y = (Math.pow(Y, 3.0) > 0.008856) ? Math.pow(Y, 3.0) : (Y - 4 / 29.0) / 7.787;
        Z = (Math.pow(Z, 3.0) > 0.008856) ? Math.pow(Z, 3.0) : (Z - 4 / 29.0) / 7.787;
        X = X * .95047;
        Y = Y * 1.00000;
        Z = Z * 1.08883;

        // Convert XYZ to RGB
        double R = X * 3.2406 + Y * -1.5372 + Z * -0.4986;
        double G = X * -0.9689 + Y * 1.8758 + Z * 0.0415;
        double _B = X * 0.0557 + Y * -0.2040 + Z * 1.0570;
        R = (R > 0.0031308) ? 1.055 * (Math.pow(R, (1 / 2.4))) - 0.055 : R * 12.92;
        G = (G > 0.0031308) ? 1.055 * (Math.pow(G, (1 / 2.4))) - 0.055 : G * 12.92;
        _B = (_B > 0.0031308) ? 1.055 * (Math.pow(_B, (1 / 2.4))) - 0.055 : _B * 12.92;
        return GWSColor.rgb((int) (R * 255), (int) (G * 255), (int) (_B * 255));
    }

    /**
     * Distance between colors.
     *
     * @param colorA the color a
     * @param colorB the color b
     * @return the double
     */
    public static double distanceBetweenColors(int colorA, int colorB) {
        return distanceBetweenColorsWithFormula(colorA, colorB, ColorDistanceFormula.ColorDistanceFormulaCIE94);
    }

    /**
     * Distance between colors with formula.
     *
     * @param colorA the color a
     * @param colorB the color b
     * @param formula the formula
     * @return the double
     */
    public static double distanceBetweenColorsWithFormula(int colorA, int colorB, ColorDistanceFormula formula) {
        double[] lab1 = GWSColor.colorToCIE_LAB(colorA);
        double[] lab2 = GWSColor.colorToCIE_LAB(colorB);
        double L1 = lab1[0];
        double A1 = lab1[1];
        double B1 = lab1[2];
        double L2 = lab2[0];
        double A2 = lab2[1];
        double B2 = lab2[2];

        // CIE76 first
        if (formula == ColorDistanceFormula.ColorDistanceFormulaCIE76) {
            double distance = Math.sqrt(Math.pow((L1 - L2), 2) + Math.pow((A1 - A2), 2) + Math.pow((B1 - B2), 2));
            return distance;
        }

        // More Common Variables
        double kL = 1;
        double kC = 1;
        double kH = 1;
        double k1 = 0.045;
        double k2 = 0.015;
        double deltaL = L1 - L2;
        double C1 = Math.sqrt((A1 * A1) + (B1 * B1));
        double C2 = Math.sqrt((A2 * A2) + (B2 * B2));
        double deltaC = C1 - C2;
        double deltaH = Math.sqrt(Math.pow((A1 - A2), 2.0) + Math.pow((B1 - B2), 2.0) - Math.pow(deltaC, 2.0));
        double sL = 1;
        double sC = 1 + k1 * (Math.sqrt((A1 * A1) + (B1 * B1)));
        double sH = 1 + k2 * (Math.sqrt((A1 * A1) + (B1 * B1)));

        // CIE94
        if (formula == ColorDistanceFormula.ColorDistanceFormulaCIE94) {
            return Math.sqrt(Math.pow((deltaL / (kL * sL)), 2.0) + Math.pow((deltaC / (kC * sC)), 2.0) + Math.pow((deltaH / (kH * sH)), 2.0));
        }

        // CIE2000
        // More variables
        double deltaLPrime = L2 - L1;
        double meanL = (L1 + L2) / 2;
        double meanC = (C1 + C2) / 2;
        double aPrime1 = A1 + A1 / 2 * (1 - Math.sqrt(Math.pow(meanC, 7.0) / (Math.pow(meanC, 7.0) + Math.pow(25.0, 7.0))));
        double aPrime2 = A2 + A2 / 2 * (1 - Math.sqrt(Math.pow(meanC, 7.0) / (Math.pow(meanC, 7.0) + Math.pow(25.0, 7.0))));
        double cPrime1 = Math.sqrt((aPrime1 * aPrime1) + (B1 * B1));
        double cPrime2 = Math.sqrt((aPrime2 * aPrime2) + (B2 * B2));
        double cMeanPrime = (cPrime1 + cPrime2) / 2;
        double deltaCPrime = cPrime1 - cPrime2;
        double hPrime1 = Math.atan2(B1, aPrime1);
        double hPrime2 = Math.atan2(B2, aPrime2);
        hPrime1 = hPrime1 % RAD(360.0);
        hPrime2 = hPrime2 % RAD(360.0);
        double deltahPrime = 0;
        if (Math.abs(hPrime1 - hPrime2) <= RAD(180.0)) {
            deltahPrime = hPrime2 - hPrime1;
        } else {
            deltahPrime = (hPrime2 <= hPrime1) ? hPrime2 - hPrime1 + RAD(360.0) : hPrime2 - hPrime1 - RAD(360.0);
        }
        double deltaHPrime = 2 * Math.sqrt(cPrime1 * cPrime2) * Math.sin(deltahPrime / 2);
        double meanHPrime = (Math.abs(hPrime1 - hPrime2) <= RAD(180.0)) ? (hPrime1 + hPrime2) / 2 : (hPrime1 + hPrime2 + RAD(360.0)) / 2;
        double T = 1 - 0.17 * Math.cos(meanHPrime - RAD(30.0)) + 0.24 * Math.cos(2 * meanHPrime) + 0.32 * Math.cos(3 * meanHPrime + RAD(6.0)) - 0.20 * Math.cos(4 * meanHPrime - RAD(63.0));
        sL = 1 + (0.015 * Math.pow((meanL - 50), 2)) / Math.sqrt(20 + Math.pow((meanL - 50), 2));
        sC = 1 + 0.045 * cMeanPrime;
        sH = 1 + 0.015 * cMeanPrime * T;
        double Rt = -2 * Math.sqrt(Math.pow(cMeanPrime, 7) / (Math.pow(cMeanPrime, 7) + Math.pow(25.0, 7))) * Math.sin(RAD(60.0) * Math.exp(-1 * Math.pow((meanHPrime - RAD(275.0)) / RAD(25.0), 2)));

        // Finally return CIE2000 distance
        return Math.sqrt(Math.pow((deltaLPrime / (kL * sL)), 2) + Math.pow((deltaCPrime / (kC * sC)), 2) + Math.pow((deltaHPrime / (kH * sH)), Rt * (deltaC / (kC * sC)) * (deltaHPrime / (kH * sH))));
    }

    private static double RAD(double degree) {
        return degree * Math.PI / 180;
    }

    /**
     * Info blue color.
     *
     * @return the int
     */
// Predefined Colors
    // System Colors
    public static int infoBlueColor() {
        return GWSColor.rgb(47, 112, 225);
    }

    /**
     * Success color.
     *
     * @return the int
     */
    public static int successColor() {
        return GWSColor.rgb(83, 215, 106);
    }

    /**
     * Warning color.
     *
     * @return the int
     */
    public static int warningColor() {
        return GWSColor.rgb(221, 170, 59);
    }

    /**
     * Danger color.
     *
     * @return the int
     */
    public static int dangerColor() {
        return GWSColor.rgb(229, 0, 15);
    }

    /**
     * Antique white color.
     *
     * @return the int
     */
// Whites
    public static int antiqueWhiteColor() {
        return GWSColor.rgb(250, 235, 215);
    }

    /**
     * Old lace color.
     *
     * @return the int
     */
    public static int oldLaceColor() {
        return GWSColor.rgb(253, 245, 230);
    }

    /**
     * Ivory color.
     *
     * @return the int
     */
    public static int ivoryColor() {
        return GWSColor.rgb(255, 255, 240);
    }

    /**
     * Seashell color.
     *
     * @return the int
     */
    public static int seashellColor() {
        return GWSColor.rgb(255, 245, 238);
    }

    /**
     * Ghost white color.
     *
     * @return the int
     */
    public static int ghostWhiteColor() {
        return GWSColor.rgb(248, 248, 255);
    }

    /**
     * Snow color.
     *
     * @return the int
     */
    public static int snowColor() {
        return GWSColor.rgb(255, 250, 250);
    }

    /**
     * Linen color.
     *
     * @return the int
     */
    public static int linenColor() {
        return GWSColor.rgb(250, 240, 230);
    }

    /**
     * Black 25 percent color.
     *
     * @return the int
     */
// Grays
    public static int black25PercentColor() {
        return GWSColor.rgb(64, 64, 64);
    }

    /**
     * Black 50 percent color.
     *
     * @return the int
     */
    public static int black50PercentColor() {
        return GWSColor.rgb(128, 128, 128);
    }

    /**
     * Black 75 percent color.
     *
     * @return the int
     */
    public static int black75PercentColor() {
        return GWSColor.rgb(192, 192, 192);
    }

    /**
     * Warm gray color.
     *
     * @return the int
     */
    public static int warmGrayColor() {
        return GWSColor.rgb(133, 117, 112);
    }

    /**
     * Cool gray color.
     *
     * @return the int
     */
    public static int coolGrayColor() {
        return GWSColor.rgb(118, 122, 133);
    }

    /**
     * Charcoal color.
     *
     * @return the int
     */
    public static int charcoalColor() {
        return GWSColor.rgb(34, 34, 34);
    }

    /**
     * Teal color.
     *
     * @return the int
     */
// Blues
    public static int tealColor() {
        return GWSColor.rgb(28, 160, 170);
    }

    /**
     * Steel blue color.
     *
     * @return the int
     */
    public static int steelBlueColor() {
        return GWSColor.rgb(103, 153, 170);
    }

    /**
     * Robin egg color.
     *
     * @return the int
     */
    public static int robinEggColor() {
        return GWSColor.rgb(141, 218, 247);
    }

    /**
     * Pastel blue color.
     *
     * @return the int
     */
    public static int pastelBlueColor() {
        return GWSColor.rgb(99, 161, 247);
    }

    /**
     * Turquoise color.
     *
     * @return the int
     */
    public static int turquoiseColor() {
        return GWSColor.rgb(112, 219, 219);
    }

    /**
     * Sky blue color.
     *
     * @return the int
     */
    public static int skyBlueColor() {
        return GWSColor.rgb(0, 178, 238);
    }

    /**
     * Indigo color.
     *
     * @return the int
     */
    public static int indigoColor() {
        return GWSColor.rgb(13, 79, 139);
    }

    /**
     * Denim color.
     *
     * @return the int
     */
    public static int denimColor() {
        return GWSColor.rgb(67, 114, 170);
    }

    /**
     * Blueberry color.
     *
     * @return the int
     */
    public static int blueberryColor() {
        return GWSColor.rgb(89, 113, 173);
    }

    /**
     * Cornflower color.
     *
     * @return the int
     */
    public static int cornflowerColor() {
        return GWSColor.rgb(100, 149, 237);
    }

    /**
     * Baby blue color.
     *
     * @return the int
     */
    public static int babyBlueColor() {
        return GWSColor.rgb(190, 220, 230);
    }

    /**
     * Midnight blue color.
     *
     * @return the int
     */
    public static int midnightBlueColor() {
        return GWSColor.rgb(13, 26, 35);
    }

    /**
     * Faded blue color.
     *
     * @return the int
     */
    public static int fadedBlueColor() {
        return GWSColor.rgb(23, 137, 155);
    }

    /**
     * Iceberg color.
     *
     * @return the int
     */
    public static int icebergColor() {
        return GWSColor.rgb(200, 213, 219);
    }

    /**
     * Wave color.
     *
     * @return the int
     */
    public static int waveColor() {
        return GWSColor.rgb(102, 169, 251);
    }

    /**
     * Emerald color.
     *
     * @return the int
     */
// Greens
    public static int emeraldColor() {
        return GWSColor.rgb(1, 152, 117);
    }

    /**
     * Grass color.
     *
     * @return the int
     */
    public static int grassColor() {
        return GWSColor.rgb(99, 214, 74);
    }

    /**
     * Pastel green color.
     *
     * @return the int
     */
    public static int pastelGreenColor() {
        return GWSColor.rgb(126, 242, 124);
    }

    /**
     * Seafoam color.
     *
     * @return the int
     */
    public static int seafoamColor() {
        return GWSColor.rgb(77, 226, 140);
    }

    /**
     * Pale green color.
     *
     * @return the int
     */
    public static int paleGreenColor() {
        return GWSColor.rgb(176, 226, 172);
    }

    /**
     * Cactus green color.
     *
     * @return the int
     */
    public static int cactusGreenColor() {
        return GWSColor.rgb(99, 111, 87);
    }

    /**
     * Chartreuse color.
     *
     * @return the int
     */
    public static int chartreuseColor() {
        return GWSColor.rgb(69, 139, 0);
    }

    /**
     * Holly green color.
     *
     * @return the int
     */
    public static int hollyGreenColor() {
        return GWSColor.rgb(32, 87, 14);
    }

    /**
     * Olive color.
     *
     * @return the int
     */
    public static int oliveColor() {
        return GWSColor.rgb(91, 114, 34);
    }

    /**
     * Olive drab color.
     *
     * @return the int
     */
    public static int oliveDrabColor() {
        return GWSColor.rgb(107, 142, 35);
    }

    /**
     * Money green color.
     *
     * @return the int
     */
    public static int moneyGreenColor() {
        return GWSColor.rgb(134, 198, 124);
    }

    /**
     * Honeydew color.
     *
     * @return the int
     */
    public static int honeydewColor() {
        return GWSColor.rgb(216, 255, 231);
    }

    /**
     * Lime color.
     *
     * @return the int
     */
    public static int limeColor() {
        return GWSColor.rgb(56, 237, 56);
    }

    /**
     * Card table color.
     *
     * @return the int
     */
    public static int cardTableColor() {
        return GWSColor.rgb(87, 121, 107);
    }

    /**
     * Salmon color.
     *
     * @return the int
     */
// Reds
    public static int salmonColor() {
        return GWSColor.rgb(233, 87, 95);
    }

    /**
     * Brick red color.
     *
     * @return the int
     */
    public static int brickRedColor() {
        return GWSColor.rgb(151, 27, 16);
    }

    /**
     * Easter pink color.
     *
     * @return the int
     */
    public static int easterPinkColor() {
        return GWSColor.rgb(241, 167, 162);
    }

    /**
     * Grapefruit color.
     *
     * @return the int
     */
    public static int grapefruitColor() {
        return GWSColor.rgb(228, 31, 54);
    }

    /**
     * Pink color.
     *
     * @return the int
     */
    public static int pinkColor() {
        return GWSColor.rgb(255, 95, 154);
    }

    /**
     * Indian red color.
     *
     * @return the int
     */
    public static int indianRedColor() {
        return GWSColor.rgb(205, 92, 92);
    }

    /**
     * Strawberry color.
     *
     * @return the int
     */
    public static int strawberryColor() {
        return GWSColor.rgb(190, 38, 37);
    }

    /**
     * Coral color.
     *
     * @return the int
     */
    public static int coralColor() {
        return GWSColor.rgb(240, 128, 128);
    }

    /**
     * Maroon color.
     *
     * @return the int
     */
    public static int maroonColor() {
        return GWSColor.rgb(80, 4, 28);
    }

    /**
     * Watermelon color.
     *
     * @return the int
     */
    public static int watermelonColor() {
        return GWSColor.rgb(242, 71, 63);
    }

    /**
     * Tomato color.
     *
     * @return the int
     */
    public static int tomatoColor() {
        return GWSColor.rgb(255, 99, 71);
    }

    /**
     * Pink lipstick color.
     *
     * @return the int
     */
    public static int pinkLipstickColor() {
        return GWSColor.rgb(255, 105, 180);
    }

    /**
     * Pale rose color.
     *
     * @return the int
     */
    public static int paleRoseColor() {
        return GWSColor.rgb(255, 228, 225);
    }

    /**
     * Crimson color.
     *
     * @return the int
     */
    public static int crimsonColor() {
        return GWSColor.rgb(187, 18, 36);
    }

    /**
     * Eggplant color.
     *
     * @return the int
     */
// Purples
    public static int eggplantColor() {
        return GWSColor.rgb(105, 5, 98);
    }

    /**
     * Pastel purple color.
     *
     * @return the int
     */
    public static int pastelPurpleColor() {
        return GWSColor.rgb(207, 100, 235);
    }

    /**
     * Pale purple color.
     *
     * @return the int
     */
    public static int palePurpleColor() {
        return GWSColor.rgb(229, 180, 235);
    }

    /**
     * Cool purple color.
     *
     * @return the int
     */
    public static int coolPurpleColor() {
        return GWSColor.rgb(140, 93, 228);
    }

    /**
     * Violet color.
     *
     * @return the int
     */
    public static int violetColor() {
        return GWSColor.rgb(191, 95, 255);
    }

    /**
     * Plum color.
     *
     * @return the int
     */
    public static int plumColor() {
        return GWSColor.rgb(139, 102, 139);
    }

    /**
     * Lavender color.
     *
     * @return the int
     */
    public static int lavenderColor() {
        return GWSColor.rgb(204, 153, 204);
    }

    /**
     * Raspberry color.
     *
     * @return the int
     */
    public static int raspberryColor() {
        return GWSColor.rgb(135, 38, 87);
    }

    /**
     * Fuschia color.
     *
     * @return the int
     */
    public static int fuschiaColor() {
        return GWSColor.rgb(255, 20, 147);
    }

    /**
     * Grape color.
     *
     * @return the int
     */
    public static int grapeColor() {
        return GWSColor.rgb(54, 11, 88);
    }

    /**
     * Periwinkle color.
     *
     * @return the int
     */
    public static int periwinkleColor() {
        return GWSColor.rgb(135, 159, 237);
    }

    /**
     * Orchid color.
     *
     * @return the int
     */
    public static int orchidColor() {
        return GWSColor.rgb(218, 112, 214);
    }

    /**
     * Goldenrod color.
     *
     * @return the int
     */
// Yellows
    public static int goldenrodColor() {
        return GWSColor.rgb(215, 170, 51);
    }

    /**
     * Yellow green color.
     *
     * @return the int
     */
    public static int yellowGreenColor() {
        return GWSColor.rgb(192, 242, 39);
    }

    /**
     * Banana color.
     *
     * @return the int
     */
    public static int bananaColor() {
        return GWSColor.rgb(229, 227, 58);
    }

    /**
     * Mustard color.
     *
     * @return the int
     */
    public static int mustardColor() {
        return GWSColor.rgb(205, 171, 45);
    }

    /**
     * Buttermilk color.
     *
     * @return the int
     */
    public static int buttermilkColor() {
        return GWSColor.rgb(254, 241, 181);
    }

    /**
     * Gold color.
     *
     * @return the int
     */
    public static int goldColor() {
        return GWSColor.rgb(139, 117, 18);
    }

    /**
     * Cream color.
     *
     * @return the int
     */
    public static int creamColor() {
        return GWSColor.rgb(240, 226, 187);
    }

    /**
     * Light cream color.
     *
     * @return the int
     */
    public static int lightCreamColor() {
        return GWSColor.rgb(240, 238, 215);
    }

    /**
     * Wheat color.
     *
     * @return the int
     */
    public static int wheatColor() {
        return GWSColor.rgb(240, 238, 215);
    }

    /**
     * Beige color.
     *
     * @return the int
     */
    public static int beigeColor() {
        return GWSColor.rgb(245, 245, 220);
    }

    /**
     * Peach color.
     *
     * @return the int
     */
// Oranges
    public static int peachColor() {
        return GWSColor.rgb(242, 187, 97);
    }

    /**
     * Burnt orange color.
     *
     * @return the int
     */
    public static int burntOrangeColor() {
        return GWSColor.rgb(184, 102, 37);
    }

    /**
     * Pastel orange color.
     *
     * @return the int
     */
    public static int pastelOrangeColor() {
        return GWSColor.rgb(248, 197, 143);
    }

    /**
     * Cantaloupe color.
     *
     * @return the int
     */
    public static int cantaloupeColor() {
        return GWSColor.rgb(250, 154, 79);
    }

    /**
     * Carrot color.
     *
     * @return the int
     */
    public static int carrotColor() {
        return GWSColor.rgb(237, 145, 33);
    }

    /**
     * Mandarin color.
     *
     * @return the int
     */
    public static int mandarinColor() {
        return GWSColor.rgb(247, 145, 55);
    }

    /**
     * Chili powder color.
     *
     * @return the int
     */
// Browns
    public static int chiliPowderColor() {
        return GWSColor.rgb(199, 63, 23);
    }

    /**
     * Burnt sienna color.
     *
     * @return the int
     */
    public static int burntSiennaColor() {
        return GWSColor.rgb(138, 54, 15);
    }

    /**
     * Chocolate color.
     *
     * @return the int
     */
    public static int chocolateColor() {
        return GWSColor.rgb(94, 38, 5);
    }

    /**
     * Coffee color.
     *
     * @return the int
     */
    public static int coffeeColor() {
        return GWSColor.rgb(141, 60, 15);
    }

    /**
     * Cinnamon color.
     *
     * @return the int
     */
    public static int cinnamonColor() {
        return GWSColor.rgb(123, 63, 9);
    }

    /**
     * Almond color.
     *
     * @return the int
     */
    public static int almondColor() {
        return GWSColor.rgb(196, 142, 72);
    }

    /**
     * Eggshell color.
     *
     * @return the int
     */
    public static int eggshellColor() {
        return GWSColor.rgb(252, 230, 201);
    }

    /**
     * Sand color.
     *
     * @return the int
     */
    public static int sandColor() {
        return GWSColor.rgb(222, 182, 151);
    }

    /**
     * Mud color.
     *
     * @return the int
     */
    public static int mudColor() {
        return GWSColor.rgb(70, 45, 29);
    }

    /**
     * Sienna color.
     *
     * @return the int
     */
    public static int siennaColor() {
        return GWSColor.rgb(160, 82, 45);
    }

    /**
     * Dust color.
     *
     * @return the int
     */
    public static int dustColor() {
        return GWSColor.rgb(236, 214, 197);
    }

    // All Holo Colors in Android

    /**
     * Holo blue light color.
     *
     * @return the int
     */
    public static int holoBlueLightColor() {
        return GWSColor.parseColor("#ff33b5e5");
    }

    /**
     * Holo green light color.
     *
     * @return the int
     */
    public static int holoGreenLightColor() {
        return GWSColor.parseColor("#ff99cc00");
    }

    /**
     * Holo red light color.
     *
     * @return the int
     */
    public static int holoRedLightColor() {
        return GWSColor.parseColor("#ffff4444");
    }

    /**
     * Holo blue dark color.
     *
     * @return the int
     */
    public static int holoBlueDarkColor() {
        return GWSColor.parseColor("#ff0099cc");
    }

    /**
     * Holo green dark color.
     *
     * @return the int
     */
    public static int holoGreenDarkColor() {
        return GWSColor.parseColor("#ff669900");
    }

    /**
     * Holo red dark color.
     *
     * @return the int
     */
    public static int holoRedDarkColor() {
        return GWSColor.parseColor("#ffcc0000");
    }

    /**
     * Holo purple color.
     *
     * @return the int
     */
    public static int holoPurpleColor() {
        return GWSColor.parseColor("#ffaa66cc");
    }

    /**
     * Holo orange light color.
     *
     * @return the int
     */
    public static int holoOrangeLightColor() {
        return GWSColor.parseColor("#ffffbb33");
    }

    /**
     * Holo orange dark color.
     *
     * @return the int
     */
    public static int holoOrangeDarkColor() {
        return GWSColor.parseColor("#ffff8800");
    }

    /**
     * Holo blue bright color.
     *
     * @return the int
     */
    public static int holoBlueBrightColor() {
        return GWSColor.parseColor("#ff00ddff");
    }

    // Holo Background colors

    /**
     * Background dark color.
     *
     * @return the int
     */
    public static int backgroundDarkColor() {
        return GWSColor.parseColor("#ff000000");
    }

    /**
     * Background light color.
     *
     * @return the int
     */
    public static int backgroundLightColor() {
        return GWSColor.parseColor("#ffffffff");
    }

    /**
     * Bright foreground dark color.
     *
     * @return the int
     */
    public static int brightForegroundDarkColor() {
        return GWSColor.parseColor("#ffffffff");
    }

    /**
     * Bright foreground light color.
     *
     * @return the int
     */
    public static int brightForegroundLightColor() {
        return GWSColor.parseColor("#ff000000");
    }

    /**
     * Bright foreground dark disabled color.
     *
     * @return the int
     */
    public static int brightForegroundDarkDisabledColor() {
        return GWSColor.parseColor("#80ffffff");
    }

    /**
     * Background holo dark color.
     *
     * @return the int
     */
    public static int backgroundHoloDarkColor() {
        return GWSColor.parseColor("#ff000000");
    }

    /**
     * Background holo light color.
     *
     * @return the int
     */
    public static int backgroundHoloLightColor() {
        return GWSColor.parseColor("#fff3f3f3");
    }

    /**
     * Bright foreground holo dark color.
     *
     * @return the int
     */
    public static int brightForegroundHoloDarkColor() {
        return GWSColor.parseColor("#fff3f3f3");
    }

    /**
     * Bright foreground holo light color.
     *
     * @return the int
     */
    public static int brightForegroundHoloLightColor() {
        return GWSColor.parseColor("#ff000000");
    }

    /**
     * Bright foreground disabled holo dark color.
     *
     * @return the int
     */
    public static int brightForegroundDisabledHoloDarkColor() {
        return GWSColor.parseColor("#ff4c4c4c");
    }

    /**
     * Bright foreground disabled holo light color.
     *
     * @return the int
     */
    public static int brightForegroundDisabledHoloLightColor() {
        return GWSColor.parseColor("#ffb2b2b2");
    }

    /**
     * Dim foreground holo dark color.
     *
     * @return the int
     */
    public static int dimForegroundHoloDarkColor() {
        return GWSColor.parseColor("#bebebe");
    }

    /**
     * Dim foreground disabled holo dark color.
     *
     * @return the int
     */
    public static int dimForegroundDisabledHoloDarkColor() {
        return GWSColor.parseColor("#80bebebe");
    }

    /**
     * Hint foreground holo dark color.
     *
     * @return the int
     */
    public static int hintForegroundHoloDarkColor() {
        return GWSColor.parseColor("#808080");
    }

    /**
     * Dim foreground holo light color.
     *
     * @return the int
     */
    public static int dimForegroundHoloLightColor() {
        return GWSColor.parseColor("#323232");
    }

    /**
     * Dim foreground disabled holo light color.
     *
     * @return the int
     */
    public static int dimForegroundDisabledHoloLightColor() {
        return GWSColor.parseColor("#80323232");
    }

    /**
     * Hint foreground holo light color.
     *
     * @return the int
     */
    public static int hintForegroundHoloLightColor() {
        return GWSColor.parseColor("#808080");
    }

    /**
     * Highlighted text holo dark color.
     *
     * @return the int
     */
    public static int highlightedTextHoloDarkColor() {
        return GWSColor.parseColor("#6633b5e5");
    }

    /**
     * Highlighted text holo light color.
     *
     * @return the int
     */
    public static int highlightedTextHoloLightColor() {
        return GWSColor.parseColor("#ff00ddff");
    }

    /**
     * Md red 50.
     *
     * @return the int
     */
// all material design colors
    //reds
    public static int mdRed50(){
        return GWSColor.parseColor("#fde0dc");
    }

    /**
     * Md red 100.
     *
     * @return the int
     */
    public static int mdRed100(){
        return GWSColor.parseColor("#f9bdbb");
    }

    /**
     * Md red 200.
     *
     * @return the int
     */
    public static int mdRed200(){
        return GWSColor.parseColor("#f69988");
    }

    /**
     * Md red 300.
     *
     * @return the int
     */
    public static int mdRed300(){
        return GWSColor.parseColor("#f36c60");
    }

    /**
     * Md red 400.
     *
     * @return the int
     */
    public static int mdRed400(){
        return GWSColor.parseColor("#e84e40");
    }

    /**
     * Md red 500.
     *
     * @return the int
     */
    public static int mdRed500(){
        return GWSColor.parseColor("#e51c23");
    }

    /**
     * Md red 600.
     *
     * @return the int
     */
    public static int mdRed600(){
        return GWSColor.parseColor("#dd191d");
    }

    /**
     * Md red 700.
     *
     * @return the int
     */
    public static int mdRed700(){
        return GWSColor.parseColor("#d01716");
    }

    /**
     * Md red 800.
     *
     * @return the int
     */
    public static int mdRed800(){
        return GWSColor.parseColor("#c41411");
    }

    /**
     * Md red 900.
     *
     * @return the int
     */
    public static int mdRed900(){
        return GWSColor.parseColor("#b0120a");
    }

    /**
     * Md red a 100.
     *
     * @return the int
     */
    public static int mdRedA100(){
        return GWSColor.parseColor("#ff7997");
    }

    /**
     * Md red a 200.
     *
     * @return the int
     */
    public static int mdRedA200(){
        return GWSColor.parseColor("#ff5177");
    }

    /**
     * Md red a 400.
     *
     * @return the int
     */
    public static int mdRedA400(){
        return GWSColor.parseColor("#ff2d6f");
    }

    /**
     * Md red a 700.
     *
     * @return the int
     */
    public static int mdRedA700(){
        return GWSColor.parseColor("#e00032");
    }

    /**
     * Md pink 50.
     *
     * @return the int
     */
//pinks
    public static int mdPink50(){
        return GWSColor.parseColor("#fce4ec");
    }

    /**
     * Md pink 100.
     *
     * @return the int
     */
    public static int mdPink100(){
        return GWSColor.parseColor("#f8bbd0");
    }

    /**
     * Md pink 200.
     *
     * @return the int
     */
    public static int mdPink200(){
        return GWSColor.parseColor("#f48fb1");
    }

    /**
     * Md pink 300.
     *
     * @return the int
     */
    public static int mdPink300(){
        return GWSColor.parseColor("#f06292");
    }

    /**
     * Md pink 400.
     *
     * @return the int
     */
    public static int mdPink400(){
        return GWSColor.parseColor("#ec407a");
    }

    /**
     * Md pink 500.
     *
     * @return the int
     */
    public static int mdPink500(){
        return GWSColor.parseColor("#e91e63");
    }

    /**
     * Md pink 600.
     *
     * @return the int
     */
    public static int mdPink600(){
        return GWSColor.parseColor("#d81b60");
    }

    /**
     * Md pink 700.
     *
     * @return the int
     */
    public static int mdPink700(){
        return GWSColor.parseColor("#c2185b");
    }

    /**
     * Md pink 800.
     *
     * @return the int
     */
    public static int mdPink800(){
        return GWSColor.parseColor("#ad1457");
    }

    /**
     * Md pink 900.
     *
     * @return the int
     */
    public static int mdPink900(){
        return GWSColor.parseColor("#880e4f");
    }

    /**
     * Md pink a 100.
     *
     * @return the int
     */
    public static int mdPinkA100(){
        return GWSColor.parseColor("#ff80ab");
    }

    /**
     * Md pink a 200.
     *
     * @return the int
     */
    public static int mdPinkA200(){
        return GWSColor.parseColor("#ff4081");
    }

    /**
     * Md pink a 400.
     *
     * @return the int
     */
    public static int mdPinkA400(){
        return GWSColor.parseColor("#f50057");
    }

    /**
     * Md pink a 700.
     *
     * @return the int
     */
    public static int mdPinkA700(){
        return GWSColor.parseColor("#c51162");
    }

    /**
     * Md purple 50.
     *
     * @return the int
     */
//purples
    public static int mdPurple50(){
        return GWSColor.parseColor("#f3e5f5");
    }

    /**
     * Md purple 100.
     *
     * @return the int
     */
    public static int mdPurple100(){
        return GWSColor.parseColor("#e1bee7");
    }

    /**
     * Md purple 200.
     *
     * @return the int
     */
    public static int mdPurple200(){
        return GWSColor.parseColor("#ce93d8");
    }

    /**
     * Md purple 300.
     *
     * @return the int
     */
    public static int mdPurple300(){
        return GWSColor.parseColor("#ba68c8");
    }

    /**
     * Md purple 400.
     *
     * @return the int
     */
    public static int mdPurple400(){
        return GWSColor.parseColor("#ab47bc");
    }

    /**
     * Md purple 500.
     *
     * @return the int
     */
    public static int mdPurple500(){
        return GWSColor.parseColor("#9c27b0");
    }

    /**
     * Md purple 600.
     *
     * @return the int
     */
    public static int mdPurple600(){
        return GWSColor.parseColor("#8e24aa");
    }

    /**
     * Md purple 700.
     *
     * @return the int
     */
    public static int mdPurple700(){
        return GWSColor.parseColor("#7b1fa2");
    }

    /**
     * Md purple 800.
     *
     * @return the int
     */
    public static int mdPurple800(){
        return GWSColor.parseColor("#6a1b9a");
    }

    /**
     * Md purple 900.
     *
     * @return the int
     */
    public static int mdPurple900(){
        return GWSColor.parseColor("#4a148c");
    }

    /**
     * Md purple a 100.
     *
     * @return the int
     */
    public static int mdPurpleA100(){
        return GWSColor.parseColor("#ea80fc");
    }

    /**
     * Md purple a 200.
     *
     * @return the int
     */
    public static int mdPurpleA200(){
        return GWSColor.parseColor("#e040fb");
    }

    /**
     * Md purple a 400.
     *
     * @return the int
     */
    public static int mdPurpleA400(){
        return GWSColor.parseColor("#d500f9");
    }

    /**
     * Md purple a 700.
     *
     * @return the int
     */
    public static int mdPurpleA700(){
        return GWSColor.parseColor("#aa00ff");
    }

    /**
     * Md deep purple 50.
     *
     * @return the int
     */
//deep purples
    public static int mdDeepPurple50(){
        return GWSColor.parseColor("#ede7f6");
    }

    /**
     * Md deep purple 100.
     *
     * @return the int
     */
    public static int mdDeepPurple100(){
        return GWSColor.parseColor("#d1c4e9");
    }

    /**
     * Md deep purple 200.
     *
     * @return the int
     */
    public static int mdDeepPurple200(){
        return GWSColor.parseColor("#b39ddb");
    }

    /**
     * Md deep purple 300.
     *
     * @return the int
     */
    public static int mdDeepPurple300(){
        return GWSColor.parseColor("#9575cd");
    }

    /**
     * Md deep purple 400.
     *
     * @return the int
     */
    public static int mdDeepPurple400(){
        return GWSColor.parseColor("#7e57c2");
    }

    /**
     * Md deep purple 500.
     *
     * @return the int
     */
    public static int mdDeepPurple500(){
        return GWSColor.parseColor("#673ab7");
    }

    /**
     * Md deep purple 600.
     *
     * @return the int
     */
    public static int mdDeepPurple600(){
        return GWSColor.parseColor("#5e35b1");
    }

    /**
     * Md deep purple 700.
     *
     * @return the int
     */
    public static int mdDeepPurple700(){
        return GWSColor.parseColor("#512da8");
    }

    /**
     * Md deep purple 800.
     *
     * @return the int
     */
    public static int mdDeepPurple800(){
        return GWSColor.parseColor("#4527a0");
    }

    /**
     * Md deep purple 900.
     *
     * @return the int
     */
    public static int mdDeepPurple900(){
        return GWSColor.parseColor("#311b92");
    }

    /**
     * Md deep purple a 100.
     *
     * @return the int
     */
    public static int mdDeepPurpleA100(){
        return GWSColor.parseColor("#b388ff");
    }

    /**
     * Md deep purple a 200.
     *
     * @return the int
     */
    public static int mdDeepPurpleA200(){
        return GWSColor.parseColor("#7c4dff");
    }

    /**
     * Md deep purple a 400.
     *
     * @return the int
     */
    public static int mdDeepPurpleA400(){
        return GWSColor.parseColor("#651fff");
    }

    /**
     * Md deep purple a 700.
     *
     * @return the int
     */
    public static int mdDeepPurpleA700(){
        return GWSColor.parseColor("#6200ea");
    }

    /**
     * Md indigo 50.
     *
     * @return the int
     */
//indigo
    public static int mdIndigo50(){
        return GWSColor.parseColor("#e8eaf6");
    }

    /**
     * Md indigo 100.
     *
     * @return the int
     */
    public static int mdIndigo100(){
        return GWSColor.parseColor("#c5cae0");
    }

    /**
     * Md indigo 200.
     *
     * @return the int
     */
    public static int mdIndigo200(){
        return GWSColor.parseColor("#9fa8da");
    }

    /**
     * Md indigo 300.
     *
     * @return the int
     */
    public static int mdIndigo300(){
        return GWSColor.parseColor("#7986cb");
    }

    /**
     * Md indigo 400.
     *
     * @return the int
     */
    public static int mdIndigo400(){
        return GWSColor.parseColor("#5c6bc0");
    }

    /**
     * Md indigo 500.
     *
     * @return the int
     */
    public static int mdIndigo500(){
        return GWSColor.parseColor("#3f51b5");
    }

    /**
     * Md indigo 600.
     *
     * @return the int
     */
    public static int mdIndigo600(){
        return GWSColor.parseColor("#3949ab");
    }

    /**
     * Md indigo 700.
     *
     * @return the int
     */
    public static int mdIndigo700(){
        return GWSColor.parseColor("#303f9f");
    }

    /**
     * Md indigo 800.
     *
     * @return the int
     */
    public static int mdIndigo800(){
        return GWSColor.parseColor("#283593");
    }

    /**
     * Md indigo 900.
     *
     * @return the int
     */
    public static int mdIndigo900(){
        return GWSColor.parseColor("#1a237e");
    }

    /**
     * Md indigo a 100.
     *
     * @return the int
     */
    public static int mdIndigoA100(){
        return GWSColor.parseColor("#8c9eff");
    }

    /**
     * Md indigo a 200.
     *
     * @return the int
     */
    public static int mdIndigoA200(){
        return GWSColor.parseColor("#536dfe");
    }

    /**
     * Md indigo a 400.
     *
     * @return the int
     */
    public static int mdIndigoA400(){
        return GWSColor.parseColor("#3d5afe");
    }

    /**
     * Md indigo a 700.
     *
     * @return the int
     */
    public static int mdIndigoA700(){
        return GWSColor.parseColor("#304ffe");
    }

    /**
     * Md blue 50.
     *
     * @return the int
     */
//blue
    public static int mdBlue50(){
        return GWSColor.parseColor("#e7e9fd");
    }

    /**
     * Md blue 100.
     *
     * @return the int
     */
    public static int mdBlue100(){
        return GWSColor.parseColor("#d0d9ff");
    }

    /**
     * Md blue 200.
     *
     * @return the int
     */
    public static int mdBlue200(){
        return GWSColor.parseColor("#afbfff");
    }

    /**
     * Md blue 300.
     *
     * @return the int
     */
    public static int mdBlue300(){
        return GWSColor.parseColor("#91a7ff");
    }

    /**
     * Md blue 400.
     *
     * @return the int
     */
    public static int mdBlue400(){
        return GWSColor.parseColor("#738ffe");
    }

    /**
     * Md blue 500.
     *
     * @return the int
     */
    public static int mdBlue500(){
        return GWSColor.parseColor("#5677fc");
    }

    /**
     * Md blue 600.
     *
     * @return the int
     */
    public static int mdBlue600(){
        return GWSColor.parseColor("#4e6cef");
    }

    /**
     * Md blue 700.
     *
     * @return the int
     */
    public static int mdBlue700(){
        return GWSColor.parseColor("#455ede");
    }

    /**
     * Md blue 800.
     *
     * @return the int
     */
    public static int mdBlue800(){
        return GWSColor.parseColor("#3b50ce");
    }

    /**
     * Md blue 900.
     *
     * @return the int
     */
    public static int mdBlue900(){
        return GWSColor.parseColor("#2a36b1");
    }

    /**
     * Md blue a 100.
     *
     * @return the int
     */
    public static int mdBlueA100(){
        return GWSColor.parseColor("#a6baff");
    }

    /**
     * Md blue a 200.
     *
     * @return the int
     */
    public static int mdBlueA200(){
        return GWSColor.parseColor("#6889ff");
    }

    /**
     * Md blue a 400.
     *
     * @return the int
     */
    public static int mdBlueA400(){
        return GWSColor.parseColor("#4d73ff");
    }

    /**
     * Md blue a 700.
     *
     * @return the int
     */
    public static int mdBlueA700(){
        return GWSColor.parseColor("#4d69ff");
    }

    /**
     * Md light blue 50.
     *
     * @return the int
     */
//light blue
    public static int mdLightBlue50(){
        return GWSColor.parseColor("#e1f5fe");
    }

    /**
     * Md light blue 100.
     *
     * @return the int
     */
    public static int mdLightBlue100(){
        return GWSColor.parseColor("#b3e5fc");
    }

    /**
     * Md light blue 200.
     *
     * @return the int
     */
    public static int mdLightBlue200(){
        return GWSColor.parseColor("#81d4fa");
    }

    /**
     * Md light blue 300.
     *
     * @return the int
     */
    public static int mdLightBlue300(){
        return GWSColor.parseColor("#4fc3f7");
    }

    /**
     * Md light blue 400.
     *
     * @return the int
     */
    public static int mdLightBlue400(){
        return GWSColor.parseColor("#29b6f6");
    }

    /**
     * Md light blue 500.
     *
     * @return the int
     */
    public static int mdLightBlue500(){
        return GWSColor.parseColor("#03a9f4");
    }

    /**
     * Md light blue 600.
     *
     * @return the int
     */
    public static int mdLightBlue600(){
        return GWSColor.parseColor("#039be5");
    }

    /**
     * Md light blue 700.
     *
     * @return the int
     */
    public static int mdLightBlue700(){
        return GWSColor.parseColor("#0288d1");
    }

    /**
     * Md light blue 800.
     *
     * @return the int
     */
    public static int mdLightBlue800(){
        return GWSColor.parseColor("#0277bd");
    }

    /**
     * Md light blue 900.
     *
     * @return the int
     */
    public static int mdLightBlue900(){
        return GWSColor.parseColor("#01579b");
    }

    /**
     * Md light blue a 100.
     *
     * @return the int
     */
    public static int mdLightBlueA100(){
        return GWSColor.parseColor("#80d8ff");
    }

    /**
     * Md light blue a 200.
     *
     * @return the int
     */
    public static int mdLightBlueA200(){
        return GWSColor.parseColor("#40c4ff");
    }

    /**
     * Md light blue a 400.
     *
     * @return the int
     */
    public static int mdLightBlueA400(){
        return GWSColor.parseColor("#00b0ff");
    }

    /**
     * Md light blue a 700.
     *
     * @return the int
     */
    public static int mdLightBlueA700(){
        return GWSColor.parseColor("#0091ea");
    }

    /**
     * Md cyan 50.
     *
     * @return the int
     */
//cyan
    public static int mdCyan50(){
        return GWSColor.parseColor("#e0f7fa");
    }

    /**
     * Md cyan 100.
     *
     * @return the int
     */
    public static int mdCyan100(){
        return GWSColor.parseColor("#b2ebf2");
    }

    /**
     * Md cyan 200.
     *
     * @return the int
     */
    public static int mdCyan200(){
        return GWSColor.parseColor("#80deea");
    }

    /**
     * Md cyan 300.
     *
     * @return the int
     */
    public static int mdCyan300(){
        return GWSColor.parseColor("#4dd0e1");
    }

    /**
     * Md cyan 400.
     *
     * @return the int
     */
    public static int mdCyan400(){
        return GWSColor.parseColor("#26c6da");
    }

    /**
     * Md cyan 500.
     *
     * @return the int
     */
    public static int mdCyan500(){
        return GWSColor.parseColor("#00bcd4");
    }

    /**
     * Md cyan 600.
     *
     * @return the int
     */
    public static int mdCyan600(){
        return GWSColor.parseColor("#00acc1");
    }

    /**
     * Md cyan 700.
     *
     * @return the int
     */
    public static int mdCyan700(){
        return GWSColor.parseColor("#0097a7");
    }

    /**
     * Md cyan 800.
     *
     * @return the int
     */
    public static int mdCyan800(){
        return GWSColor.parseColor("#00838f");
    }

    /**
     * Md cyan 900.
     *
     * @return the int
     */
    public static int mdCyan900(){
        return GWSColor.parseColor("#006064");
    }

    /**
     * Md cyan a 100.
     *
     * @return the int
     */
    public static int mdCyanA100(){
        return GWSColor.parseColor("#84ffff");
    }

    /**
     * Md cyan a 200.
     *
     * @return the int
     */
    public static int mdCyanA200(){
        return GWSColor.parseColor("#18ffff");
    }

    /**
     * Md cyan a 400.
     *
     * @return the int
     */
    public static int mdCyanA400(){
        return GWSColor.parseColor("#00e5ff");
    }

    /**
     * Md cyan a 700.
     *
     * @return the int
     */
    public static int mdCyanA700(){
        return GWSColor.parseColor("#00b8d4");
    }

    /**
     * Md teal 50.
     *
     * @return the int
     */
//teal
    public static int mdTeal50(){
        return GWSColor.parseColor("#e0f2f1");
    }

    /**
     * Md teal 100.
     *
     * @return the int
     */
    public static int mdTeal100(){
        return GWSColor.parseColor("#b2dfdb");
    }

    /**
     * Md teal 200.
     *
     * @return the int
     */
    public static int mdTeal200(){
        return GWSColor.parseColor("#80cbc4");
    }

    /**
     * Md teal 300.
     *
     * @return the int
     */
    public static int mdTeal300(){
        return GWSColor.parseColor("#4db6ac");
    }

    /**
     * Md teal 400.
     *
     * @return the int
     */
    public static int mdTeal400(){
        return GWSColor.parseColor("26a69a");
    }

    /**
     * Md teal 500.
     *
     * @return the int
     */
    public static int mdTeal500(){
        return GWSColor.parseColor("#009688");
    }

    /**
     * Md teal 600.
     *
     * @return the int
     */
    public static int mdTeal600(){
        return GWSColor.parseColor("#00897b");
    }

    /**
     * Md teal 700.
     *
     * @return the int
     */
    public static int mdTeal700(){
        return GWSColor.parseColor("#00796b");
    }

    /**
     * Md teal 800.
     *
     * @return the int
     */
    public static int mdTeal800(){
        return GWSColor.parseColor("#00695c");
    }

    /**
     * Md teal 900.
     *
     * @return the int
     */
    public static int mdTeal900(){
        return GWSColor.parseColor("#004d40");
    }

    /**
     * Md teal a 100.
     *
     * @return the int
     */
    public static int mdTealA100(){
        return GWSColor.parseColor("#a7ffeb");
    }

    /**
     * Md teal a 200.
     *
     * @return the int
     */
    public static int mdTealA200(){
        return GWSColor.parseColor("#64ffda");
    }

    /**
     * Md teal a 400.
     *
     * @return the int
     */
    public static int mdTealA400(){
        return GWSColor.parseColor("#1de9b6");
    }

    /**
     * Md teal a 700.
     *
     * @return the int
     */
    public static int mdTealA700(){
        return GWSColor.parseColor("#00bfa5");
    }

    /**
     * Md green 50.
     *
     * @return the int
     */
//green
    public static int mdGreen50(){
        return GWSColor.parseColor("#d0f8ce");
    }

    /**
     * Md green 100.
     *
     * @return the int
     */
    public static int mdGreen100(){
        return GWSColor.parseColor("#a3e9a4");
    }

    /**
     * Md green 200.
     *
     * @return the int
     */
    public static int mdGreen200(){
        return GWSColor.parseColor("#72d572");
    }

    /**
     * Md green 300.
     *
     * @return the int
     */
    public static int mdGreen300(){
        return GWSColor.parseColor("#42bd41");
    }

    /**
     * Md green 400.
     *
     * @return the int
     */
    public static int mdGreen400(){
        return GWSColor.parseColor("#2baf2b");
    }

    /**
     * Md green 500.
     *
     * @return the int
     */
    public static int mdGreen500(){
        return GWSColor.parseColor("#2baf2b");
    }

    /**
     * Md green 600.
     *
     * @return the int
     */
    public static int mdGreen600(){
        return GWSColor.parseColor("#0a8f08");
    }

    /**
     * Md green 700.
     *
     * @return the int
     */
    public static int mdGreen700(){
        return GWSColor.parseColor("#0a7e07");
    }

    /**
     * Md green 800.
     *
     * @return the int
     */
    public static int mdGreen800(){
        return GWSColor.parseColor("#056f00");
    }

    /**
     * Md green 900.
     *
     * @return the int
     */
    public static int mdGreen900(){
        return GWSColor.parseColor("#0d5302");
    }

    /**
     * Md green a 100.
     *
     * @return the int
     */
    public static int mdGreenA100(){
        return GWSColor.parseColor("#a2f78d");
    }

    /**
     * Md green a 200.
     *
     * @return the int
     */
    public static int mdGreenA200(){
        return GWSColor.parseColor("#5af158");
    }

    /**
     * Md green a 400.
     *
     * @return the int
     */
    public static int mdGreenA400(){
        return GWSColor.parseColor("#14e715");
    }

    /**
     * Md green a 700.
     *
     * @return the int
     */
    public static int mdGreenA700(){
        return GWSColor.parseColor("#12c700");
    }


    /**
     * Md light green 50.
     *
     * @return the int
     */
//light green
    public static int mdLightGreen50(){
        return GWSColor.parseColor("#f1f8e9");
    }

    /**
     * Md light green 100.
     *
     * @return the int
     */
    public static int mdLightGreen100(){
        return GWSColor.parseColor("#dcedc8");
    }

    /**
     * Md light green 200.
     *
     * @return the int
     */
    public static int mdLightGreen200(){
        return GWSColor.parseColor("#c5e1a5");
    }

    /**
     * Md light green 300.
     *
     * @return the int
     */
    public static int mdLightGreen300(){
        return GWSColor.parseColor("#aed581");
    }

    /**
     * Md light green 400.
     *
     * @return the int
     */
    public static int mdLightGreen400(){
        return GWSColor.parseColor("#9ccc65");
    }

    /**
     * Md light green 500.
     *
     * @return the int
     */
    public static int mdLightGreen500(){
        return GWSColor.parseColor("#8bc34a");
    }

    /**
     * Md light green 600.
     *
     * @return the int
     */
    public static int mdLightGreen600(){
        return GWSColor.parseColor("#7cb342");
    }

    /**
     * Md light green 700.
     *
     * @return the int
     */
    public static int mdLightGreen700(){
        return GWSColor.parseColor("#689f38");
    }

    /**
     * Md light green 800.
     *
     * @return the int
     */
    public static int mdLightGreen800(){
        return GWSColor.parseColor("#558b2f");
    }

    /**
     * Md light green 900.
     *
     * @return the int
     */
    public static int mdLightGreen900(){
        return GWSColor.parseColor("#33691e");
    }

    /**
     * Md light green a 100.
     *
     * @return the int
     */
    public static int mdLightGreenA100(){
        return GWSColor.parseColor("#ccff90");
    }

    /**
     * Md light green a 200.
     *
     * @return the int
     */
    public static int mdLightGreenA200(){
        return GWSColor.parseColor("#eeff41");
    }

    /**
     * Md light green a 400.
     *
     * @return the int
     */
    public static int mdLightGreenA400(){
        return GWSColor.parseColor("#76ff03");
    }

    /**
     * Md light green a 700.
     *
     * @return the int
     */
    public static int mdLightGreenA700(){
        return GWSColor.parseColor("#64dd17");
    }

    /**
     * Md lime 50.
     *
     * @return the int
     */
//lime
    public static int mdLime50(){
        return GWSColor.parseColor("#f9fbe7");
    }

    /**
     * Md lime 100.
     *
     * @return the int
     */
    public static int mdLime100(){
        return GWSColor.parseColor("#f0f4c3");
    }

    /**
     * Md lime 200.
     *
     * @return the int
     */
    public static int mdLime200(){
        return GWSColor.parseColor("#e6ee9c");
    }

    /**
     * Md lime 300.
     *
     * @return the int
     */
    public static int mdLime300(){
        return GWSColor.parseColor("#dce775");
    }

    /**
     * Md lime 400.
     *
     * @return the int
     */
    public static int mdLime400(){
        return GWSColor.parseColor("#d4e157");
    }

    /**
     * Md lime 500.
     *
     * @return the int
     */
    public static int mdLime500(){
        return GWSColor.parseColor("#cddc30");
    }

    /**
     * Md lime 600.
     *
     * @return the int
     */
    public static int mdLime600(){
        return GWSColor.parseColor("#c0ca33");
    }

    /**
     * Md lime 700.
     *
     * @return the int
     */
    public static int mdLime700(){
        return GWSColor.parseColor("#afb42b");
    }

    /**
     * Md lime 800.
     *
     * @return the int
     */
    public static int mdLime800(){
        return GWSColor.parseColor("#9e9d24");
    }

    /**
     * Md lime 900.
     *
     * @return the int
     */
    public static int mdLime900(){
        return GWSColor.parseColor("#827717");
    }

    /**
     * Md lime a 100.
     *
     * @return the int
     */
    public static int mdLimeA100(){
        return GWSColor.parseColor("#f4ff81");
    }

    /**
     * Md lime a 200.
     *
     * @return the int
     */
    public static int mdLimeA200(){
        return GWSColor.parseColor("#eeff41");
    }

    /**
     * Md lime a 400.
     *
     * @return the int
     */
    public static int mdLimeA400(){
        return GWSColor.parseColor("#c6ff00");
    }

    /**
     * Md lime a 700.
     *
     * @return the int
     */
    public static int mdLimeA700(){
        return GWSColor.parseColor("#aeea00");
    }

    /**
     * Md yellow 50.
     *
     * @return the int
     */
//yellow
    public static int mdYellow50(){
        return GWSColor.parseColor("#fffde7");
    }

    /**
     * Md yellow 100.
     *
     * @return the int
     */
    public static int mdYellow100(){
        return GWSColor.parseColor("#fff9c4");
    }

    /**
     * Md yellow 200.
     *
     * @return the int
     */
    public static int mdYellow200(){
        return GWSColor.parseColor("#fff59d");
    }

    /**
     * Md yellow 300.
     *
     * @return the int
     */
    public static int mdYellow300(){
        return GWSColor.parseColor("#fff176");
    }

    /**
     * Md yellow 400.
     *
     * @return the int
     */
    public static int mdYellow400(){
        return GWSColor.parseColor("#ffee58");
    }

    /**
     * Md yellow 500.
     *
     * @return the int
     */
    public static int mdYellow500(){
        return GWSColor.parseColor("#ffeb3b");
    }

    /**
     * Md yellow 600.
     *
     * @return the int
     */
    public static int mdYellow600(){
        return GWSColor.parseColor("#fdd835");
    }

    /**
     * Md yellow 700.
     *
     * @return the int
     */
    public static int mdYellow700(){
        return GWSColor.parseColor("#fbc02d");
    }

    /**
     * Md yellow 800.
     *
     * @return the int
     */
    public static int mdYellow800(){
        return GWSColor.parseColor("#f9a825");
    }

    /**
     * Md yellow 900.
     *
     * @return the int
     */
    public static int mdYellow900(){
        return GWSColor.parseColor("#f57f17");
    }

    /**
     * Md yellow a 100.
     *
     * @return the int
     */
    public static int mdYellowA100(){
        return GWSColor.parseColor("#ffff8d");
    }

    /**
     * Md yellow a 200.
     *
     * @return the int
     */
    public static int mdYellowA200(){
        return GWSColor.parseColor("#ffff00");
    }

    /**
     * Md yellow a 400.
     *
     * @return the int
     */
    public static int mdYellowA400(){
        return GWSColor.parseColor("#ffea00");
    }

    /**
     * Md yellow a 700.
     *
     * @return the int
     */
    public static int mdYellowA700(){
        return GWSColor.parseColor("#ffd600");
    }


    /**
     * Md amber 50.
     *
     * @return the int
     */
//amber
    public static int mdAmber50(){
        return GWSColor.parseColor("#fff8e1");
    }

    /**
     * Md amber 100.
     *
     * @return the int
     */
    public static int mdAmber100(){
        return GWSColor.parseColor("#ffecb3");
    }

    /**
     * Md amber 200.
     *
     * @return the int
     */
    public static int mdAmber200(){
        return GWSColor.parseColor("#ffe082");
    }

    /**
     * Md amber 300.
     *
     * @return the int
     */
    public static int mdAmber300(){
        return GWSColor.parseColor("#ffd54f");
    }

    /**
     * Md amber 400.
     *
     * @return the int
     */
    public static int mdAmber400(){
        return GWSColor.parseColor("#ffca28");
    }

    /**
     * Md amber 500.
     *
     * @return the int
     */
    public static int mdAmber500(){
        return GWSColor.parseColor("#ffc107");
    }

    /**
     * Md amber 600.
     *
     * @return the int
     */
    public static int mdAmber600(){
        return GWSColor.parseColor("#ffb300");
    }

    /**
     * Md amber 700.
     *
     * @return the int
     */
    public static int mdAmber700(){
        return GWSColor.parseColor("#ffa000");
    }

    /**
     * Md amber 800.
     *
     * @return the int
     */
    public static int mdAmber800(){
        return GWSColor.parseColor("#ff8f00");
    }

    /**
     * Md amber 900.
     *
     * @return the int
     */
    public static int mdAmber900(){
        return GWSColor.parseColor("#ff6f00");
    }

    /**
     * Md amber a 100.
     *
     * @return the int
     */
    public static int mdAmberA100(){
        return GWSColor.parseColor("#ffe57f");
    }

    /**
     * Md amber a 200.
     *
     * @return the int
     */
    public static int mdAmberA200(){
        return GWSColor.parseColor("#ffd740");
    }

    /**
     * Md amber a 400.
     *
     * @return the int
     */
    public static int mdAmberA400(){
        return GWSColor.parseColor("#ffc400");
    }

    /**
     * Md amber a 700.
     *
     * @return the int
     */
    public static int mdAmberA700(){
        return GWSColor.parseColor("#ffab00");
    }

    /**
     * Md orange 50.
     *
     * @return the int
     */
//orange
    public static int mdOrange50(){
        return GWSColor.parseColor("#ffff3e0");
    }

    /**
     * Md orange 100.
     *
     * @return the int
     */
    public static int mdOrange100(){
        return GWSColor.parseColor("#ffe0b2");
    }

    /**
     * Md orange 200.
     *
     * @return the int
     */
    public static int mdOrange200(){
        return GWSColor.parseColor("#ffcc80");
    }

    /**
     * Md orange 300.
     *
     * @return the int
     */
    public static int mdOrange300(){
        return GWSColor.parseColor("#ffb74d");
    }

    /**
     * Md orange 400.
     *
     * @return the int
     */
    public static int mdOrange400(){
        return GWSColor.parseColor("#ffa726");
    }

    /**
     * Md orange 500.
     *
     * @return the int
     */
    public static int mdOrange500(){
        return GWSColor.parseColor("#ff9800");
    }

    /**
     * Md orange 600.
     *
     * @return the int
     */
    public static int mdOrange600(){
        return GWSColor.parseColor("#fb8c00");
    }

    /**
     * Md orange 700.
     *
     * @return the int
     */
    public static int mdOrange700(){
        return GWSColor.parseColor("#f57c00");
    }

    /**
     * Md orange 800.
     *
     * @return the int
     */
    public static int mdOrange800(){
        return GWSColor.parseColor("#ef6c0");
    }

    /**
     * Md orange 900.
     *
     * @return the int
     */
    public static int mdOrange900(){
        return GWSColor.parseColor("#e65100");
    }

    /**
     * Md orange a 100.
     *
     * @return the int
     */
    public static int mdOrangeA100(){
        return GWSColor.parseColor("#ffd180");
    }

    /**
     * Md orange a 200.
     *
     * @return the int
     */
    public static int mdOrangeA200(){
        return GWSColor.parseColor("#ffab40");
    }

    /**
     * Md orange a 400.
     *
     * @return the int
     */
    public static int mdOrangeA400(){
        return GWSColor.parseColor("#ff9100");
    }

    /**
     * Md orange a 700.
     *
     * @return the int
     */
    public static int mdOrangeA700(){
        return GWSColor.parseColor("#ff6d00");
    }

    /**
     * Md deep orange 50.
     *
     * @return the int
     */
//deep orange
    public static int mdDeepOrange50(){
        return GWSColor.parseColor("#fbe9e7");
    }

    /**
     * Md deep orange 100.
     *
     * @return the int
     */
    public static int mdDeepOrange100(){
        return GWSColor.parseColor("#ffccbc");
    }

    /**
     * Md deep orange 200.
     *
     * @return the int
     */
    public static int mdDeepOrange200(){
        return GWSColor.parseColor("#ffab91");
    }

    /**
     * Md deep orange 300.
     *
     * @return the int
     */
    public static int mdDeepOrange300(){
        return GWSColor.parseColor("#ff8a65");
    }

    /**
     * Md deep orange 400.
     *
     * @return the int
     */
    public static int mdDeepOrange400(){
        return GWSColor.parseColor("#ff7043");
    }

    /**
     * Md deep orange 500.
     *
     * @return the int
     */
    public static int mdDeepOrange500(){
        return GWSColor.parseColor("#ff5722");
    }

    /**
     * Md deep orange 600.
     *
     * @return the int
     */
    public static int mdDeepOrange600(){
        return GWSColor.parseColor("#f4511e");
    }

    /**
     * Md deep orange 700.
     *
     * @return the int
     */
    public static int mdDeepOrange700(){
        return GWSColor.parseColor("#e64a19");
    }

    /**
     * Md deep orange 800.
     *
     * @return the int
     */
    public static int mdDeepOrange800(){
        return GWSColor.parseColor("#d84315");
    }

    /**
     * Md deep orange 900.
     *
     * @return the int
     */
    public static int mdDeepOrange900(){
        return GWSColor.parseColor("#bf360c");
    }

    /**
     * Md deep orange a 100.
     *
     * @return the int
     */
    public static int mdDeepOrangeA100(){
        return GWSColor.parseColor("#ff9e80");
    }

    /**
     * Md deep orange a 200.
     *
     * @return the int
     */
    public static int mdDeepOrangeA200(){
        return GWSColor.parseColor("#ff6e40");
    }

    /**
     * Md deep orange a 400.
     *
     * @return the int
     */
    public static int mdDeepOrangeA400(){
        return GWSColor.parseColor("#ff3d00");
    }

    /**
     * Md deep orange a 700.
     *
     * @return the int
     */
    public static int mdDeepOrangeA700(){
        return GWSColor.parseColor("#dd2c00");
    }


    /**
     * Md brown 50.
     *
     * @return the int
     */
//brown
    public static int mdBrown50(){
        return GWSColor.parseColor("#efebe9");
    }

    /**
     * Md brown 100.
     *
     * @return the int
     */
    public static int mdBrown100(){
        return GWSColor.parseColor("#d7ccc8");
    }

    /**
     * Md brown 200.
     *
     * @return the int
     */
    public static int mdBrown200(){
        return GWSColor.parseColor("#bcaaa4");
    }

    /**
     * Md brown 300.
     *
     * @return the int
     */
    public static int mdBrown300(){
        return GWSColor.parseColor("#a1887f");
    }

    /**
     * Md brown 400.
     *
     * @return the int
     */
    public static int mdBrown400(){
        return GWSColor.parseColor("#8d6e63");
    }

    /**
     * Md brown 500.
     *
     * @return the int
     */
    public static int mdBrown500(){
        return GWSColor.parseColor("#795548");
    }

    /**
     * Md brown 600.
     *
     * @return the int
     */
    public static int mdBrown600(){
        return GWSColor.parseColor("#6d4c41");
    }

    /**
     * Md brown 700.
     *
     * @return the int
     */
    public static int mdBrown700(){
        return GWSColor.parseColor("#5d4037");
    }

    /**
     * Md brown 800.
     *
     * @return the int
     */
    public static int mdBrown800(){
        return GWSColor.parseColor("#4e342e");
    }

    /**
     * Md brown 900.
     *
     * @return the int
     */
    public static int mdBrown900(){
        return GWSColor.parseColor("#3e2723");
    }

    /**
     * Md grey 50.
     *
     * @return the int
     */
//grey
    public static int mdGrey50(){
        return GWSColor.parseColor("#fafafa");
    }

    /**
     * Md grey 100.
     *
     * @return the int
     */
    public static int mdGrey100(){
        return GWSColor.parseColor("#f5f5f5");

    }

    /**
     * Md grey 200.
     *
     * @return the int
     */
    public static int mdGrey200(){
        return GWSColor.parseColor("#eeeeee");
    }

    /**
     * Md grey 300.
     *
     * @return the int
     */
    public static int mdGrey300(){
        return GWSColor.parseColor("#e0e0e0");
    }

    /**
     * Md grey 400.
     *
     * @return the int
     */
    public static int mdGrey400(){
        return GWSColor.parseColor("#bdbdbd");
    }

    /**
     * Md grey 500.
     *
     * @return the int
     */
    public static int mdGrey500(){
        return GWSColor.parseColor("#9e9e9e");
    }

    /**
     * Md grey 600.
     *
     * @return the int
     */
    public static int mdGrey600(){
        return GWSColor.parseColor("#757575");
    }

    /**
     * Md grey 700.
     *
     * @return the int
     */
    public static int mdGrey700(){
        return GWSColor.parseColor("#616161");
    }

    /**
     * Md grey 800.
     *
     * @return the int
     */
    public static int mdGrey800(){
        return GWSColor.parseColor("#424242");
    }

    /**
     * Md grey 900.
     *
     * @return the int
     */
    public static int mdGrey900(){
        return GWSColor.parseColor("#212121");
    }

    /**
     * Md grey 1000 b.
     *
     * @return the int
     */
    public static int mdGrey1000b(){

        return GWSColor.parseColor("#000000");
    }

    /**
     * Md grey 1000 w.
     *
     * @return the int
     */
    public static int mdGrey1000w(){
        return GWSColor.parseColor("#ffffff");
    }


    /**
     * Md blue grey 50.
     *
     * @return the int
     */
//blue grey
    public static int mdBlueGrey50(){
        return GWSColor.parseColor("#eceff1");
    }

    /**
     * Md blue grey 100.
     *
     * @return the int
     */
    public static int mdBlueGrey100(){

        return GWSColor.parseColor("#cfd8dc");
    }

    /**
     * Md blue grey 200.
     *
     * @return the int
     */
    public static int mdBlueGrey200(){
        return GWSColor.parseColor("#b0bec5");
    }

    /**
     * Md blue grey 300.
     *
     * @return the int
     */
    public static int mdBlueGrey300(){
        return GWSColor.parseColor("#90a4ae");
    }

    /**
     * Md blue grey 400.
     *
     * @return the int
     */
    public static int mdBlueGrey400(){
        return GWSColor.parseColor("#78909c");
    }

    /**
     * Md blue grey 500.
     *
     * @return the int
     */
    public static int mdBlueGrey500(){
        return GWSColor.parseColor("#607d8b");
    }

    /**
     * Md blue grey 600.
     *
     * @return the int
     */
    public static int mdBlueGrey600(){
        return GWSColor.parseColor("#546e7a");
    }

    /**
     * Md blue grey 700.
     *
     * @return the int
     */
    public static int mdBlueGrey700(){
        return GWSColor.parseColor("#455a64");
    }

    /**
     * Md blue grey 800.
     *
     * @return the int
     */
    public static int mdBlueGrey800(){
        return GWSColor.parseColor("#37474f");
    }

    /**
     * Md blue grey 900.
     *
     * @return the int
     */
    public static int mdBlueGrey900(){
        return GWSColor.parseColor("#263238");
    }





}

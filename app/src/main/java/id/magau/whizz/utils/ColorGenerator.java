package id.magau.whizz.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorGenerator {

    public static ColorGenerator DEFAULT;

    public static ColorGenerator MATERIAL;

    public static ColorGenerator BASEAPP;

    public static ColorGenerator APP;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));

        APP = create(Arrays.asList(
                0xff0983e2,
                0xfffd8d64,
                0xff00b793,
                0xfff2bc59,
                0xff6c5ce6,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff8dda18,
                0xff5874ff,
                0xffa8269b,
                0xffaed581,
                0xffF25857
        ));

        MATERIAL = create(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae
        ));
        BASEAPP = create(Arrays.asList(
                0xff1360Fb,
                0xffff71e3,
                0xff00eef6,
                0xff8dda18,
                0xff5874ff,
                0xff102126,
                0xffa8269b,
                0xfffc4ccd,
                0xffff5858,
                0xff444444,
                0xff00844b,
                0xff53d7ff,
                0xff00C871,
                0xff53D7ff,
                0xffcccccc,
                0xff1c85a5,
                0xffe67817,
                0xff2ea1c4,
                0xff606060
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}


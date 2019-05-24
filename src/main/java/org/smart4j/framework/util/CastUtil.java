package org.smart4j.framework.util;


/**
 * 转型操作工具类
 */
public class CastUtil {


    /**
     * 转化为String
     *
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }


    /**
     * 转化为String，带有默认值
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }


    /**
     * 转化为double
     *
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }


    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String s = castString(obj);
            if (StringUtil.isNotEmpty(s)) {
                try {
                    doubleValue = Double.valueOf(s);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0L);
    }


    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String s = castString(obj);
            if (StringUtil.isNotEmpty(s)) {
                try {
                    longValue = Long.parseLong(s);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }


    /**
     * 转化为int型
     *
     * @param obj
     * @return
     */
    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String s = castString(obj);
            if (StringUtil.isNotEmpty(s)) {
                try {
                    intValue = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转化为boolean
     *
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;

    }

    /**
     * 转化为String[]
     *
     * @param objArray
     * @return
     */
    public static String[] castStringArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        String[] strArray = new String[objArray.length];
        if (ArrayUtil.isNotEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                strArray[i] = castString(objArray[i]);
            }
        }
        return strArray;
    }

    public static double[] castDoubleArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        double[] doubleArray = new double[objArray.length];
        if (ArrayUtil.isNotEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                doubleArray[i] = castDouble(objArray[i]);
            }
        }
        return doubleArray;
    }

    public static long[] castLongArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        long[] longArray = new long[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                longArray[i] = castLong(objArray[i]);
            }
        }
        return longArray;
    }


    public static int[] castIntArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                intArray[i] = castInt(objArray[i]);
            }
        }
        return intArray;
    }


    public static boolean[] castBooleanArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        boolean[] booleanArray = new boolean[objArray.length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                booleanArray[i] = castBoolean(objArray[i]);
            }
        }
        return booleanArray;
    }




}

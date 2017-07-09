package com.qianqi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import android.text.method.ReplacementTransformationMethod;

import com.qianqi.QqAppApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class CommonUtils {
    /**
     * @Description:2/3G网络IP地址
     * @Author 13050629
     * @Date 2014-7-14
     */
    private static String get3GIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    /**
     * 获取imei号
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-8-7
     */
    public static String getImei() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) QqAppApplication
                .getInstance().getSystemService(
                        QqAppApplication.getInstance().TELEPHONY_SERVICE);
        return mTelephonyMgr.getDeviceId();
    }


    /**
     * @Description:一维数组转字符串
     * @Author 13050629
     * @Date 2014-7-18
     */
    public static String getStringValue(Object[] t) {
        String value = "";
        StringBuilder sb = new StringBuilder();
        for (Object str : t) {
            sb.append(str + ",");
        }
        value = sb.toString().substring(0, sb.toString().length() - 1);
        return value;
    }

    /**
     * 数组转换成｜间隔的字符串
     *
     * @param args
     * @return
     */
    public static String array2String2(String[] args) {
        String value = "";
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str + ";");
        }
        if (args.length == 0) {
            return value;
        }
        value = sb.toString().substring(0, sb.toString().length() - 1);
        if (value == null || value.length() == 0)
            value = " ";
        return value;
    }

    /**
     * 数组转换成｜间隔的字符串
     *
     * @param args
     * @return
     */
    public static String array2String(String[] args) {
        String value = "";
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str + "|");
        }
        if (args.length == 0) {
            return value;
        }
        value = sb.toString().substring(0, sb.toString().length() - 1);
        if (value == null || value.length() == 0)
            value = " ";
        return value;
    }

    /**
     * 数组转换成;间隔的字符串
     *
     * @param args
     * @return
     */
    public static String array2StringColumn(String[] args) {
        String value = "";
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str + ";");
        }
        if (args.length == 0) {
            return value;
        }
        value = sb.toString().substring(0, sb.toString().length() - 1);
        if (value == null || value.length() == 0)
            value = " ";
        return value;
    }

    /**
     * 判断有无网络
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-4-16
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) QqAppApplication
                .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        NetworkInfo mobileInfo = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        State mobileState = State.SUSPENDED;
        if (mobileInfo != null) {
            mobileState = mobileInfo.getState();
            ;
        }
        if (wifiState == State.CONNECTED || mobileState == State.CONNECTED) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前系统时间
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-7-28
     */
    public static String getDateTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());

        return date;
    }

    /**
     * @author 15010551
     * @Data 2016-1-28
     */
    public static String getDateTime(String dateFormat) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormat);
        String date = sDateFormat.format(new java.util.Date());

        return date;
    }

    /**
     * 比较时间大小
     *
     * @return
     */
    public static boolean compareTime(String date) {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = df.parse(date);
            Date d2 = df.parse(getDateTime());
            if (d.getTime() < d2.getTime()) {
                // System.out.println("dt1 在dt2前");
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static String addZero(int data) {
        String dataStr;
        if (data < 10) {
            dataStr = "0" + data;
        } else {
            dataStr = String.valueOf(data);
        }

        return dataStr;
    }

    /**
     * 去除空格
     *
     * @Description:
     * @Author 13050629
     * @Date 2014-7-29
     */
    public static String removeAllSpace(String str) {
        String tmpstr = str.replace(" ", "");
        return tmpstr;
    }

    public static <T> T addZero(T value) {
        if ((value.toString()).length() > 0 && (value.toString()).length() <= 1) {
            value = (T) ("0" + value);
        }

        return value;
    }


    /**
     * 判断集合是否为空
     *
     * @param coll
     * @return
     */
    public static boolean isEmpty(Collection<? extends Object> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 判断是否純数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否数字+字母
     */
    public static boolean isNumericLettrt(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String leftPad(String str, int size) {
        String addStr = "00000000000000000000";
        String allStr = addStr + str;
        return allStr.substring(allStr.length() - size, allStr.length());

    }

    /**
     * 20位以上前面去0
     */

    public static String cleanZero(String str) {
        String newStr = str.replaceFirst("^0*", "");
        return newStr;
    }

    /**
     * 判断是否純数字 小数允许最大三位
     * <p>
     * ^\d+(.\d{1,3})?$
     */
    public static boolean isDecimalsNumeric(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,3})?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 。0 变整
     */
    public static String turnToint(String str) {
        if (str.endsWith(".0")) {
            return str.substring(0, str.length() - 2);
        } else if (str.endsWith(".00")) {
            return str.substring(0, str.length() - 3);
        } else if (str.endsWith(".000")) {
            return str.substring(0, str.length() - 4);
        } else {
            return str;
        }

    }

    /**
     * md5
     *
     * @param input
     * @return
     */
    public static String string2MD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            char[] resultCharArray = new char[resultByteArray.length * 2];
            int index = 0;
            for (byte b : resultByteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }
            return new String(resultCharArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class InputLowerToUpper extends
            ReplacementTransformationMethod {
        @Override
        protected char[] getOriginal() {
            char[] lower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'};
            return lower;
        }

        @Override
        protected char[] getReplacement() {
            char[] upper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                    'W', 'X', 'Y', 'Z'};
            return upper;
        }

    }

}

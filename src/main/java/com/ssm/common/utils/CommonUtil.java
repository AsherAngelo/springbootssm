package com.ssm.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static String listToString(Collection<String> stringCollection) {
        if (stringCollection == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringCollection) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static String listToString(Collection<String> stringCollection, String pattern) {
        if (stringCollection == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringCollection) {
            if (flag) {
                result.append(pattern);
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * 提取字符串中的数字
     *
     * @param str
     * @return
     */
    public static int stringToInt(String str) {
        str = str.trim();
        StringBuffer sb = new StringBuffer();
        if (!CommonUtil.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    sb.append(str.charAt(i));
                }
            }
            String str2 = sb.toString();
            return Integer.parseInt("".equals(str2) ? "0" : str2);
        }
        return 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isEmpty(str);
    }

    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static List<String> arrayToList(String[] strs) {
        List<String> strList = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            strList.add(strs[i]);
        }
        return strList;
    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            logger.error("", e);
        }
        return gc;
    }

    public static Date convertToDate(XMLGregorianCalendar cal) {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    //读取配置文件
    public static String getConfig(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("config/config");
        return rb.getString(key);
    }

    public static Integer nullToZero(Integer num) {
        if (null == num) {
            return 0;
        }
        return num;
    }

    public static String nullToBlank(String str) {
        if (null == str) {
            return "";
        }
        return str;
    }

    public static Integer objToInteger(Object obj) {
        if (null == obj) {
            return 0;
        }
        return (Integer) obj;
    }

    public static List<String> stringToList(String strList) {
        List<String> list = new ArrayList<String>();
        for (String str : strList.split(",")) {
            list.add(str);
        }
        return list;
    }

    public static List<String> stringToList(String... strList) {
        List<String> list = new ArrayList<String>();
        if (strList == null || strList.length == 0) {
            return list;
        }
        for (String str : strList) {
            list.add(str);
        }
        return list;
    }

    public static Long stringToLong(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return Long.parseLong(str);
    }

    public static Float stringToFloat(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return Float.parseFloat(str);
    }

    public static boolean isNotSpecialChar(String str) {
//    	 String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
        String regEx = "[\u4e00-\u9fa5_0-9a-zA-z]{1,}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isDigitAndAlphabet(String str) {
        String regEx = "[0-9a-zA-z]{1,}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isDigit(String str) {
        //整数
        String regEx = "[0-9]{1,}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        //浮点数
        String floatEx = "^\\d+(\\.\\d+)?$";
        Pattern patternfl = Pattern.compile(floatEx);
        Matcher matcherlf = patternfl.matcher(str);

        return matcher.matches() || matcherlf.matches();
    }

    //删除String两边的第一个中括号
    public static String getMessage(String str) {
        int start = str.indexOf("[");
        int end = -1;
        int index = -1;
        while ((index = str.indexOf("]", end + 1)) > 0) {
            end = index;
        }
        return start < 0 || end < 0 || start > end ? str : str.substring(start + 1, end);
    }


    /**
     * 将异常信息转化成字符串
     *
     * @param t
     * @return
     * @throws IOException
     */
    public static String printException(Throwable t) throws IOException {
        if (t == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            t.printStackTrace(new PrintStream(baos));
        } finally {
            baos.close();
        }
        return baos.toString();
    }

    public static String convertString(String str) {
        return isEmpty(str) ? "" : str;
    }

    public static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") >= 0) return "IE";
            if (UserAgent.indexOf("firefox") >= 0) return "FF";
            if (UserAgent.indexOf("safari") >= 0) return "SF";
        }
        return null;
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号 
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }


    public static boolean pingIp(String ip) {
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

            ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", ip);
            Process proc = processBuilder.start();

            int returnVal = proc.waitFor();
            return returnVal == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean doPingCmd(String ipAddress) {
        return doPingCmd(ipAddress, "10");
    }

    public static boolean doPingCmd(String ipAddress, String timeout) {
        LineNumberReader lnr = null;
        try {
            Process process = Runtime.getRuntime().exec("ping " + ipAddress + " -w " + timeout);
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            lnr = new LineNumberReader(isr);
            String line = null;
            while ((line = lnr.readLine()) != null) {
                if (!line.isEmpty() && line.contains("ttl")) {
                    return true;
                }
            }
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (lnr != null) {
                try {
                    lnr.close();
                } catch (IOException e) {
                    logger.error("", e);
                    logger.info("", "close error");
                }
            }
        }
        return false;
    }

    public static boolean binarySearch(List<Integer> list, Integer target) {
        if (isCollectionEmpty(list)) return false;
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {

            int mid = start + (end - start) / 2;
            int val = list.get(mid);
            if (val == target) return true;
            if (val > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}

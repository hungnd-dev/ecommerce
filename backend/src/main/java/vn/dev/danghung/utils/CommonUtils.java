package vn.dev.danghung.utils;

import com.sun.istack.internal.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


public class CommonUtils {

    /**
     * get local host address
     *
     * @return local host address
     * @throws UnknownHostException
     */
    public static String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * generate random number between min and max
     *
     * @param min min value
     * @param max max value
     * @return random number between min and max
     */
    public static int generateRandomNum(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static long convertIpv4ToLong(String ipV4) throws Exception {
        long ip = 0;
        if (ipV4 == null || ipV4.isEmpty()) throw new Exception("Invalid ip " + ipV4);
        String[] octets = ipV4.split(Pattern.quote("."));
        if (octets.length != 4) throw new Exception("Invalid ip " + ipV4);
        for (int i = 3; i >= 0; i--) {
            long octet = Long.parseLong(octets[3 - i]);
            if (octet > 255 || octet < 0) throw new Exception("Invalid ip " + ipV4);
            ip |= octet << (i * 8);
        }

        return ip;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String longToHex(long input) {
        String hexString = Long.toHexString(input);

        StringBuilder sb = new StringBuilder();

        for (int i = hexString.length(); i < 16; i++) {
            sb.append("0");
        }

        sb.append(hexString);

        return sb.toString();
    }

    public static String intToHex(int input) {
        String hexString = Integer.toHexString(input);

        StringBuilder sb = new StringBuilder();

        for (int i = hexString.length(); i < 8; i++) {
            sb.append("0");
        }

        sb.append(hexString);

        return sb.toString();
    }

    public static String md5(@NotNull String input) throws Exception {
        if (input == null) throw new NullPointerException();
        String result;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] digest = md5.digest(inputBytes);
        StringBuilder sb = new StringBuilder();
        for (byte d : digest) {
            sb.append(Integer.toHexString((d & 0xFF) | 0x100).substring(1, 3));
        }
        result = sb.toString();

        return result;
    }

    public static long crc32(String input) {
        // get bytes from string
        byte bytes[] = input.getBytes(StandardCharsets.UTF_8);

        Checksum checksum = new CRC32();

        // update the current checksum with the specified array of bytes
        checksum.update(bytes, 0, bytes.length);

        // get the current checksum value
        return checksum.getValue();
    }

    public static Timestamp parseTime(long timeInMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeInMillis);
        String t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(c.getTime());
        return Timestamp.valueOf(t);
    }

    public static String hmac256(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");

        mac.init(signingKey);

        byte[] digest = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(digest);
    }

    public static String hmac512(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");

        Mac mac = Mac.getInstance("HmacSHA512");

        mac.init(signingKey);

        byte[] digest = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(digest);
    }

    public static synchronized String validateNull(String source) {
        if (source == null) return "";
        return source.trim();
    }

    public static boolean checkEmpty(String s){
        return s == null || s.isEmpty() || s == "";
    }
}

package me.dion.mygriboedov.core.server;

import static android.content.Context.WIFI_SERVICE;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import me.dion.mygriboedov.core.server.exception.NoInternetConnectionException;

public class GameIDGenerator {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String ipEncrypt(Context context) throws NoInternetConnectionException {
        try {
            String[] code = getLocalIP(context).split("\\.");
            return code[2] + code[3];
        } catch (Exception e) {
            throw new NoInternetConnectionException("Нет подключения к интернету!");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String getLocalIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            ipAddressString = null;
        }

        return ipAddressString;
    }

    public static String ipDecrypt(String code) {
        return "192.168." + code.substring(0, 0) + "." + code.substring(1);
    }
}

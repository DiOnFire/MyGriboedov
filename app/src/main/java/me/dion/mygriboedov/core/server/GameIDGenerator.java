package me.dion.mygriboedov.core.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import me.dion.mygriboedov.core.server.exception.NoInternetConnectionException;

public class GameIDGenerator {
    private static String ipEncrypt() throws Exception {
        InetAddress address = getLocalIP();
        if (address != null) {
            String[] string_address = address.toString().split(".");
            return string_address[3] + "." + string_address[4];
        } else {
            throw new NoInternetConnectionException("No internet connection!");
        }
    }

    private static InetAddress getLocalIP() throws Exception {
        List<NetworkInterface> netInts = Collections.list(NetworkInterface.getNetworkInterfaces());

        if (netInts.size() == 1) {
            return InetAddress.getLocalHost();
        }

        for (NetworkInterface net : netInts) {
            if (!net.isLoopback() && !net.isVirtual() && net.isUp()) {
                Enumeration<InetAddress> addrEnum = net.getInetAddresses();
                while (addrEnum.hasMoreElements()) {
                    InetAddress addr = addrEnum.nextElement();
                    if (!addr.isLoopbackAddress() && !addr.isAnyLocalAddress() && !addr.isLinkLocalAddress() && !addr.isMulticastAddress()) return addr;
                }
            }
        }
        return null;
    }
}

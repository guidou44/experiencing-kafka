package com.kafkatester.domain.messages;

import java.net.InetAddress;

public class NetworkScanMessage {

    public int PartitionId;
    public long PingTimeMilli;
    public String IpAddress;
    public String HostName;
    public boolean Reachable;

    public NetworkScanMessage() {
    }

    public NetworkScanMessage(String ipAddress, String hostName, long pingTimeMilli, boolean reachable) {
        this.PingTimeMilli = pingTimeMilli;
        this.IpAddress = ipAddress;
        this.HostName = hostName;
        this.Reachable = reachable;
    }
}

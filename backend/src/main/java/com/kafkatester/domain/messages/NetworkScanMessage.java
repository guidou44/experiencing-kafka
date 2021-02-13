package com.kafkatester.domain.messages;

import java.net.InetAddress;

public class NetworkScanMessage {

    public int PartitionId;
    public long PingTimeMilli;
    public InetAddress IpAddress;
    public boolean Reachable;

    public NetworkScanMessage(InetAddress ipAddress, long pingTimeMilli, boolean reachable) {
        this.PingTimeMilli = pingTimeMilli;
        this.IpAddress = ipAddress;
        this.Reachable = reachable;
    }
}

package com.networkscanner.domain.messages;

public class NetworkScanMessage {

    public int partitionId;
    public long pingTimeMilli;
    public String ipAddress;
    public String hostName;
    public boolean reachable;

    public NetworkScanMessage() {
    }

    public NetworkScanMessage(String ipAddress, String hostName, long pingTimeMilli, boolean reachable) {
        this.pingTimeMilli = pingTimeMilli;
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.reachable = reachable;
    }
}

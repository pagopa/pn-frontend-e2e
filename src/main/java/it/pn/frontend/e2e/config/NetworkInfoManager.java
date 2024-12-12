package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.listeners.NetWorkInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfoManager {



    private static final ThreadLocal<List<NetWorkInfo>> networkInfoThread = ThreadLocal.withInitial(ArrayList::new);

    public static List<NetWorkInfo> getNetworkInfo() {
        return networkInfoThread.get();
    }

    public static void addNetworkInfo(NetWorkInfo info) {
        networkInfoThread.get().add(info);
    }

    public static void clearNetworkInfos() {
        networkInfoThread.remove();
    }

}
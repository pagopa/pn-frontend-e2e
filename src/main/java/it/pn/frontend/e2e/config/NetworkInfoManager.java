package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.listeners.NetWorkInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfoManager {
    private static ThreadLocal<List<NetWorkInfo>> networkInfo = ThreadLocal.withInitial(ArrayList::new);

    public static List<NetWorkInfo> getNetworkInfo() {
        return networkInfo.get();
    }

    public static void clearNetworkInfo() {
        networkInfo.remove();
    }
}
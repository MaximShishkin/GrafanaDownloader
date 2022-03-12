package ru.shishkin.grafanaDownloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafanaDownloader {

    private final static Map<String, List<String>> args = new HashMap<>();

    public static void main(String[] arg) {
        try {
            readParams(arg);
            Props p = new Props(args.get("config").get(0), args.get("out").get(0));
            Grafana g = new Grafana(p, args.get("start").get(0), args.get("finish").get(0));
            g.downloadAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void readParams(String[] arg) {
        List<String> options = null;
        for (int i = 0; i < arg.length; i++) {
            final String a = arg[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }
                options = new ArrayList<>();
                args.put(a.substring(1), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }
        System.out.println("Input:");
        System.out.println("Start=" + args.get("start").get(0));
        System.out.println("Finish=" + args.get("finish").get(0));
        System.out.println("Config=" + args.get("config").get(0));
        System.out.println("Out=" + args.get("out").get(0));
    }
}

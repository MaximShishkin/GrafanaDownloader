package ru.shishkin.grafanaDownloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Props {
    private Properties prop;
    public String outFolder;
    public Map<String, String> params = new TreeMap<>();
    public ArrayList<Map<String, String>> metrics = new ArrayList<>();

    public Props(String config, String out) {
        this.outFolder = out;
        try {
            InputStream input = new FileInputStream(config);
            prop = new Properties();
            prop.load(input);
            params.put("grafana.host", prop.getProperty("grafana.host"));
            params.put("grafana.token", prop.getProperty("grafana.token"));
            params.put("panels.count", prop.getProperty("panels.count"));
            params.put("panels.height", prop.getProperty("panels.height"));
            params.put("panels.width", prop.getProperty("panels.width"));
            params.put("java.timeMask", prop.getProperty("java.timeMask"));

            System.out.println("\r\nConfig:");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
                System.out.println(key + "=" + val);
            }
            int metriCount = Integer.parseInt(params.get("panels.count"));

            for (int i = 1; i < metriCount + 1; i++) {
                Map<String, String> tempM = new HashMap<>();
                tempM.put("dashboardName", prop.getProperty("metric" + i + ".dashboardName"));
                tempM.put("output", prop.getProperty("metric" + i + ".output"));
                metrics.add(tempM);
            }
            System.out.println("\r\nMetrics:");
            int outC = 1;
            for (Map<String, String> m : metrics) {
                for (Map.Entry<String, String> entry : m.entrySet()) {
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    System.out.println("metric" + outC + "." + key + "=" + val.toString());
                }
                System.out.print("\r\n");
                outC++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Props.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

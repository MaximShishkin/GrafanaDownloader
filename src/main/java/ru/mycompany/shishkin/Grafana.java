package ru.mycompany.shishkin;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;

public class Grafana {
    private Props conf;
    private String start;
    private String finish;

    public Grafana(Props conf, String start, String finish) {
        this.conf = conf;
        this.start = Utils.dateIntoTimestamp(start, conf.params.get("java.timeMask"));
        this.finish = Utils.dateIntoTimestamp(finish, conf.params.get("java.timeMask"));
    }

    public void downloadAll() {
        System.out.println("\r\nDownloading: ");
        String host = conf.params.get("grafana.host");
        String width = conf.params.get("panels.width");
        String height = conf.params.get("panels.height");

        String urls;
        HttpURLConnection conn;

        try {
            for (int i = 1; i < conf.metrics.size() + 1; i++) {
                urls = Utils.urlBuilder(
                        host,
                        conf.metrics.get(i - 1).get("dashboardName"),
                        start,
                        finish,
                        width,
                        height);
                System.out.println("Link" + i + "=" + urls);
                URL url = new URL(urls);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + conf.params.get("grafana.token"));

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedImage image = ImageIO.read(conn.getInputStream());
                String outImage = conf.outFolder + "" + conf.metrics.get(i - 1).get("output");
                ImageIO.write(image, "png", new File(outImage));
                System.out.println("Succesfull for: " + outImage);
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

# GrafanaDownloader

Download rendered panels from Grafana for given time interval

Java 11 with Grafana Image Render Plugins

### Start example

java -jar GrafanaDownloader.jar -start 2020-02-20T20:00 -finish 2020-02-20T23:00 -config /user/config.txt -out /user/photo/

###Arguments

**-start** Start of time interval

**-finish** End of time interval

**-config** Path to config file

**-out** Subfolder name for downloaded images

###Config example
```
#Connection props
grafana.host=https://adminnt.sberchat.sberbank.ru
grafana.token=aaabbbccc_grafanatoken_cccbbbaaa

#Metric props
panels.count=3
panels.height=500
panels.width=1000

#Env props
java.timeMask=yyyy-MM-dd'T'HH:mm

#Metric1 props
metric1.dashboardName=/grafana/render/d-solo/dialog_server/dialogserver?orgId=1&panelId=32&tz=Europe%2FMoscow
metric1.output=RPC.jpg

#Metric2 props
metric2.dashboardName=/grafana/render/d-solo/dialog_server/dialogserver?orgId=1&panelId=6&tz=Europe%2FMoscow
metric2.output=SendMessageRate.jpg

#Metric3 props
metric3.dashboardName=/grafana/render/d-solo/dialog_server/dialogserver?orgId=1&panelId=40&tz=Europe%2FMoscow
metric3.output=ResponseTime.jpg
```

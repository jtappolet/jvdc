package io.fennix.jvdc.announce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

public class ServiceAnnouncer {

    private static final Logger log = LoggerFactory.getLogger(ServiceAnnouncer.class);

    ServiceInfo dsVdc;
    private static ServiceAnnouncer instance;

    public static ServiceAnnouncer instance(){
        if(instance == null)
            instance = new ServiceAnnouncer();
        return instance;
    }

    private ServiceAnnouncer(){
    }

    public void registerService(int port){
        dsVdc = ServiceInfo.create(
                "_ds-vdc._tcp.local.",
                "jvdc host", port, "");
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            jmdns.registerService(dsVdc);
            log.info("Service registered: "+dsVdc.toString());
        } catch (IOException e) {
            log.error("Unable to announce vdc.");
        }
    }

}

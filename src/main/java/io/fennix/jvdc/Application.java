package io.fennix.jvdc;

import com.blade.Blade;
import com.blade.event.EventType;
import io.fennix.jvdc.announce.ServiceAnnouncer;
import io.fennix.jvdc.socket.VdcRpcServer;

public class Application {

    public static void main(String[] args) {

        Blade.of()
                .bannerText(
            "__________________________________/\\\\\\________________        \n" +
            " _______/\\\\\\______________________\\/\\\\\\________________       \n" +
            "  ______\\///_______________________\\/\\\\\\________________      \n" +
            "   _______/\\\\\\__/\\\\\\____/\\\\\\________\\/\\\\\\______/\\\\\\\\\\\\\\\\_     \n" +
            "    ______\\/\\\\\\_\\//\\\\\\__/\\\\\\____/\\\\\\\\\\\\\\\\\\____/\\\\\\//////__    \n" +
            "     ______\\/\\\\\\__\\//\\\\\\/\\\\\\____/\\\\\\////\\\\\\___/\\\\\\_________   \n" +
            "      __/\\\\_\\/\\\\\\___\\//\\\\\\\\\\____\\/\\\\\\__\\/\\\\\\__\\//\\\\\\________  \n" +
            "       _\\//\\\\\\\\\\\\_____\\//\\\\\\_____\\//\\\\\\\\\\\\\\/\\\\__\\///\\\\\\\\\\\\\\\\_ \n" +
            "        __\\//////_______\\///_______\\///////\\//_____\\////////__\n")
                .on(EventType.SERVER_STARTED, e -> {
                    VdcRpcServer.instance().openSocket(9001);
                    ServiceAnnouncer.instance().registerService(9001);
                })
                .get("/basic-routes-example", ctx -> ctx.text("GET called"))
                .post("/basic-routes-example", ctx -> ctx.text("POST called"))
                .put("/basic-routes-example", ctx -> ctx.text("PUT called"))
                .delete("/basic-routes-example", ctx -> ctx.text("DELETE called"))
                .start(Application.class, args);

    }

}

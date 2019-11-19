package io.fennix.jvdc.socket;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class VdcRpcServer {
    private static final Logger log = LoggerFactory.getLogger(VdcRpcServer.class);

    private static VdcRpcServer instance;

    public static VdcRpcServer instance(){
        if (instance == null)
            instance = new VdcRpcServer();
        return instance;
    }

    private VdcRpcServer(){}

    private Server server;

    public void openSocket(int port) {
        /* The port on which the server should run */
        try {
            server = NettyServerBuilder.forPort(port)
                    .addService(new VdcApiService())
                    .protocolNegotiator()
                    .build()
                    .start();
            log.info("Server started, listening on " + port);

        } catch (IOException e) {
            log.error("Failed to start server on port "+ port, e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            VdcRpcServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}

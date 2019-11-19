package io.fennix.jvdc.socket;


import io.fennix.jvdc.proto.VdsmServiceGrpc;
import io.fennix.jvdc.proto.vdc_ResponseHello;
import io.fennix.jvdc.proto.vdsm_RequestHello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VdcApiService extends VdsmServiceGrpc.VdsmServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(VdcApiService.class);


    @Override
    public void hello(vdsm_RequestHello request,
                      io.grpc.stub.StreamObserver<vdc_ResponseHello> responseObserver) {
        int apiVersion = request.getApiVersion();
        String dss = request.getDSUID();
        log.info("dSS said hello with dSUID %s and API version %s", dss, apiVersion);


        vdc_ResponseHello response = vdc_ResponseHello.newBuilder()
                .setDSUID("")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

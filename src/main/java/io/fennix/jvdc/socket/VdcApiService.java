package io.fennix.jvdc.socket;


import io.fennix.jvdc.proto.VdsmServiceGrpc;
import io.fennix.jvdc.proto.vdc_ResponseHello;
import io.fennix.jvdc.proto.vdsm_RequestHello;


public class VdcApiService extends VdsmServiceGrpc.VdsmServiceImplBase {
    @Override
    public void hello(vdsm_RequestHello request,
                      io.grpc.stub.StreamObserver<vdc_ResponseHello> responseObserver) {
        int apiVersion = request.getApiVersion();
        String dss = request.getDSUID();


        vdc_ResponseHello response = vdc_ResponseHello.newBuilder()
                .setDSUID("")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

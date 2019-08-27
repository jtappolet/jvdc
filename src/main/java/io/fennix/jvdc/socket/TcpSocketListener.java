package io.fennix.jvdc.socket;

import io.fennix.jvdc.announce.ServiceAnnouncer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class TcpSocketListener {
    private static final Logger log = LoggerFactory.getLogger(TcpSocketListener.class);

    private static TcpSocketListener instance;

    public static TcpSocketListener instance(){
        if (instance == null)
            instance = new TcpSocketListener();
        return instance;
    }

    private TcpSocketListener(){}

    public void openSocket(int port){
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        acceptor.getFilterChain().addLast("codec",  new ProtocolCodecFilter(new DemuxingProtocolCodecFactory()));
        acceptor.setHandler(new SocketCommunicationHandler());
        try {
            acceptor.bind( new InetSocketAddress(port) );
        } catch (IOException e) {
            log.error("Cannot open TCP listener on port "+ port, e);
        }
    }
}

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class Server {
    private static final int PORT = 9000;

    public static void main(String[] args) {
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HTTPInitializer());

            //Channel ch = b.bind(PORT).sync().channel();

           // Channel ch = b.bind(new InetSocketAddress("127.0.0.1", PORT)).sync().channel();
            //TODO ip - пк где сервак
           // Channel ch = b.bind(new InetSocketAddress("192.168.1.68", PORT)).sync().channel();
            Channel ch;

           // if(false)
            //    ch = b.bind(new InetSocketAddress("91.76.182.215", PORT)).sync().channel();
           // else
//                ch = b.bind(new InetSocketAddress("192.168.1.68", PORT)).sync().channel();
            ch = b.bind(new InetSocketAddress("192.168.43.212", PORT)).sync().channel();

            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
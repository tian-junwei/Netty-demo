package com.tianjunwei.netty.chat.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tianjunwei
 * @date 2019/1/30 14:23
 */
public class ChatClient {
    private String host;
    private int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    //.remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChatClientInitializer());

            Channel channel = b.connect(host,port).sync().channel();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                channel.writeAndFlush(input.readLine() + "\n");
            }
            //ChannelFuture f = b.connect().sync();
            //f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ChatClient("localhost",8888).start();
    }
}
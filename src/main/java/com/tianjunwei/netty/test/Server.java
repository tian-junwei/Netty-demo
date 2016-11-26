package com.tianjunwei.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {

	private int port;
	public Server(int port){
		this.port = port;
	}
	
	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(2);
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
					.localAddress(port).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							 ch.pipeline().addLast("decoder", new StringDecoder());  
	                            ch.pipeline().addLast("encoder", new StringEncoder());  
	                            ch.pipeline().addLast(new ServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128)     
	                .childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture future = serverBootstrap.bind(8080).sync();
	        System.out.println("Server start listen at " + port );  
	        future.channel().closeFuture().sync();  
		} catch (Exception e) {
			 bossGroup.shutdownGracefully();  
	         workerGroup.shutdownGracefully();
		}
	}
	public static void main(String[] args) throws Exception {  
        int port;  
        if (args.length > 0) {  
            port = Integer.parseInt(args[0]);  
        } else {  
            port = 8080;  
        }  
        new Server(port).start();  
    }  
}

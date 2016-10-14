
package com.tianjunwei.netty.codec.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import com.tianjunwei.netty.codec.protobuf.SubscribeReqProto.SubscribeReq;


public class SubReqClientHandler extends ChannelHandlerAdapter {

    /**
     * Creates a client-side handler.
     */
    public SubReqClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) {
	for (int i = 0; i < 10; i++) {
	    ctx.write(subReq(i));
	}
	ctx.flush();
    }

    private SubscribeReq subReq(int i) {
	SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
		.newBuilder();
	builder.setSubReqID(i);
	builder.setUserName("Lilinfeng");
	builder.setProductName("Netty Book For Protobuf");
	List<String> address = new ArrayList<>();
	address.add("NanJing YuHuaTai");
	address.add("BeiJing LiuLiChang");
	address.add("ShenZhen HongShuLin");
	builder.addAllAddress(address);
	return builder.build();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	System.out.println("Receive server response : [" + msg + "]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}

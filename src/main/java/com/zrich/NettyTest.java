package com.zrich;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2017/7/3.
 */
public class NettyTest {


    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new ECHO());
                        }
                    }).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = boot.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            return;
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class ECHO extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.writeAndFlush(msg);
//            System.out.println(String.valueOf(msg));
        }
    }
}

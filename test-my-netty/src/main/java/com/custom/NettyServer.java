package com.custom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServer {

    public static void main(String[] arg){

        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
//            bootstrap.handler(new ChannelInitializer<SocketChannel>(){
//                @Override
//                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
//                    ch.pipeline().addLast(new NettyServerHandler());
//                }
//            });
            System.out.println("netty server start");
            ChannelFuture cf = bootstrap.bind(9000).sync();

            cf.channel().closeFuture().sync();

        }catch(Exception e){

        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

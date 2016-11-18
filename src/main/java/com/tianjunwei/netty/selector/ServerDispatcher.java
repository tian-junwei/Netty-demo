package com.tianjunwei.netty.selector;

import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import org.apache.log4j.Logger;

public class ServerDispatcher{
	private ServerSocketChannel ssc;
	private Selector[] selectors = new Selector[3];

	private SelectorProvider selectorProvider;
	
	private final static Logger logger = Logger.getLogger(ServerDispatcher.class);
	public ServerDispatcher(ServerSocketChannel ssc, SelectorProvider selectorProvider) throws IOException {
		this.ssc = ssc;
		this.selectorProvider = selectorProvider;
		for (int i = 0; i < 3; i++)
		{
			selectors[i] = selectorProvider.openSelector();
		}
	}
	
	public Selector getAcceptSelector()
	{
		return selectors[0];
	}
	
	public Selector getReadSelector()
	{
		return selectors[1];
	}
	
	public Selector getWriteSelector()
	{
		return selectors[1];
	}
	
	public void execute() throws IOException
	{
		SocketHandler r = new SocketAcceptHandler(this, ssc, getAcceptSelector());
		new Thread(r).start();
		
		r = new SocketReadHandler(this, ssc, getReadSelector());
		new Thread(r).start();

		r = new SocketWriteHandler(this, ssc, getWriteSelector());
		new Thread(r).start();
	}
	
	
}
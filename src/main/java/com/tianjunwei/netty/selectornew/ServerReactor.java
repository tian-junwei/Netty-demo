/**
 *    Copyright  2016 tianjunwei
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.tianjunwei.netty.selectornew;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author tianjunwei
 * @time 2016下午9:42:52
 */
public class ServerReactor implements Runnable {
	
	private SelectorProvider selectorProvider = SelectorProvider.provider();  
	private Selector selector;
	ServerSocketChannel serverSocketChannel;
	
	public ServerReactor(ServerSocketChannel serverSocketChannel){
		try {
			selector = selectorProvider.openSelector();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.serverSocketChannel = serverSocketChannel;
		
	}
	
	@Override
	public void run() {
		try {
			serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT, this);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}

}

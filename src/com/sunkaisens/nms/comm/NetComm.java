package com.sunkaisens.nms.comm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class NetComm {

	protected Log log = LogFactory.getLog(getClass());
	private static DatagramSocket socket;
	
	public NetComm(int localPort,String localIP){
		try {
			socket = new DatagramSocket(localPort,InetAddress.getByName(localIP));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(byte[] pak, String remoteIP, int remotePort) {

		try {
			socket.send(new DatagramPacket(pak, pak.length, InetAddress
						.getByName(remoteIP), remotePort));
			log.debug("发送到["+remotePort+":"+remotePort+"]");
			log.debug(HexDump.dump(pak, 0, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		NetComm.socket = socket;
	}
}

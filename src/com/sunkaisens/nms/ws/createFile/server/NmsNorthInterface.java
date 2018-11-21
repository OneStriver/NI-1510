package com.sunkaisens.nms.ws.createFile.server;

import javax.jws.WebService;

@WebService
public interface NmsNorthInterface {

	public boolean perfQueryMethod(String xml);

	public boolean queryDeviceStatusMethod(String xml);

	public boolean queryTerminalInfoConfigMethod(String xml);

	public boolean terminalInfoUpdateNotifyMethod(String xml);

	public boolean terminalInfoUpdateResultMethod(String xml);

}

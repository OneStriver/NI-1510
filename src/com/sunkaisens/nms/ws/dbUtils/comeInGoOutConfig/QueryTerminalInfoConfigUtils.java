package com.sunkaisens.nms.ws.dbUtils.comeInGoOutConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.entity.TerminalInfoConfigFileEntity;
import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.util.StackUtil;

public class QueryTerminalInfoConfigUtils {

	private static Logger logger = Logger.getLogger(QueryTerminalInfoConfigUtils.class);
	private static String reserveBitStr = ConfigurationManager.getInstance().getReserveNumberBit();

	public static List<TerminalInfoConfigFileEntity> getTerminalInfos() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TerminalInfoConfigFileEntity> lists = new ArrayList<TerminalInfoConfigFileEntity>();
		String sql = "select h.imsi,h.mdn,h.msprofile,d.name from hss.HLR h join myomc.hssdevicetype d on h.DeviceType=d.id";
		connection = ComeInGoOutConfigDbUtil.getTerminalInfoConfigConnection();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TerminalInfoConfigFileEntity terminalInfo = new TerminalInfoConfigFileEntity();
				//终端类型
				String terminalType = rs.getString("name");
				terminalInfo.setDeviceType(terminalType);
				//终端号码
				String terminalNumber = rs.getString("mdn");
				Integer terminalNumberReserveBit = Integer.valueOf(reserveBitStr);
				Integer countBit = terminalNumber.length()-terminalNumberReserveBit;
				if(countBit>0){
					terminalInfo.setDeviceNumber(terminalNumber.substring(countBit));
				}else{
					terminalInfo.setDeviceNumber(terminalNumber);
				}
				//终端配置类型(可选)
				terminalInfo.setType("add");
				// IMSI
				String imsi = rs.getString("imsi");
				terminalInfo.setImsi(imsi);
				//Priority 出局呼叫权
				String profileStr = rs.getString("msprofile");
				Integer convertInt = Integer.valueOf(profileStr);
				//计算优先级的值
				int priority = ((convertInt & 0xF0000000))>>>28;
				terminalInfo.setPriority(String.valueOf(priority));
				//出局呼叫权(也就是国际业务)
				int internationality = ((convertInt & 0x00080000)<<12)>>>31;
				terminalInfo.setPermitCallOut(String.valueOf(internationality));
				//循环将查询的用户添加到集合中
				lists.add(terminalInfo);
			}
			logger.info("获取到1510-OMC终端用户数量:" + lists.size());
			return lists;
		} catch (SQLException e) {
			logger.error(StackUtil.getTrace(e));
		} finally {
			ComeInGoOutConfigDbUtil.free(rs, ps, connection);
		}
		return null;
	}
	
}

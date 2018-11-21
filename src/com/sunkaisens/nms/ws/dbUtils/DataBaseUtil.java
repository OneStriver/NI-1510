package com.sunkaisens.nms.ws.dbUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sunkaisens.nms.ws.dbUtils.alarmDeviceStatus.AlarmDeviceStatusDbUtil;
import com.sunkaisens.nms.ws.dbUtils.dbResponseDeviceStatusEntity.DeviceStatusQuery;
import com.sunkaisens.nms.ws.dbUtils.dbResponsePerfInfoEntity.DbQueryPerformanceInfo;
import com.sunkaisens.nms.ws.dbUtils.performanceInfo.PerformanceInfoDbUtil;

public class DataBaseUtil {

	private static Logger logger = Logger.getLogger(DataBaseUtil.class);
	//设备状态查询集合
	private static List<DeviceStatusQuery> list = new ArrayList<DeviceStatusQuery>();
	
	public static void createCorrespondTable() {
		try {
			logger.info(">>>>>>>>开始创建数据库和表>>>>>>>>");
			File file = new File("confs/sql/dbTable.sql");
			FileInputStream is = new FileInputStream(file);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Connection connection = AlarmDeviceStatusDbUtil.getInitConnection();
			IOUtils.copy(is, os);
			String[] sqls = os.toString().split(";");
			for (String sql : sqls) {
				connection.createStatement().execute(sql);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//故障上报中 告警码对应该告警的信息
	public static AlarmCorrespond selectByAlarmCode(int alarm_code) {
		AlarmCorrespond alarmCorrespond = new AlarmCorrespond();
		String sql = "select alarm_type,specific_problem,alarm_des from northInterface.alarmCorrespond where alarm_code=? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = AlarmDeviceStatusDbUtil.getNorthIntefaceConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, alarm_code);
			rs = ps.executeQuery();
			while (rs.next()) {
				alarmCorrespond.setAlarm_type(rs.getString("alarm_type"));
				alarmCorrespond.setSpecific_problem(rs.getString("specific_problem"));
				alarmCorrespond.setAlarm_des(rs.getString("alarm_des"));
				return alarmCorrespond;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}
	
	//写入设备状态对应信息
	public static void insertDeviceStatusInfo(String deviceType,String deviceIndex,String deviceStatus) {
		list.clear();
		String sql = "insert into northInterface.deviceStatusInformation(deviceType,deviceIndex,deviceStatus) values(?,?,?) ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = AlarmDeviceStatusDbUtil.getNorthIntefaceConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deviceType);
			ps.setString(2, deviceIndex);
			ps.setString(3, deviceStatus);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
	}
	
	//更新设备状态信息
	public static void updateDeviceStatusInfo(String deviceType,String deviceIndex,String deviceStatus) {
		list.clear();
		String sql = "update northInterface.deviceStatusInformation set deviceIndex=?,deviceStatus=? where deviceType=? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = AlarmDeviceStatusDbUtil.getNorthIntefaceConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deviceIndex);
			ps.setString(2, deviceStatus);
			ps.setString(3, deviceType);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
	}
	
	//通过设备类型获取某个设备的信息
	public static DeviceStatusQuery selectDeviceStatusInfoByDeviceType(String deviceType) {
		DeviceStatusQuery deviceStatusQuery = new DeviceStatusQuery();
		String sql = "select deviceType,deviceIndex,deviceStatus from northInterface.deviceStatusInformation where deviceType=? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = AlarmDeviceStatusDbUtil.getNorthIntefaceConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deviceType);
			rs = ps.executeQuery();
			while (rs.next()) {
				deviceStatusQuery.setDeviceType(rs.getString("deviceType"));;
				deviceStatusQuery.setDeviceIndex(rs.getString("deviceIndex"));;
				deviceStatusQuery.setDeviceStatus(rs.getString("deviceStatus"));;
				return deviceStatusQuery;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}
	
	//获取所有设备的信息
	public static List<DeviceStatusQuery> selectAllDeviceStatusInfo() {
		list.clear();
		DeviceStatusQuery deviceStatusQuery = new DeviceStatusQuery();
		String sql = "select deviceType,deviceIndex,deviceStatus from northInterface.deviceStatusInformation ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = AlarmDeviceStatusDbUtil.getNorthIntefaceConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				deviceStatusQuery.setDeviceType(rs.getString("deviceType"));;
				deviceStatusQuery.setDeviceIndex(rs.getString("deviceIndex"));;
				deviceStatusQuery.setDeviceStatus(rs.getString("deviceStatus"));;
				list.add(deviceStatusQuery);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}
	
	//获取性能数据的信息
	public static List<DbQueryPerformanceInfo> selectDbPerformanceInfo(Integer channelStatus){
		List<DbQueryPerformanceInfo> perfInfoList = new ArrayList<DbQueryPerformanceInfo>();
		String sql = "select bts_id,channel_status,count(channel_status) as channelCount from nms.bts_channel_resource where channel_status=? group by bts_id ";
		System.err.println(sql);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = PerformanceInfoDbUtil.getPerfInfoCorrespondConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, channelStatus);
			rs = ps.executeQuery();
			while (rs.next()) {
				DbQueryPerformanceInfo dbQueryPerformanceInfo = new DbQueryPerformanceInfo();
				dbQueryPerformanceInfo.setBtsId(Integer.valueOf(rs.getString("bts_id")));
				dbQueryPerformanceInfo.setChannelStatus(Integer.valueOf(rs.getString("channel_status")));
				dbQueryPerformanceInfo.setCountChannelNumber(Integer.valueOf(rs.getString("channelCount")));;
				perfInfoList.add(dbQueryPerformanceInfo);
			}
			return perfInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}

}

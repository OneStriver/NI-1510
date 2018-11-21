package com.sunkaisens.nms.udpMessageThread.cncpMsg;

/**
 * Cncp私有协议操作类型定义接口
 */
public interface CncpProtoType {
	
	char OAM_MANAGER = 0x04;
	char NMS = 0xE4;
	
	//1510北向接口和1510OMC交互的消息类型
	char NI_HEADER					= 0xE8;
	char OMC_HEADER 				= 0xE0;
	int CNCP_OMC_NI_MSG				= 0x8010;
	int CNCP_OMC_NI_ACK_MSG			= 0x8011;
	int CNCP_NI_OMC_MSG				= 0x8012;
	int	CNCP_NI_OMC_ACK_MSG			= 0x8013;
	
	//-- message type --//
	int CNCP_SET_MSG 			    = 0x7001;		//CNCP_SET_MSG
	int CNCP_SET_ACK_MSG 		    = 0x7002;		//CNCP_SET_ACK_MSG
	int CNCP_GET_MSG 				= 0x7003;       //CNCP_GET_MSG
	int CNCP_GET_ACK_MSG 		    = 0x7004;
	int CNCP_STATUS_MSG 		    = 0x7005;
	int CNCP_ALARM_MSG 			    = 0x7008;
	int CNCP_UIMESSAGE_MSG 	        = 0x7006;      //CNCP_STATISTICS_MSG
	int CNCP_PERFORMENCE_MSG 	    = 0x7006;      
	int CNCP_LOGINFO_MSG 	    	= 0x7020;      
	int CNCP_LINK_STATUS_MSG 	    = 0x7007;
	int CNCP_NOTIFY_MSG 		    = 0x700B;
	int CNCP_ALARM_RElEASE_MSG      = 0x7010;
	int CNCP_MESSTATEINFO_MSG       = 0x700F;
	int CNCP_CONFIGEFFECTIVE_MSG    = 0x700C; 
	
	// sub type of CNCP_GET
	int CNCP_GET_NE_RT_CONF    		= 0x00;
	int CNCP_GET_NE_CONF        	= 0x01; // reserved
	int CNCP_GET_SYSTEM         	= 0x02;
	int CNCP_GET_NE_LOG         	= 0x03;
	int CNCP_GET_NE_LOGLIST     	= 0x04;
	int OAM_GET_IF   				= 0x0A;
	int OAM_GET_IF_CONF   			= 0x0B;
	
	// sub type of CNCP_SET
	int CNCP_SET_NE_RT_CONF_CREATE	= 0x00;
	int CNCP_SET_NE_RT_CONF_DELETE	= 0x01;
	int CNCP_SET_NE_RT_CONF_UPDATE	= 0x02;
	int CNCP_SET_NE_CONF_CREATE   	= 0x03; // reserved
	int CNCP_SET_NE_CONF_DELETE   	= 0x04; // reserved
	int CNCP_SET_NE_CONF_UPDATE   	= 0x05; // reserved
	int CNCP_SET_NE_PLAN_CREATE   	= 0x06;
	int CNCP_SET_NE_PLAN_DELETE   	= 0x07;
	int CNCP_SET_NE_PLAN_UPDATE   	= 0x08;
	int CNCP_SET_NE_LOG_DELETE    	= 0x09;
	int CNCP_SET_NE_FILE_DELETE   	= 0x0A;
	int CNCP_SET_NE_FILE_UPDATE  	= 0x0B;
	int CNCP_SET_NE_PROC_START   	= 0x0C;
	int CNCP_SET_NE_PROC_STOP     	= 0x0D;
	int CNCP_SET_NE_PROC_RESTART  	= 0x0E;
	int CNCP_SET_NE_BOOT_ACTIVE   	= 0x0F;
	int CNCP_SET_NE_BOOT_INACTIVE 	= 0x10;
	int CNCP_SET_OS_EXEC           	= 0x11;
	//网卡 路由  Host 等
	int OAM_GET_THRESHOLD        	= 0x08;
	int OAM_GET_NE_CONF         	= 0x09;
	int OAM_GET_TABLE_IF         	= 0x0A;
	int OAM_GET_TABLE_IF_CONF    	= 0x0B;
	int OAM_GET_SERVICE_STATUS   	= 0x0C;
	int OAM_GET_SERVICE_BOOT_STATUS = 0x0D;
	int OAM_GET_NAMESERVERS         = 0x0E;
	int OAM_GET_HOSTNAME            = 0x0F;
	int OAM_GET_TABLE_GLOBAL_ROUTE  = 0x10;
	int OAM_GET_TABLE_STATIC_ROUTE  = 0x11;
	int OAM_GET_TABLE_HOSTS         = 0x12;
	int OAM_SET_THRESHOLD         	= 0x16;
	int OAM_SET_NE_CONF           	= 0x17;
	int OAM_SET_IF_UP             	= 0x18;
	int OAM_SET_IF_DOWN           	= 0x19;
	int OAM_SET_OBSOLETE1A        	= 0x1A;//#define OAM_SET_IF_CREATE   0x1A
	int OAM_SET_IF_UPDATE         	= 0x1B;
	int OAM_SET_IF_CONF_CREATE    	= 0x1C;
	int OAM_SET_IF_CONF_DELETE    	= 0x1D;
	int OAM_SET_IF_CONF_UPDATE   	= 0x1E;
	int OAM_SET_SERVICE_START     	= 0x1F;
	int OAM_SET_SERVICE_STOP      	= 0x20;
	int OAM_SET_SERVICE_RESTART   	= 0x21;
	int OAM_SET_SERVICE_BOOT_ACTIVE = 0x22;
	int OAM_SET_SERVICE_BOOT_INACTIVE=0x23;
	int OAM_SET_NAMESERVERS         = 0x24;
	int OAM_SET_HOSTNAME            = 0x25;
	int OAM_SET_STATIC_ROUTE_CREATE = 0x26;
	int OAM_SET_STATIC_ROUTE_DELETE = 0x27;
	int OAM_SET_HOSTS_DELETE        = 0x28;
	int OAM_SET_HOSTS_UPDATE        = 0x29;
	//HSS subtype data UE:IMSI\n
	int OAM_GET_UE					= 0X14;
	int OAM_SET_UE_DELETE			= 0X2A;
	int OAM_SET_UE_UPDATE			= 0X2B;
	int OAM_SET_UE_SYNC				= 0x30;
	int OAM_SET_HSS_CREATE			= 0X31;
	int OAM_SET_HSS_DELETE			= 0X32;
	int OAM_SET_HSS_UPDATE			= 0X33;
	int OAM_SET_UE_DESTROY			= 0X34;
	int OAM_SET_UE_RESTORE			= 0X35;
	int OAM_SET_UE_SWOON			= 0X36;
	
	//-- old subtype --//
	int CNCP_SET_TYPE_C = CNCP_SET_NE_RT_CONF_CREATE;
	int CNCP_SET_TYPE_U = CNCP_SET_NE_RT_CONF_UPDATE;
	int CNCP_GET_TYPE_R = CNCP_GET_NE_RT_CONF;
	int CNCP_SET_TYPE_D = CNCP_SET_NE_RT_CONF_DELETE;
	
	//-- status type --//
	int CNCP_STATUS_ACTION_CHANGE 	= 0x01;
	int CNCP_STATUS_ACTION_PERIODIC = 0x00;
	//-- status value --//
	int CNCP_STATUS_UP 				= 0x01;
	int CNCP_STATUS_DOWN			= 0x00;
	
	//-- errors --//
	int CNCP_ERRNO_SUCCESS = 0X01;
	
	//-- response type --//
	int SUCCESS = 0x00;				// 操作成功
	int FAILURE = 0x01;				// 操作失败
	int TIMEOUT = 0x0C;				// 超时
	int ERR_NE_EXIST = 0x02;		// 已规划的网元
	int ERR_NE_NOTEXIST = 0X03;		// 未规划的网元
	int ERR_NE_RUNNING = 0x04;		// 网元已运行
	int ERR_NE_STOPED = 0X05;		// 网元已停止
	int ERR_MESSAGE = 0x06;			// 消息格式错误
	int ERR_FTP_UPLOAD = 0x07;		// FTP上传失败
	int ERR_FTP_DOWNLOAD = 0x08;	// FTP下载失败
	int ERR_EXTRACT = 0x09;			// 解压失败
	int ERR_REMOVE = 0x0A;			// 删除失败
	int ERR_HOST_LOST = 0x0B;		// 宿主丧失
	int ERR_CHANNEL_BUSY = 0x0D;	// 消息拥挤
	int ERR_NE_LOST = 0x0E;			// 网元丧失
	int ERR_REPEAT_SETUP = 0x0F;	// 重复设置
	int ERR_TOO_BIG = 0x10;			// 数据过大
	int ERR_NE_STARTUP=0x11;		// 网元启动失败
	
}

package com.sunkaisens.nms.ws.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sunkaisens.nms.util.CodecUtil;

public class AlarmMsg extends OamMsg {
	private long alarmCode;
	private char alarmLevel;
	private String desc;

	public AlarmMsg(char src, char dest, int type, int transId, long neId, long instId, long alarmCode, char alarmLevel,
			String desc) {
		super(src, dest, type, transId, neId, instId);
		this.len += 36;
		if (desc != null)
			this.len += desc.length();

		this.alarmCode = alarmCode;
		this.alarmLevel = alarmLevel;
		this.desc = desc;
	}

	@Override
	public int code(byte[] array) {
		int offset = super.code(array);

		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		CodecUtil.putUnsignedInt(array, alarmCode, offset);
		CodecUtil.putUnsignedChar(array, alarmLevel, offset + 4);
		CodecUtil.putString(array, time, offset + 5, 31);
		CodecUtil.putStringToChars(array, desc, offset + 36);
		return 36 + desc.length();
	}
}

package com.sunkaisens.nms.ws.dbUtils;

public class AlarmCorrespond {

	private int alarm_code;
	private String alarm_type;
	private String alarm_des;
	private String specific_problem;
	private String probable_cause;
	private String handle_propose;

	public AlarmCorrespond() {
		super();
	}

	public int getAlarm_code() {
		return alarm_code;
	}

	public void setAlarm_code(int alarm_code) {
		this.alarm_code = alarm_code;
	}

	public String getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}

	public String getAlarm_des() {
		return alarm_des;
	}

	public void setAlarm_des(String alarm_des) {
		this.alarm_des = alarm_des;
	}

	public String getSpecific_problem() {
		return specific_problem;
	}

	public void setSpecific_problem(String specific_problem) {
		this.specific_problem = specific_problem;
	}

	public String getProbable_cause() {
		return probable_cause;
	}

	public void setProbable_cause(String probable_cause) {
		this.probable_cause = probable_cause;
	}

	public String getHandle_propose() {
		return handle_propose;
	}

	public void setHandle_propose(String handle_propose) {
		this.handle_propose = handle_propose;
	}

}

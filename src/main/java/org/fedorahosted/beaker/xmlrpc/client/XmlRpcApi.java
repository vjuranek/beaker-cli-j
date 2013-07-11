package org.fedorahosted.beaker.xmlrpc.client;

public enum XmlRpcApi {
	
	AUTH("auth.login_password"),
	JOB_CANCEL("jobs.stop"),
	JOB_XML("jobs.to_xml"),
	JOB_UPLOAD("jobs.upload"),
	TASK_EXTEND("recipes.tasks.extend"),
	TASK_INFO("taskactions.task_info"),
	TASK_RESULTS("taskactions.to_xml"),
	WATCHDOG_SHOW("recipes.tasks.watchdog"),
	WHO_AM_I("auth.who_am_i");
	
	private String rpc;
	
	private XmlRpcApi(String rpc){
		this.rpc = rpc;
	}
	
	public String getRpc(){
		return rpc;
	}

}

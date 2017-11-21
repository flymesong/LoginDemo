package yuepark.bean;

public class ResRegister extends ResBase<ResRegister> {

	private String parkuserid;//用户停车号
	private String mobilenumber;//用户手机号
	private String imeinumber;//手机IMEI号
	private String loginpwd;//登录密码
	private String addtime;//注册时间
	private String userstatus;//用户状态
	public String getParkuserid() {
		return parkuserid;
	}
	public void setParkuserid(String parkuserid) {
		this.parkuserid = parkuserid;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getImeinumber() {
		return imeinumber;
	}
	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}
	public String getLoginpwd() {
		return loginpwd;
	}
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
}

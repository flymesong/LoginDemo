package bean;

public class User {
	//手机号
	private String mobileno;
	//常用手机编号
	private String mobilecode;
	//登录密码
	private String loginpwd;
	//用户真实姓名
	private String username;
	//用户身份证号
	private String idcardno;
	//注册时间
	private String addtime;
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getMobilecode() {
		return mobilecode;
	}
	public void setMobilecode(String mobilecode) {
		this.mobilecode = mobilecode;
	}
	public String getLoginpwd() {
		return loginpwd;
	}
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
}

package bean;

public class Yuepark {
	private int id;
	private String openid;
	private String imgurl;
	private String phone;
	private String password;
	private String accesstoken;
	private String addtime;
	private String openid_xiao;
	private String unionid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	public String getOpenid_xiao() {
		return openid_xiao;
	}
	public void setOpenid_xiao(String openid_xiao) {
		this.openid_xiao = openid_xiao;
	}

	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Yuepark(int id, String openid, String imgurl, String phone,
			String password, String accesstoken, String addtime,
			String openid_xiao, String unionid) {
		super();
		this.id = id;
		this.openid = openid;
		this.imgurl = imgurl;
		this.phone = phone;
		this.password = password;
		this.accesstoken = accesstoken;
		this.addtime = addtime;
		this.openid_xiao = openid_xiao;
		this.unionid = unionid;
	}
	public Yuepark() {
		super();
	}
	
}

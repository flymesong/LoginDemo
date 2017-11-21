package bean;

public class AccessToken {
	private int id;
	private String access_token;
	private String data_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getData_time() {
		return data_time;
	}
	public void setData_time(String data_time) {
		this.data_time = data_time;
	}
	public AccessToken(int id, String access_token, String data_time) {
		super();
		this.id = id;
		this.access_token = access_token;
		this.data_time = data_time;
	}
	public AccessToken() {
		super();
	}
	
}

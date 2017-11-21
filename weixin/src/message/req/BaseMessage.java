package message.req;

public class BaseMessage {

	//开发者微信号
	private String ToUserName;
	//发送发账号(一个openId)
	private String FormUserName;
	//消息创建时间(整形)
	private long CreateTime;
	//消息类型
	private String MsgType;
	//消息ID,64位整形
	private long MsgId;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFormUserName() {
		return FormUserName;
	}
	public void setFormUserName(String formUserName) {
		FormUserName = formUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long MsgId) {
		this.MsgId = MsgId;
	}
	
}

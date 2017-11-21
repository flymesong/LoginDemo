package yuepark.bean;
/**
 * 接口应答
 * @author Administrator
 *
 */
public class ResBase<T> {
	 private String status;//成功标志位（1成功，0失败）
	 private String msg;//错误信息
	 private T data;//解析对象
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	 
}

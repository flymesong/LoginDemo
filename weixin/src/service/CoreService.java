package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import message.resp.Article;
import message.resp.NewsMessage;
import message.resp.TextMessage;
import util.MessageUtil;


public class CoreService {
	 
	//转换emoji表情
	private static String emoji(int codePoint){
		return String.valueOf(Character.toChars(codePoint));
	}
	
	//处理微信发送过来的请求
	public static String processRequest(HttpServletRequest request){
		//XML格式的消息数据
		String respXml = null;
		String respContent = null;
		try{
			//调用parseXml方法解析请求信息
			Map<String,String> requestMap = MessageUtil.parseXml(request);
			//发送方账号
			String fromUserName = requestMap.get("FromUserName");//FromUserName是传送回来的XMl节点
			//开发者微信号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");
			
			//回复一律是文本信息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){//如果是文本类型				
//				respContent = "您发送的是文本类型";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){//如果是图片类型
//				respContent = "您发送的是图片类型";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){//如果是语音类型
//				respContent = "您发送的是语音类型";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){//如果是视频类型
//				respContent = "您发送的是视频类型";
//			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){//如果是地理位置类型
//				respContent = "您发送的是地理位置类型";	
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){//如果是链接类型
//				respContent = "您发送的是链接类型";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){//如果是事件类型
				//事件类型
				String eventType = requestMap.get("Event");
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){//如果是关注事件
					respContent = "你好！欢迎关注YueParking!";
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
//						respContent = "取消关注!";
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
//					respContent = "用户已关注时的扫描带参数二维码!";
//				}else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
//					respContent = "上报地理位置!";
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){//自定义菜单
					String eventKey = requestMap.get("EventKey");
					if(eventKey.equals("V1001_TODAY_MUSIC")){
							respContent = "您可拨打客服热线： 0755-33019165，如有建议或投诉，您也可以在公众号留言，客服时间:9:00-17:30";
					}
				}
			}
			
			//设置文本信息的内容
			textMessage.setContent(respContent);
			respXml = MessageUtil.messageToXml(textMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
		return respXml;
	}
}

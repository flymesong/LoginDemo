$(document).ready(function() {
	init();
});
function init(){
	
	window.alert = function(name){
		var iframe = document.createElement("IFRAME");
		iframe.style.display="none";
		iframe.setAttribute("src", 'data:text/plain,');
		document.documentElement.appendChild(iframe);
		window.frames[0].window.alert(name);
		iframe.parentNode.removeChild(iframe);
		};
	
	
	$.get("PayServlet",{
		code:$("#code").val(),
		paymoney:$("#amount").val()
		
		},
		function(data,status){
			var obj = JSON.parse(data);
			function onBridgeReady(){
			 WeixinJSBridge.invoke(
				        'getBrandWCPayRequest', {
				            "appId":obj.appId,     //公众号名称，由商户传入
				            "timeStamp":obj.timeStamp,         //时间戳，自1970年以来的秒数
				            "nonceStr":obj.nonceStr, //随机串
				            "package":obj.package,
//				            "partnerId":obj.partnerId,
				            "signType":obj.signType,//微信签名方式：
				            "paySign":obj.paySign //微信签名
			        },
			        function(res){
			        	if(res.err_msg == "get_brand_wcpay_request:ok"){  
			        		layer.msg('支付成功', {icon: 1});
			        		setTimeout(function() {
			                location.href="zhanghuchongzhi1.jsp";
//			        			wx.closeWindow();
			        		},3000) ;
			            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
			            	layer.msg('取消支付', {icon: 0});
			            	setTimeout(function() {
			                location.href="zhanghuchongzhi1.jsp";
//			            		wx.closeWindow();
			            	},3000) ;
			            }else{  
			            	layer.msg('支付失败', {icon: 2});
			            	setTimeout(function() {
			            	location.href="zhanghuchongzhi1.jsp";
//			            		wx.closeWindow();
			            	},3000) ;
			            }					
			        });
			}
			
//			alert(data);
			if (typeof WeixinJSBridge == "undefined"){
			    if( document.addEventListener ){
			        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			    }else if (document.attachEvent){
			        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			    }
			 }else{
				 onBridgeReady();
			 }
		}
		
	);
	
	// 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
    var useragent = navigator.userAgent;
    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
        // 这里警告框会阻塞当前页面继续加载
    	layer.msg('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
        // 以下代码是用javascript强行关闭当前页面
        var opened = window.open('about:blank', '_self');
        opened.opener = null;
        opened.close();
    }
	
}

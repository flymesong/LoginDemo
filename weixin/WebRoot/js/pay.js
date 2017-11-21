// JavaScript Document
$(document).ready(function() {
	init();
});
function init(){
	
	//getxuan();
	$("#zhifu").click(function(){
		 WeixinJSBridge.invoke(
			        'getBrandWCPayRequest', {
			            "appId":"wx931a94ba0effdb37",     //公众号名称，由商户传入
			            "timeStamp":"1501668443",         //时间戳，自1970年以来的秒数
			            "nonceStr":"0TX5HL9LV6QAS38U", //随机串
			            "package":"prepay_id=wx201708021807267235d2a9c60261258430",
			            "signType":"MD5",         //微信签名方式：
			            "paySign":"2746C472F89C5DB927FCFA51BE4DAA08" //微信签名
		        },
		        function(res){
		        		// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
		            if(res.err_msg == "get_brand_wcpay_request:ok"){  
		            	layer.msg('支付成功', {icon: 1}); 
		            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
		            	layer.msg('取消支付', {icon: 0});
		            }else{  
		            	layer.msg('支付失败', {icon: 2}); 
		            } 
				});

		function callpay(data){
				if (typeof WeixinJSBridge == "undefined"){
			    if( document.addEventListener ){
			        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			    }else if (document.attachEvent){
			        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			    }
			 }else{
			    onBridgeReady(data);
			 } 
		}
    });
	
}
window.alert = function(name){
	var iframe = document.createElement("IFRAME");
	iframe.style.display="none";
	iframe.setAttribute("src", 'data:text/plain,');
	document.documentElement.appendChild(iframe);
	window.frames[0].window.alert(name);
	iframe.parentNode.removeChild(iframe);
	};
	

// JavaScript Document
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
	
//	burl();
	xuanze();
	paytop();
	$(".retiqu").click(function(){
		$("#coshoutform").submit();
	});
//	$("#zhifu").click(function(){
//		var shuzhi=$("#shuzhi").val();
//		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yueparking.applinzi.com/payment.jsp&response_type=code&scope=snsapi_userinfo&state="+shuzhi+"&connect_redirect=1#wechat_redirect";
//	});
	$("#zhifu").click(function(){
		var result=true;
		var shuzhi=$("#shuzhi").val();
		//var reshuzi = shuzhi.toFixed(2);
	if(shuzhi=="" || shuzhi==null){
//		$("#zhifu").attr("disabled","disabled");
//		alert("请输入充值金额");
		result=false;	
	}else if(isNaN(shuzhi)){
//		$("#zhifu").attr("disabled","disabled");
		result=false;
		
	}else if(shuzhi<=0){
//		$("#zhifu").attr("disabled","disabled");
		result=false;
		
	}else{
		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/payment.jsp&response_type=code&scope=snsapi_base&state="+shuzhi+"&connect_redirect=1#wechat_redirect";
		result=false;
	}
	return result;
	});
	//getxuan();
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
function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    }
    if(obj.value =="."){
    	obj.value="0.";
    }
}
function cashout(){
	var result= true;
	var cashout = $("#cashout").html();	
	var shuzhi=$("#shuzhi").val();
	if(parseFloat(shuzhi)>parseFloat(cashout)){
		layer.msg("提现的金额不能大于余额");
		result = false;
	}
	return result;
	
}
function paytop(){
	$(".chongzhi").on('keyup',function(){
		if($('#shuzhi').val().trim() !== ""){
			$("#zhifu").removeAttr("disabled");
		}else{
			$("#zhifu").attr("disabled", true);
		}
	});	
}
function xuanze(){
	var result = $("#shuzhi");
	$(".money").click(function() {
		$(".money").removeClass("end");
		$(".money").addClass("strat");
		
		
        $(this).each(function(){
			if($(this).hasClass("strat")){
				$(this).removeClass("strat");
				$(this).addClass("end");
			}
			result.val($(this).val());
			$("#zhifu").removeAttr("disabled");
		});
		
    });
	
   
}

	

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
	
	tixian();
	tixianxuanze();
	allCash();
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
//提现
function tixian(){
	$(".tiqu").on('keyup',function(){
		if($('#shuzhi').val().trim() !== ""){
			$("#tixian").removeAttr("disabled");
			$("#tixian").removeClass("btncolor");
			$("#tixian").addClass("btnes");
			
		}else{
			$("#tixian").attr("disabled", true);
			$("#tixian").removeClass("btnes");
			$("#tixian").addClass("btncolor");
		}
	});

}
function tixianxuanze(){
	$("#tixian").click(function(){
		var result=true;
		var shuzhi=$("#shuzhi").val();
		var tiqu = $(".tiqu").val();
		var cashout = $("#cashout").html();
		var a=parseFloat(shuzhi);
		
		if(cashout==""){
			cashout=0.0;
		}
		var b=parseFloat(cashout);
		//var reshuzi = shuzhi.toFixed(2);
	if(isNaN(shuzhi)){
//		$("#tixian").attr("disabled","disabled");
		result=false;
		
	}else if(tiqu<=0){
//		$("#tixian").attr("disabled","disabled");
		layer.msg("提现的金额不能为0");
		result=false;
	}else if(parseFloat(shuzhi)>b){
		layer.msg("提现的金额不能大于余额");
		result = false;
	}else{
//		$("#tixian").removeAttr("disabled");
		
		layer.msg("提现成功");
		setTimeout(function() { 
			$("#coshoutform").submit();
//			WeixinJSBridge.call('closeWindow');
//			wx.closeWindow();
		},2000) ;
			//$("#shuzhi").html(reshuzi);
		result=false;
	}
	return result;
		
	});
}
function allCash(){
	var result = $("#shuzhi");
	$("#allcash").click(function(){
//		alert($("#cashout").html());
		result.val($("#cashout").html());
		$("#tixian").removeAttr("disabled");
		$("#tixian").removeClass("btncolor");
		$("#tixian").addClass("btnes");
	});
}


	

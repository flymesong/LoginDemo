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
		
	window.confirm = function (message) {
		   var iframe = document.createElement("IFRAME");
		   iframe.style.display = "none";
		   iframe.setAttribute("src", 'data:text/plain,');
		   document.documentElement.appendChild(iframe);
		   var alertFrame = window.frames[0];
		   var result = alertFrame.window.confirm(message);
		   iframe.parentNode.removeChild(iframe);
		   return result;
	 };
	login();
	logines();
	yanzhengma();
//	logins();
	var exit_btn=$("#exit_btn").val();
	
	var btn_error = $("#btn_error").val();
	if(exit_btn =="1"){
		layer.msg("您已安全退出");
	}
	if(btn_error == "1"){
		layer.msg("用户名或密码错误！");
	}else if(btn_error == "2"){
		setTimeout(function() {
			layer.msg("登录成功");
			setTimeout(function() { 
				WeixinJSBridge.call('closeWindow');
				},3000) ;
		
		},200) ;
	}else if(btn_error == "0"){
		setTimeout(function() {
			layer.msg("注册成功,已自动登录");
			setTimeout(function() { 
				WeixinJSBridge.call('closeWindow');
				},3000) ;
		
		},200) ;
	};
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
function lighton(){
	$("#zhuce").css('background','#555555');
}
function lightoff(){
	$(this).css('background','#fff');
}

function login(){
	$(".shoujihao").on('keyup',function(){
		if($('#mphone').val().trim() !== "" && $('#pwd').val().trim() !== ""){
			$("#login").removeAttr("disabled");
			$("#login").removeClass("btncolor");
			$("#login").addClass("btnes");
			if($('#yan').val() !== ""){
				$("#zhuce").removeAttr("disabled");
				$("#zhuce").removeClass("btncolor");
				$("#zhuce").addClass("btnhui");
			}else{
				$("#zhuce").attr("disabled", true);
				$("#zhuce").removeClass("btnhui");
				$("#zhuce").addClass("btncolor");
			}
			
		}else{
			$("#login").attr("disabled", true);
			$("#login").removeClass("btnes");
			$("#login").addClass("btncolor");
			
			
		}
	});
	
}
function logines(){
	$("#login").click(function(){
		$.get("LoginFifter",
		{
		mphone:$("#mphone").val(),
		pwd:$("#pwd").val()
		
		},
		function(data,status){
			var obj = JSON.parse(data);
			var snum = obj.snum;
			var msg = obj.msg;
			if(snum == 1){
				var phone =obj.phone;
				var repwd = obj.repwd;
				var type ="1";
				location.href="LoginShouquanServlet?mphone="+phone+"&pwd="+repwd+"&type="+type;				
			}else{
				layer.msg(msg);
			}
		});
	});
	$("#zhuce").click(function(){
//		$("#zhuce").css('background','#555555');
		var result =true;
		var yan = $("#yan").val();
		var ma = $("#ma").val();
		var phone =$("#mphone").val();
		var password = $("#pwd").val();
		if(yan == ""){
			layer.msg("请输入验证码");
			result =false;
		}else if(yan != ma){
			layer.msg("验证码不正确");
			result =false;
		}else{
			$.get("RegisterServlet",
				{
				mphone:$("#mphone").val(),
				pwd:$("#pwd").val()
				
				},
				function(data){
					var obj = JSON.parse(data);
					var statuses = obj.status;
					var msg = obj.msg;
					if(statuses == 1){
						var type ="0";
						location.href="LoginShouquanServlet?mphone="+phone+"&pwd="+password+"&type="+type;
						
					}else{
						layer.msg(msg);
					}
				});
		}
		return result;
	});
	
}
var wait=60;  
function time() {
	var huoqu = document.getElementById("huoqu");
        if (wait == 0) {  
        	$("#huoqu").removeAttr("disabled");  
        	
        	huoqu.value="获取验证码";;  
            wait = 60;  
        } else {  
        	$("#huoqu").attr("disabled", true); 
//        	$(".xiaoma").removeAttr("id");
        	huoqu.value=wait+"s";  
            wait--; 
//            alert(wait);
            setTimeout(function() {  
                time();  
            },  
            1000);  
        }  
    }
var partten = /^1[0-9]{10}/;
function yanzhengma(){
	$("#huoqu").click(function(){
//		checkphone();
		var huoqu = document.getElementById("huoqu");
		
		var a=true;
		var result = true;
		var phone= $("#mphone").val();
		var waitt = 3;
		if(phone ==""){
			layer.msg("请输入手机号");
			result = false;
		}else if(!partten.test(phone)){
			layer.msg("您输入的手机号码不正确，请重新输入");
			result =false;
			
		}else{

			$.post("VerificationServlet",
					{
					phone:$("#mphone").val(),
					ma:$("#ma").val()
					
					},
					function(data,status){
//						location.reload();
						var obj = JSON.parse(data);
						var code = obj.ma;
						$("#ma").val(obj.ma);
						time();
//						huoqu.value = "123";
//						alert($("#huoqu").val());
						result = false;
					});


//			}
		}
		return result;
	});
}



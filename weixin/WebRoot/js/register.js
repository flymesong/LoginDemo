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
	
//	checkphone();
	yanzhengma();
	getxuan();
	login();
	register();
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
    
    var btn_error = $("#btn_error").val();
    if(btn_error == "2"){
		setTimeout(function() {
			layer.msg("注册成功!并已登录");
			setTimeout(function() { 
				WeixinJSBridge.call('closeWindow');
				},3000) ;
		
		},200) ;
	};
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
//document.getElementById("btn").onclick=function(){time(this);}  
var partten = /^1[0-9]{10}/;
function login(){
		$(".shoujihao").on('keyup',function(){
			if($('#phone').val().trim() !== "" && $('#yan').val().trim() !== "" && $('#password').val().trim() !== "" && $('#repassword').val().trim() !== ""){
				$("#register").removeAttr("disabled");
				$("#register").removeClass("btncolor");
				$("#register").addClass("btnes");
				
			}else{
				$("#register").attr("disabled", true);
				$("#register").removeClass("btnes");
				$("#register").addClass("btncolor");
			}
		});
		
}
function register(){
	$("#register").click(function(){
		var result =true;
		var phone= $("#phone").val();
		var password = $("#password").val();
		var repassword =$("#repassword").val();
		var yan = $("#yan").val();
		var ma = $("#ma").val();
		var yanphone=$("#yanphone").val();
	
		if(phone ==""){
			result =false;
		}else if(!partten.test(phone)){
			layer.msg("您输入的手机号码不正确，请重新输入");
			result =false;
//		}else if(yanphone=="0"){
//			layer.msg("该手机号已注册");
//			result =false;
		}else if(yan == ""){
			layer.msg("请输入验证码");
			result =false;
		}else if(yan != ma){
			layer.msg("验证码不正确");
			result =false;
		}else if(password == ""){
	//		layer.msg("请输入密码");
			result =false;
		}else if(password.length<6 || password.length>12){
			layer.msg("请输入6~12位密码");
			result =false;
		}else if(repassword != password){
			layer.msg("两次输入的密码不正确");
			result =false;
		}else{
			$.get("RegisterServlet",
					{
					mphone:$("#phone").val(),
					pwd:$("#password").val()
					
					},
					function(data){
						var obj = JSON.parse(data);
						var statuses = obj.status;
						var msg = obj.msg;
						if(statuses == 1){
//							layer.msg(msg);
							location.href="LoginShouquanServlet?mphone="+phone+"&pwd="+password+"&type=0";
							window.location.close();
							setTimeout(function() { 
								WeixinJSBridge.call('closeWindow');
								},3000) ;
						}else{
							layer.msg(msg);
						}
					});
		}
		return result;
	});
}

function yanzhengma(){
	$("#huoqu").click(function(){
//		checkphone();
		var huoqu = document.getElementById("huoqu");
		
		var a=true;
		var result = true;
		var phone= $("#phone").val();
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
					phone:$("#phone").val(),
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
function checkphone(){
	$("#phone").blur(function(){
		$.post("InfoServlet",//ajax
			{
				phone:$("#phone").val()
			},
			function(data,status){
				var obj = JSON.parse(data);
				$("#yanphone").val(obj.result);
				
			}
		);		
	});

}
function getxuan(){
	$('input[name="checked"]').click(function(){
		
			if($('#phone').val().trim() !== "" && $('#yan').val().trim() !== "" && $('#password').val().trim() !== "" && $('#repassword').val().trim() !== "" && $('input[name="checked"]').is(":checked")==true){
				$("#register").removeAttr("disabled");
				$("#register").removeClass("btncolor");
				$("#register").addClass("btnes");
				
			}else{
				$("#register").attr("disabled", true);
				$("#register").removeClass("btnes");
				$("#register").addClass("btncolor");
			}
		
	});

}
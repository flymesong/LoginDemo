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
	

	yanzhengma();
	forget();
	login();
	
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
			if($('#phone').val().trim() !== "" && $('#yan').val().trim() !== "" && $('#password').val().trim() !== ""){
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

function yanzhengma(){
	$("#huoqu").click(function(){
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
						var obj = JSON.parse(data);
						var code = obj.ma;
						$("#ma").val(obj.ma);
						time();
						result = false;
					});
		}
		return result;
	});
}
function forget(){
	$("#register").click(function(){
		var yan = $("#yan").val();
		var ma = $("#ma").val();
		if(yan != ma){
			layer.msg("验证码不正确");
			result =false;
		}else{
			$.get("ForgetServlet",
					{
				phone:$("#phone").val(),
				password:$("#password").val()
					},
					function(data,status){
						var obj = JSON.parse(data);
						var snum = obj.snum;
						var msg = obj.msg;
						if(snum == 1){
							layer.msg(msg);
//						layer.msg("找回密码成功");
							setTimeout(function() { 
							location.href='login.jsp';
//								wx.closeWindow();
							},3000) ;
							
						}else{
							layer.msg(msg);
						}
			});
		}
	});
}

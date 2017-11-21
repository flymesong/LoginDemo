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
	logines();
	huifu();
	
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

function huifu(){
	$(".shoujihao").on('keyup',function(){
		if($('#pwd').val().trim() !== "" && $('#newpwd').val().trim() !== "" && $('#repwd').val().trim() !== ""){
			$("#queren").removeAttr("disabled");
			$("#queren").removeClass("btncolor");
			$("#queren").addClass("btnes");
			
		}else{
			$("#queren").attr("disabled", true);
			$("#queren").removeClass("btnes");
			$("#queren").addClass("btncolor");
		}
	});
}

function logines(){
	
	$("#queren").click(function(){
		var newpwd1 = $("#newpwd").val();
		var repwd = $("#repwd").val();
		if(repwd != newpwd1){
			layer.msg("两次输入的密码不正确！");
		}else{
			$.get("ModifyServlet",
					{
					mphone:$("#phone").val(),
					oldpwd:$("#pwd").val(),
					newpwd:$("#newpwd").val()
					
					},
					function(data,status){
						var obj = JSON.parse(data);
						var snum = obj.snum;
						var msg = obj.msg;
						if(snum == 1){
							var phone =obj.phone;
							var repwd = obj.repwd;
								layer.msg(msg);
							setTimeout(function() { 
								location.href='wodezhanghu.jsp';
								},3000) ;
						}else{
							layer.msg(msg);
						}
					});
		}
		
	});
}
var wait=2;  
function time() {
        if (wait == 0) {          	 
            wait = 2;  
        } else {   
           wait--; 
            setTimeout(function() {  
                time();  
            },  
            1000);  
        }  
    }

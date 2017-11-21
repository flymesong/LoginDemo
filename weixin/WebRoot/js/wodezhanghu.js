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
		 
//	var abc = $("#abc").val();	 
//	if(abc =="1"){
//		layer.msg("修改成功!");
//	}
	$('#click').on('click',function () {
        $('.cloak').css('display','block');
    });
	$('.cloak').on('click',function () {
        $('.cloak').css('display','none');
    });
    $('.box span').on('click',function () {
        $('.cloak').css('display','none');
    });
    
    $("#btn_exit").click(function(){
//    	var exit_phone=$("#exit_phone").val();
//    	$.ajax({
//		    url : 'LogoutServlet',
////		    type : "GET",
//		    dataType : 'jsonp',
//		    data : {
//		    	exit_phone:$("#exit_phone").val()
//		    },
//		    success : function(data){
//		    	var obj = JSON.parse(data);
//				var code = obj.status;
//				if(code=="1"){
//					alert("注销成功"+code);
//				}
//				else{
//					alert("退出失败"+code);
//				}
////				location.href="login.jsp";
//			}
//		});
    	$.get("LogoutServlet",
				{
    		exit_phone:$("#exit_phone").val()
				
				},
				function(data,status){
					var obj = JSON.parse(data);
					var code = obj.status;
					if(code=="1"){
//						if(confirm("您已安全退出")){
						layer.msg("您已安全退出");
						setTimeout(function() {
							WeixinJSBridge.call('closeWindow');
						},3000) ;
//						}else{
//							wx.closeWindow();
//						}
//						alert("注销成功"+code);
//						location.href="login.jsp?exit_btn=1";
					}
					else{
						layer.msg("退出失败");
//						location.href="register.jsp";
					}
//					location.href="login.jsp";
				});
    	
    });
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


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
	    setInterval(function () {
	    	dingdan();
//	    	alert("112");
		}, 30000);
}
function dingdan(){
	$.get("CurrentOrder",
		{
			mphone :$("#mphone").val()
		},
		function(data,status){
			location.reload();
		});
}

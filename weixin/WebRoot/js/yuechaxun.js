$(document).ready(function() {
	init();
});
function init(){
	tixian();
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
function chongzhi(){
	$("#chongzhi").click(function(){
		location="zhanghuchongzhi.jsp";
	});
}
function tixian(){
	$("#tixian").click(function(){
		var mphone=$("#mphone").val();
		var balance =$("#balance").val();
		location="yuetixian.jsp?mphone="+mphone+"&balance="+balance;
	});
}

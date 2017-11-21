$(document).ready(function() {
	init();
});
function init(){
	$("#close").click(function(){
		layer.msg('开心。。', {icon: 1});
	});
	$(".close").keyup(function(){    
        $(this).val($(this).val().replace(/[^0-9.]/g,''));    
    }).bind("paste",function(){  //CTR+V事件处理    
        $(this).val($(this).val().replace(/[^0-9.]/g,''));     
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
	
	
	$("#dian").click(function(){
		layer.msg('这是最常用的吧');
	});
	$("#xuanze").on('keyup',function(){
		var tt = /^-?([1-9]\d*\.\d{1,2}|0\.\d*[1-9]\d{1,2}|0?\.0+|0)$/;
			var flag = tt.test(this.value);
			alert(flag);
	});
	
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
window.alert = function(name){
	var iframe = document.createElement("IFRAME");
	iframe.style.display="none";
	iframe.setAttribute("src", 'data:text/plain,');
	document.documentElement.appendChild(iframe);
	window.frames[0].window.alert(name);
	iframe.parentNode.removeChild(iframe);
	}
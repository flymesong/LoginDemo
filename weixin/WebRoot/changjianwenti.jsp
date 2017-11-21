<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>约泊停车</title>
<link rel="stylesheet" type="text/css" href="css/changjianwenti.css">
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
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
</script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">常见问题</span></p>
   </div>
<div class="" style="clear:both;"></div>
 <!---------------------内容一项结束-------------------------> 
<div style="margin-left:30px; margin-top:60px; margin-right:30px;">
<p>1.如何联系我们？</p><p class="lan">有任何问题，请您拨打客服电话：0755-86700413，我们将在第一时间帮您解决问题。</p><br/>
<p>2.约泊停车有什么特色？</p><p class="lan">预约车位保证去现场有车可停。</p><br/>
<p>3.使用约泊停车资金安全吗？</p><p class="lan">绝对安全！约泊停车作为最早与微信支付达成战略合作的企业，经过微信支付严格审核并根据移动支付实际金额提供保证金，切实保证资金安全。</p><br/>
<p>4.立即预约是怎么收费的？</p><p class="lan">我们会收取预约的服务费(按次数收取)，还会代停车场收取预约开始到停车入泊位时间段的车位占用费(参考预约停车场的收费规则)；您驶离停车场的时候还需要向停车场缴纳正常停车费用。</p><br/>
<p>5.提前预约是怎么收费的？</p>
<p class="lan">我们会帮您在预约时间点之前提前1个小时开始分配车位，如果分配成功则开始计费(收费规则参考立即预约)。</p><br/>
<p>6.租车位是怎么收费的？</p>
<p class="lan">租车位模式需要收取一笔预约服务费和停车场车位占用费(按天收取)</p><br/>
<p>7.如何管理我的多个车牌？</p>
<p class="lan">在APP首页进入“我的”->“车辆管理”，即可新增、删除车牌信息。</p><br/>
<p>8.到了预约区，为什么无法连接车位锁？</p>
<p class="lan">首先需要确认您的手机是否支持蓝牙4.0，然后打开蓝牙点击“连接车位锁”；如超过3次还是无法连接车位锁，您可以结束订单，车位锁会自动降下，您就可以停车入泊位了。</p><br/>
<p>9.预约之后可以随时结束订单吗？</p>
<p class="lan">可以，如预约还未分配泊位，您无需缴纳费用；如预约已经分配了泊位，则您需要缴纳一定的费用。</p><br/>
<p>10.如果在支付中出现支付异常如何解决？</p>
<p class="lan">可以拨打我们的客服电话，反馈给工作人员，如有多收费用，经核实我们将在一个工作日之内返给您。</p><br/>
<p>11.如何缴纳预约费用？</p>
<p class="lan">只要您有微信或者支付宝都可以缴费；您停好车之后，订单会自动结束，在APP首页点击“我的”->“我的订单”，查找到未支付的订单，点进去选择“立即支付”，您就可以选择微信或者支付宝缴费了。</p><br/>
<p>12.为什么我不能预约车位了？</p>
<p class="lan">您需要进入APP“我的”->“我的订单”，把未缴费的订单全部缴费之后才能进行下一次的预约。</p><br/>

<p>13.有什么方法避免每次手动缴费？</p>
<p class="lan">我们暂时不支持自动扣费，后续我们可能推出自动扣费版本。</p><br/>
<p>14.可以给自己的车位安装一个私人车位锁吗？</p>
<p class="lan">可以的，只要您购买了我们的私人车位锁，输入地锁设备编号添加到我车位，就可以通过手机控制私人车位锁了。</p><br/>
<p>15.我的私人车位被别人添加了怎么办？</p>
<p class="lan">私人车位只能被一个用户添加，您可以拨打我们的客服电话，反馈相关的信息给我们处理。</p><br/>
<p>16.如何购买私人车位锁？</p>
<p class="lan">进入我们的官网购买 http://www.cncadre.com/</p><br/>
</div>
</body>
</html>

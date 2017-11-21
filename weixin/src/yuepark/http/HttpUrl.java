package yuepark.http;

public class HttpUrl {
	 public static String Base_Url = "http://58.250.57.69:5250/Index.aspx?";
	 /**
     * 会员注册
     */
    public static final String Register_Url = Base_Url + "t=member&method=register";

    /**
     * 获取验证码
     */
    public static final String Getsafecode_Url = Base_Url + "t=member&method=getsafecode";

    /**
     * 用户登录
     */
    public static final String Login_Url = Base_Url + "t=member&method=login";

    /**
     * 用户注销登录
     */
    public static final String Logout_Url = Base_Url + "t=member&method=loginout";

    /**
     * 用户资料获取
     */
    public static final String Getuserinfo_Url = Base_Url + "t=member&method=getuserinfo";
    /**
     * 修改登录密码
     */
    public static final String ChangePwd_Url = Base_Url + "t=member&method=updateloginpwd";
    /**
     * 修改手机编号信息
     */
    public static final String ChangeMobilecode_Url = Base_Url + "t=member&method=updatemobilecode";

    /**
     * 找回登录密码
     */
    public static final String ResetPwd_Url = Base_Url + "t=member&method=resetloginpwd";

    /**
     * 获取用户车牌
     */
    public static final String GetPlate_Url = Base_Url + "t=transaction&method=GetplateNO";

    /**
     * 车牌管理
     */
    public static final String PlateNo_Url = Base_Url + "t=transaction&method=PlateNO";

    /**
     * 停车场信息
     */
    public static final String GetParkInfo_Url = Base_Url + "t=berth&method=GetParkingInf";

    /**
     * GIS地图停车场信息
     */
    public static final String GetGISParkInfo_Url = Base_Url + "t=berth&method=GetGISParkingInf";

    /**
     * 获取地图线路坐标信息
     */
    public static final String ParkMapInfo_Url = Base_Url + "t=berth&method=ParkingMapInf";

    /**
     * 立即预约申请
     */
    public static final String ParkApply_Url = Base_Url + "t=transaction&method=parkapply";

    /**
     * 激活车位锁
     */
    public static final String ActCarLock_Url = Base_Url + "t=berth&method=actcarlock";

    /**
     * 获取订单列表
     */
    public static final String GetOrderList_Url = Base_Url + "t=transaction&method=getorderlist";

    /**
     * 取消订单
     */
    public static final String CancelOrder_Url = Base_Url + "t=transaction&method=CancelOrder";

    /**
     * 微信支付
     */
    public static final String Weixinpay_Url = Base_Url + "t=transaction&method=Weixinpay";

    /**
     * 支付宝支付
     */
    public static final String Alipay_Url = Base_Url + "t=transaction&method=alipayrequest";

    /**
     * 支付宝支付结果查询
     */
    public static final String AlipayResult_Url = Base_Url + "t=transaction&method=alipayreturns";

    /**
     * 提前预约申请
     */
    public static final String PreApply_Url = Base_Url + "t=transaction&method=parkapply2";

    /**
     * 租用车位申请
     */
    public static final String RentApply_Url = Base_Url + "t=transaction&method=parkapply3";

    /**
     * 获取消息列表
     */
    public static final String GetPushList_Url = Base_Url + "t=operation&method=getupushlist";

    /**
     * 私人车位管理
     */
    public static final String BerthManage_Url = Base_Url + "t=berth&method=personalberth";

    /**
     * 获取用户车位信息
     */
    public static final String GetLockinfo_Url = Base_Url + "t=berth&method=getpersonalberth";

    /**
     * 查询设备信息信息
     */
    public static final String SeachLockinfo_Url = Base_Url + "t=berth&method=getplocklist";

    /**
     * 余额查询
     */
    public static final String QueryBalance_Url = Base_Url + "t=member&method=QueryBalance";

    /**
     * 余额充值
     */
    public static final String BalanceRecharge_Url = Base_Url + "t=member&method=BalanceRecharge";

    /**
     * 余额明细
     */
    public static final String BalanceDetail_Url = Base_Url + "t=member&method=BalanceDetail";

    /**
     * 余额提现
     */
    public static final String ApplyCashOut_Url = Base_Url + "t=member&method=ApplyCashOut";

    /**
     * 申请泊位
     */
    public static final String BerthCodeApply_Url = Base_Url + "t=transaction&method=BerthCodeApply";

    /**
     * 当前订单
     */
    public static final String CurrentOrder_Url = Base_Url + "t=transaction&method=CurrentOrder";

    /**
     * 控锁状态
     */
    public static final String Setberthstatus_Url = Base_Url + "t=transaction&method=setberthstatus";


    /**
     * 余额支付
     */
    public static final String AccountPay_Url = Base_Url + "t=other&method=AccountPay";

    /**
     * 熟人列表
     */
    public static final String Getfriendslist_Url = Base_Url + "t=berth&method=getfriendslist";

    /**
     * 熟人管理
     */
    public static final String SetFriends_Url = Base_Url + "t=berth&method=setfriends";

    /**
     * 熟人分享
     */
    public static final String FriendShare_Url = Base_Url + "t=berth&method=friendsberthshare";

    /**
     * 全状态上报
     */
    public static final String SetStatus_Url = Base_Url + "t=berth&method=updatalockstate";
    
}

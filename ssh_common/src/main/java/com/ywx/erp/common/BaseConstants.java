package com.ywx.erp.common;

public class BaseConstants {

    //Action名称
    public static final String DEPACTION = "DepAction";
    public static final String EMPACTION = "EmpAction";
    public static final String GOODSACTION = "GoodsAction";
    public static final String GOODSTYPEACTION = "GoodstypeAction";
    public static final String ORDERDETAILACTION = "OrderdetailAction";
    public static final String ORDERSACTION = "OrdersAction";
    public static final String STOREACTION = "StoreAction";
    public static final String STOREDETAILACTION = "StoredetailAction";
    public static final String STOREOPERACTION = "StoreoperAction";
    public static final String SUPPLIERACTION = "SupplierAction";
    
    //结果类型
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    //回显数据名定义
    public static final String TUUID = "t.uuid";
    public static final String TORDERUUID = "t.ordersuuid";
    public static final String TSTOREUUID = "t.storeuuid";
    public static final String TMENUID = "t.menuid";
    public static final String TEMPUUID = "t.empuuid";
    public static final String TGOODSUUID = "t.goodsuuid";
    public static final String TPID = "t.pid";

    public static final String TNAME = "t.name";
    public static final String TUSERNAME = "t.username";
    public static final String TMENUNAME = "t.menuname";
    public static final String TGOODSNAME = "t.goodsname";
    public static final String TSTORENAME = "t.storename";
    public static final String TEMPNAME = "t.empname";

    public static final String TDEPDO = "t.depDo";
    public static final String TGOODSTYPEDO = "t.goodstypeDo";

    public static final String TINPRICE = "t.inprice";
    public static final String TMONEY = "t.money";
    public static final String TPRICE = "t.price";
    public static final String TOPERTIIME = "t.opertime";
    public static final String TOUTPRICE = "t.outprice";

    public static final String TTELE = "t.tele";
    public static final String TPWD = "t.pwd";
    public static final String TGENDER = "t.gender";
    public static final String TEMAIL = "t.email";
    public static final String TADDERSS = "t.address";
    public static final String TBIRTHDAY = "t.birthday";
    public static final String TORIGIN = "t.origin";
    public static final String TPRODUCER = "t.producer";
    public static final String TUNIT = "t.unit";
    public static final String TICON = "t.icon";
    public static final String TURL = "t.url";
    public static final String TSTATE = "t.state";
    public static final String TENDER = "t.ender";
    public static final String TENDTIME = "t.endtime";
    public static final String TNUM = "t.num";
    public static final String TTYPE = "t.type";
    public static final String TCONTACT = "t.contact";

    //日期格式
    public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    //常用字符串
    public static final String NULLSTR = "";
    public static final String DOUHAOSTR = ",";
    public static final String JUHAOSTR= ".";
    public static final String ZEROSTR= "0";
    public static final String ONESTR= "1";
    public static final String SUCCESS = "success";
    public static final String MSG = "msg";

    //整形常量
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;

    //Http
    public static final String CONTENTTYPE_JSON = "application/json;charset=utf-8";

    //StateCode
    public static final String STATE_CREATE = "0";	//未审核
    public static final String STATE_CHECK = "1";	//已审核
    public static final String STATE_START = "2";	//已确认
    public static final String STATE_END = "3";		//已入库
    public static final String STATE_NOT_IN_OUT = "0";	//未入库, 未出库
    public static final String STATE_IN_OUT = "1";		//已入库，已出库
}

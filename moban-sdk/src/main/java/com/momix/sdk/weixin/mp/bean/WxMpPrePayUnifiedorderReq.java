package com.momix.sdk.weixin.mp.bean;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.common.utils.string.CommonUtil;

import java.io.Serializable;
import java.util.Map;

/**
 * <pre>
 *     <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">统一下单</a>
 * </pre>
 * Created by rono on 2016/1/26.
 */
public class WxMpPrePayUnifiedorderReq implements Serializable{
    // private String appid;
    // private String mch_id;
    private String device_info;
    //private String nonce_str;
    //private String sign;
    private String body;
    private String detail;
    private String attach;
    private String out_trade_no;
    private String fee_type;
    private Integer total_fee;
    private String spbill_create_ip;
    private String time_start;
    private String time_expire;
    private String goods_tag;
    private String notify_url;
    private String trade_type;
    private String product_id;
    private String limit_pay;
    private String openid;

    /**
     * 公众账号ID
     * @return
     */
//    public String getAppid() {
//        return appid;
//    }

    /**
     * 公众账号ID
     * @param appid
     */
//    public void setAppid(String appid) {
//        this.appid = appid;
//    }

    /**
     * 商户号
     * @return
     */
//    public String getMch_id() {
//        return mch_id;
//    }

    /**
     * 商户号
     * @param mch_id
     */
//    public void setMch_id(String mch_id) {
//        this.mch_id = mch_id;
//    }

    /**
     * 设备号
     * @return
     */
    public String getDevice_info() {
        return device_info;
    }

    /**
     * 设备号
     * @param device_info
     */
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    /**
     * 随机字符串
     * @return
     */
//    public String getNonce_str() {
//        return nonce_str;
//    }

    /**
     * 随机字符串
     * @param nonce_str
     */
//    public void setNonce_str(String nonce_str) {
//        this.nonce_str = nonce_str;
//    }

    /**
     * 签名
     * @return
     */
//    public String getSign() {
//        return sign;
//    }

    /**
     * 签名
     * @param sign
     */
//    public void setSign(String sign) {
//        this.sign = sign;
//    }

    /**
     * 商品描述
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * 商品描述
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 商品详情
     * @return
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 商品详情
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 附加数据
     * @return
     */
    public String getAttach() {
        return attach;
    }

    /**
     * 附加数据
     * @param attach
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * 商户订单号
     * @return
     */
    public String getOut_trade_no() {
        return out_trade_no;
    }

    /***
     * 商户订单号
     * @param out_trade_no
     */
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    /**
     * 货币类型
     * @return
     */
    public String getFee_type() {
        return fee_type;
    }

    /**
     * 货币类型
     * @param fee_type
     */
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    /**
     * 总金额
     * @return
     */
    public Integer getTotal_fee() {
        return total_fee;
    }

    /**
     * 总金额
     * @param total_fee
     */
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    /**
     * 终端IP
     * @return
     */
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    /**
     * 终端IP
     * @param spbill_create_ip
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    /**
     * 交易起始时间
     * @return
     */
    public String getTime_start() {
        return time_start;
    }

    /**
     * 交易起始时间
     * @param time_start
     */
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    /**
     * 交易结束时间
     * @return
     */
    public String getTime_expire() {
        return time_expire;
    }

    /**
     * 交易结束时间
     * @param time_expire
     */
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    /**
     * 商品标记
     * @return
     */
    public String getGoods_tag() {
        return goods_tag;
    }

    /**
     * 商品标记
     * @param goods_tag
     */
    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    /**
     * 通知地址
     * @return
     */
    public String getNotify_url() {
        return notify_url;
    }

    /**
     * 通知地址
     * @param notify_url
     */
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    /**
     * 交易类型
     * @return
     */
    public String getTrade_type() {
        return trade_type;
    }

    /**
     * 交易类型
     * @param trade_type
     */
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    /**
     * 商品ID
     * @return
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * 商品ID
     * @param product_id
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * 指定支付方式
     * @return
     */
    public String getLimit_pay() {
        return limit_pay;
    }

    /**
     * 指定支付方式
     * @param limit_pay
     */
    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    /**
     * 用户标识
     * @return
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 用户标识
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public static void toMap(WxMpPrePayUnifiedorderReq req,Map<String,String> parameters)throws SdkException {
        if(CommonUtil.isNotEmpty(req.getDevice_info()))
            parameters.put("device_info",req.getDevice_info());

        if(CommonUtil.isEmpty(req.getBody()))
            throw new SdkException("商品描述不能为空！");
        parameters.put("body", req.getBody());

        if(CommonUtil.isNotEmpty(req.getDetail()))
            parameters.put("detail", req.getDetail());

        if(CommonUtil.isNotEmpty(req.getAttach()))
            parameters.put("attach",req.getAttach());

        if(CommonUtil.isEmpty(req.getOut_trade_no()))
            throw new SdkException("商户订单号不能为空");
        parameters.put("out_trade_no",req.getOut_trade_no());

        if(CommonUtil.isNotEmpty(req.getFee_type()))
            parameters.put("fee_type",req.getFee_type());

        if(CommonUtil.isEmpty(req.getTotal_fee()))
            throw new SdkException("总金额不能为空！");
        parameters.put("total_fee",req.getTotal_fee()+"");

        if(CommonUtil.isEmpty(req.getSpbill_create_ip()))
            throw new SdkException("终端IP不能为空");
        parameters.put("spbill_create_ip",req.getSpbill_create_ip());

        if(CommonUtil.isNotEmpty(req.getTime_start()))
            parameters.put("time_start",req.getTime_start());

        if(CommonUtil.isNotEmpty(req.getTime_expire()))
            parameters.put("time_expire",req.getTime_expire());

        if(CommonUtil.isNotEmpty(req.getGoods_tag()))
            parameters.put("goods_tag",req.getGoods_tag());

        if(CommonUtil.isEmpty(req.getNotify_url()))
            throw new SdkException("通知地址不能为空！");
        parameters.put("notify_url",req.getNotify_url());

        if(CommonUtil.isEmpty(req.getTrade_type()))
            throw new SdkException("交易类型不能为空！");
        parameters.put("trade_type",req.getTrade_type());

        if(CommonUtil.isNotEmpty(req.getProduct_id()))
            parameters.put("product_id",req.getProduct_id());

        if(CommonUtil.isNotEmpty(req.getLimit_pay()))
            parameters.put("limit_pay",req.getLimit_pay());

        if(CommonUtil.isNotEmpty(req.getOpenid()))
            parameters.put("openid",req.getOpenid());

        if("JSAPI".equals(req.getTrade_type()) && CommonUtil.isEmpty(req.getOpenid()) )
            throw new SdkException("openid 不能为空！");
    }
}

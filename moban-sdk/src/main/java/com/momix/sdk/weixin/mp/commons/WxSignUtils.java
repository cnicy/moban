package com.momix.sdk.weixin.mp.commons;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * Created by rono on 2016/1/26.
 */
public class WxSignUtils {
    /**
     * <pre>
     *     <a href-"https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3">微信支付算法</a>
     * </pre>
     * @param packageParams
     * @param signKey
     * @return
     */
    public static String createSign(Map<String, String> packageParams, String signKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.putAll(packageParams);

        List<String> keys = new ArrayList<>(packageParams.keySet());
        Collections.sort(keys);

        StringBuffer toSign = new StringBuffer();
        for (String key : keys) {
            String value = packageParams.get(key);
            if (null != value && !"".equals(value) && !"sign".equals(key)
                    && !"key".equals(key)) {
                toSign.append(key + "=" + value + "&");
            }
        }
        toSign.append("key=" + signKey);
        System.out.println(toSign.toString());
        return  DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }
}

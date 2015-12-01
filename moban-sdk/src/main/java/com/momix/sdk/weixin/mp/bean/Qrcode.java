package com.momix.sdk.weixin.mp.bean;

import java.io.Serializable;

/**
 * <pre>
 *  <a href="http://mp.weixin.qq.com/wiki/18/8a8bbd4f0abfa3e58d7f68ce7252c0d6.html">二维码相关</a>
 * </pre>
 * Created by rono on 2015/12/1.
 */
public class Qrcode implements Serializable {
    /**该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。*/
    private Integer expire_seconds;
    private String action_name;
    private ActionInfo action_info;

    public Integer getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfo action_info) {
        this.action_info = action_info;
    }

    /**
     * 二维码详细信息
     */
    public static class ActionInfo{
        private Sence scene;

        public Sence getScene() {
            return scene;
        }

        public void setScene(Sence scene) {
            this.scene = scene;
        }
        /**
         *  二维码场景信息
         */
        public static class Sence{
            private String scene_str;
            private Integer scene_id;

            public String getScene_str() {
                return scene_str;
            }

            public void setScene_str(String scene_str) {
                this.scene_str = scene_str;
            }

            public Integer getScene_id() {
                return scene_id;
            }

            public void setScene_id(Integer scene_id) {
                this.scene_id = scene_id;
            }
        }
    }
}

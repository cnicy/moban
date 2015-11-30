package com.momix.sdk.weixin.mp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单
 * Created by rono on 2015/11/30.
 */
public class WxMenu implements Serializable {
    private List<WxButton> button = new ArrayList<WxButton>();

    public void setButton(WxButton wxButton){
        button.add(wxButton);
    }

    public static class WxButton{
        private String type;
        private String name;
        private String key;
        private String url;

        private List<WxButton> sub_button;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<WxButton> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<WxButton> sub_button) {
            this.sub_button = sub_button;
        }
    }
}

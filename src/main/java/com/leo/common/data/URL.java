package com.leo.common.data;

import com.leo.common.utils.HttpServletUtil;

/**
 * 封装URL地址，自动添加应用上下文路径
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/21 12:40
 **/
public class URL {
    private String url;
    public URL(){ }

    /**
     * 封装URL地址，自动添加应用上下文路径
     * @param url URL地址
     */
    public URL(String url){
        this.url = HttpServletUtil.getRequest().getContextPath() + url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

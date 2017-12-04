/*
 * Copyright (C) 2017 Zhejiang BYCDAO Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.BYCDAO.com.
 * Developer Web Site: http://open.BYCDAO.com.
 */

package com.Marissa.demo.domain;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * @since:swagger-bootstrap-ui-demo 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a> 
 * 2017/08/10 14:12
 */
public class Menu {

    private String label;
    private String text;
    private List<Menu> childrens;

    public Menu(String label, String text) {
        this.label = label;
        this.text = text;
    }

    public void addChildren(Menu menu){
        if (childrens==null){
            childrens=new ArrayList<>();
        }
        childrens.add(menu);
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Menu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Menu> childrens) {
        this.childrens = childrens;
    }
}

package com.qy.main.bean;

import java.io.Serializable;

public class Obj implements Serializable
{
    private static final long serialVersionUID = 5612409872778709567L;
	/** 项目名称 */
	public String title;
	/** 项目简介路径 */
//	public String introPath;
	/** 是否商用过 */
	public boolean isUse;
	/** 是否有演示界面 */
	public boolean isEnable;
	/** 跳转到的演示界面 */
	public Class _class;
	/** 开源地址 */
	public String url;
	public Obj(String title/*, String introPath*/, boolean isUse) {
	    super();
	    this.title = title;
//	    this.introPath = introPath;
	    this.isUse = isUse;
	    this.isEnable = false;
    }
	public Obj(String title/*, String introPath*/, boolean isUse,String url) {
		super();
		this.title = title;
//		this.introPath = introPath;
		this.isUse = isUse;
		this.isEnable = false;
		this.url = url;
	}
	public Obj(String title/*, String introPath*/, boolean isUse, boolean isEnable, Class _class) {
		super();
		this.title = title;
//		this.introPath = introPath;
		this.isUse = isUse;
		this.isEnable = isEnable;
		this._class = _class;
	}
	public Obj(String title/*, String introPath*/, boolean isUse, boolean isEnable,String url, Class _class) {
		super();
		this.title = title;
//		this.introPath = introPath;
		this.isUse = isUse;
		this.isEnable = isEnable;
		this.url = url;
		this._class = _class;
	}
	
}

package com.zyf.t31.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Restful相应结果实体类
 * @author 雪松
 * @date 2021/10/28 11:54
 */
@Data
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标记
	 */
	private boolean success = true;
	/**
	 * 提示信息
	 */
	private String msg = "操作成功";
	/**
	 * 添加，修改的实体类
	 */
	private Object model;

	/**
	 * http状态码
	 */
	private int code = 200;

	/**
	 * session id
	 */
	private String token;

	private String errorCode;

	private String errorMessage;

	public ResponseBean(){

	}
	public ResponseBean(String errorCode,String errorMessage,String msg){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.msg = msg;
	}

}

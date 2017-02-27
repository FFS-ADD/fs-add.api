package com.accenture.fsadd.common.mvc.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * メッセージモーダル。
 *
 */
public class MessageModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * メッセージコード。
     */
    private String msgCode;

    /**
     * ラベルID。
     */
    private String labelId;

    /**
     * メッセージパラメーター。
     */
    private Object[] arguments = null;

    public MessageModel() {

    	this("", "", null);
    }

    public MessageModel(String messageCode, String labelId, Object[] arguments) {
        super();
        this.msgCode = messageCode;
        this.labelId = labelId;
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    /**
     * @return msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode
     *            セットする msgCode
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return labelId
     */
    public String getLabelId() {
        return labelId;
    }

    /**
     * @param labelId
     *            セットする labelId
     */
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

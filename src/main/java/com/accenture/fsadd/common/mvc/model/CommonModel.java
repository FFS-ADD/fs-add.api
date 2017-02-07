package com.accenture.fsadd.common.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.accenture.fsadd.common.APIExecutedStatusType;

/**
 * APIからモバイル側の戻り値の親クラス
 *
 *
 */
public class CommonModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * メッセージ。<br/>
     * サーバーからモバイル側にメッセージに渡すために使う。
     */
    private List<MessageModel> msgList;

    /**
     * ステータス。APIExecutedStatusType中に定義したコードを使う必要。<br/>
     * {@link APIExecutedStatusType}
     */
    private int status = APIExecutedStatusType.SUCCESS.getValue();

    /**
     * 失敗した場合、失敗した理由を記述する。
     */
    private int errorCause;


    /**
     * 失敗した場合は、失敗が発生したところを記述する。<BR/>
     * ESBエラーが発生した場合は、どっちのESB IFにエラーが発生したのは、記載する。、
     */
    private int errorLocation;

    public CommonModel() {
        msgList = new ArrayList<MessageModel>();
    }

    /**
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return list
     */
    public List<MessageModel> getMsgList() {
        return msgList;
    }

    /**
     * @param status
     *            セットする status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param msgCode
     *            セットする msgCode
     */
    public void setMsgList(List<MessageModel> msgList) {
        this.msgList = msgList;
    }

    /**
     * 単一メッセージ設定の場合
     *
     * @param msgCode
     *            メッセージコード
     */
    public void setMsg(String msgCode) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMsgCode(msgCode);
        this.msgList.add(messageModel);
    }

    /**
     * 単一メッセージ設定の場合
     *
     * @param msgCode
     *            メッセージコード
     */
    public void setMsg(String msgCode, String labelid) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMsgCode(msgCode);
        messageModel.setLabelId(labelid);
        this.msgList.add(messageModel);
    }

    /**
     * 単一メッセージ設定の場合
     *
     * @param msgCode
     *            メッセージコード
     */
    public void setMsg(String msgCode, String labelid, Object[] arguments) {
        MessageModel messageModel = new MessageModel(msgCode, labelid, arguments);
        this.msgList.add(messageModel);
    }

    public int getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(int errorCause) {
        this.errorCause = errorCause;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int getErrorLocation() {
        return errorLocation;
    }

    public void setErrorLocation(int errorLocation) {
        this.errorLocation = errorLocation;
    }

}

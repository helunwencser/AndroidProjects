package edu.cmu.lunwenh.mortgage.exception;

/**
 * Created by lunwenh on 3/21/2016.
 */
public class InputEmptyException extends Exception {
    /* error information */
    private String errorInfo;
    /* error number */
    private int errorNum;

    public InputEmptyException(String errorInfo, int errorNum) {
        this.errorInfo = errorInfo;
        this.errorNum = errorNum;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }
}

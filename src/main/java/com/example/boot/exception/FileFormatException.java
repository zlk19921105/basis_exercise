package com.example.boot.exception;

/**
 * 自定义异常
 * Administrator zhoulk
 * date: 2018/10/28 0028
 */
public class FileFormatException extends  Exception {
   private  String message;

    public FileFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "文件不是EXCEL";
    }
}

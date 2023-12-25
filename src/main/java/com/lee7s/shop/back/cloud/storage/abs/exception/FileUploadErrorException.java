package com.lee7s.shop.back.cloud.storage.abs.exception;

/**
 * @author somg
 * @date 2023/3/8 7:46
 * @do 文件上传异常
 */
public class FileUploadErrorException extends RuntimeException{

    public FileUploadErrorException(){
        super("File-Upload-Error");
    }

}

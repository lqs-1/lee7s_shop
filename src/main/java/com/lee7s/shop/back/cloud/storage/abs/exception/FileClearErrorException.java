package com.lee7s.shop.back.cloud.storage.abs.exception;

/**
 * @author somg
 * @date 2023/3/8 11:25
 * @do 文件删除异常
 */
public class FileClearErrorException extends RuntimeException{

    public FileClearErrorException(){
        super("File-Clear-Error");
    }

}

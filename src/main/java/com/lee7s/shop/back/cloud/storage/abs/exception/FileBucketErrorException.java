package com.lee7s.shop.back.cloud.storage.abs.exception;

/**
 * @author somg
 * @date 2023/3/14 11:51
 * @do Bucket异常
 */
public class FileBucketErrorException extends RuntimeException{

    public FileBucketErrorException(){
        super("File-Bucket-Error");
    }


}

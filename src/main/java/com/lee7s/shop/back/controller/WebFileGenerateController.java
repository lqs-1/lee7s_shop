package com.lee7s.shop.back.controller;

import com.lee7s.shop.back.cloud.storage.abs.upload.FileUploadPlus;
import com.lee7s.shop.back.service.StorageService;
import com.lee7s.shop.back.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author somg
 * @date 2023/1/16 12:27
 * @do 网络图片生成控制器
 */

@Controller
@RequestMapping("back")
@Api(tags = "上传模块")
public class WebFileGenerateController {


    @Autowired
    private StorageService storageService;




    /**
     * 单张图片上传
     * @param file
     * @return
     */
    @PostMapping("web-file-generate/single")
    @ResponseBody
    public R webFileSingleGenerate(@RequestBody MultipartFile file) {

        return storageService.singleUpload(file);

    }



    /**
     * 批量图片上传 一般用不上
     * @param files
     * @return
     */
    @PostMapping("web-file-generate/multi")
    @ResponseBody
    public R webFileMultiGenerate(@RequestBody List<MultipartFile> files){

        return storageService.multiUpload(files);


    }

}

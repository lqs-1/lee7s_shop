package com.lee7s.shop.back.service.mpl;

import com.lee7s.shop.back.cloud.storage.abs.upload.FileUploadPlus;
import com.lee7s.shop.back.service.StorageService;
import com.lee7s.shop.back.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private FileUploadPlus fileUpload;


    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public R singleUpload(MultipartFile file) {

        R result = fileUpload.build().singleFileUpload(file);

        String url = fileUpload.build().extractFileFullPath((String) result.get("web-file-url"), true);
        result.put("url", url);
        // uploadPlusProperties.getBaseUrl() + item.getFile()

        return result;
    }

    /**
     * 批量文件上传
     *
     * @param files
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public R multiUpload(List<MultipartFile> files) {
        return null;
    }
}

package com.lee7s.shop.back.service;

import com.lee7s.shop.back.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    R singleUpload(MultipartFile file);
    R multiUpload(List<MultipartFile> files);
}

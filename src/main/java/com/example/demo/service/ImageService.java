package com.example.demo.service;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class ImageService {
    public String uploadImage(MultipartFile file, String path) {
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "web-yemu2";
        String filedir = path;
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        String prefix = UUID.randomUUID().toString();
        String prefix2 = prefix + ".jpg";
        File tempFile = null;
        try {
            //创建临时文件
            tempFile = File.createTempFile(prefix2, prefix2);
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, filedir + prefix2, tempFile);
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, filedir + prefix2, expiration);
        ossClient.shutdown();
        String str = url.toString();
        String sub = str.substring(0, str.indexOf("?Expires="));
        String s = sub.replace("web-yemu2.oss-cn-shanghai.aliyuncs.com", "img.panbingwen.cn");

        return s;
    }

}

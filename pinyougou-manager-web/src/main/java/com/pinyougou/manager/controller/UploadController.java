package com.pinyougou.manager.controller;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-07-30<p>
 */
@RestController
public class UploadController {

    /** 注入文件服务器访问地址: 把属性文件中的key对应的value注入 */
    @Value("${fileServerUrl}")
    private String fileServerUrl;

    /** 上传文件 */
    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file")MultipartFile multipartFile){
        Map<String, Object> data = new HashMap<>();
        // {status : 200, url : 'http://192.'}
        data.put("status", 500);
        try{

            // 获取fastdfs_client.conf，得到它得绝对路径
            String conf_filename = this.getClass().getResource("/fastdfs_client.conf").getPath();
            // 初始化客户端全局信息对象
            ClientGlobal.init(conf_filename);
            // 创建存储客户对象
            StorageClient storageClient = new StorageClient();

            // 获取上传文件的字节数组
            byte[] fileByteData = multipartFile.getBytes();
            // 获取上传文件的原文件名
            String originalFilename = multipartFile.getOriginalFilename();

            // 上传文件到FastDFS服务器
            String[] arr = storageClient.upload_file(fileByteData,
                    FilenameUtils.getExtension(originalFilename), null);

            // [group1, M00/00/01/wKgMg1te9FGAVyByAAUK0BcEKSA328.jpg]
            /**
             * 访问路径：http://192.168.12.131/group1/M00/00/01/wKgMg1te9FGAVyByAAUK0BcEKSA328.jpg
             **/
            StringBuilder url = new StringBuilder(fileServerUrl);
            for (String str : arr){
                url.append("/" + str);
            }

            data.put("status", 200);
            data.put("url", url.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }
}

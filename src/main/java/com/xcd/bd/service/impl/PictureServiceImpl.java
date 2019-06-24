package com.xcd.bd.service.impl;

import com.xcd.bd.service.IPictureService;
import com.xcd.bd.utils.FileUtil;
import com.xcd.bd.utils.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureServiceImpl implements IPictureService {

    @Value("${image.upload.path}")
    private String filePath;
//    private String filePath = "/Users/lijinku/Documents/picture/";

    @Override
    public Map<String, Object> uploadPicture(MultipartFile uploadFile) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            if (uploadFile != null && uploadFile.getOriginalFilename()
                    != null && uploadFile.getOriginalFilename().length() > 0) {

                //生成一个新的文件名
                //取原始文件名
                String oldName = uploadFile.getOriginalFilename();

                //生成新文件名
                //UUID.randomUUID();
                String newName = IDUtils.genImageName();

                newName = newName + oldName.substring(oldName.lastIndexOf("."));

                //新文件
                File file = new File(filePath + newName);

                //将内存中的文件写入磁盘
                uploadFile.transferTo(file);

                //图片上传成功后，将图片的地址写回
                resultMap.put("error", 0);
                resultMap.put("url", "/picture/" + newName);
                return resultMap;

            } else {
                //返回结果
                resultMap.put("error", 1);
                resultMap.put("message", "文件异常");
                return resultMap;
            }
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

    @Override
    public boolean deleteFile(String picName) throws Exception {

        picName = picName.substring(picName.lastIndexOf("/") + 1);

        picName = "D:\\upload\\temp\\img\\" + picName;

        FileUtil.deleteFile(picName);

        return true;
    }
}

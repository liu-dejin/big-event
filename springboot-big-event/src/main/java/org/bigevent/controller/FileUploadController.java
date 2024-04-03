package org.bigevent.controller;

import lombok.extern.slf4j.Slf4j;
import org.bigevent.utils.AliOssUtils;
import org.bigevent.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
public class FileUploadController {

    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        /**
         *         // 保存文件到本地
         *         String originalFilename = file.getOriginalFilename();
         *         // 通过 uuid 生成新的文件名
         *         String filename = null;
         *         if (originalFilename != null) {
         *             filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
         *         }
         *         // 保存文件到指定目录
         *         file.transferTo(new File("D:\\desktopD:\\desktop\\" + filename));        // 保存文件到本地
         *         String originalFilename = file.getOriginalFilename();
         *         // 通过 uuid 生成新的文件名
         *         String filename = null;
         *         if (originalFilename != null) {
         *             filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
         *         }
         *         // 保存文件到指定目录
         *         file.transferTo(new File("D:\\desktopD:\\desktop\\" + filename));
         */
        String originalFilename = file.getOriginalFilename();
        String filename = null;
        if (originalFilename != null) {
            filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String url = AliOssUtils.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}

package com.jason.controller;

import com.jason.pojo.Result;
import com.jason.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    /*
      将文件上传到本地
     */
    /*@PostMapping(("/upload"))
    public Result upload(String name, Integer age, MultipartFile image) {
        log.info("文件上传: name-{}; age-{}; file-{};", name, age, image);

        //上传相同名字的文件时保证文件不会覆盖
        // 1. 获取原始文件名
        String originalFilename = image.getOriginalFilename();
        // 2. 获取文件扩展名(.xxx)
        assert originalFilename != null;
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 3. (随机名+文件扩展名) - uuid(通用唯一识别码)
        String newFileName = UUID.randomUUID() + extname;
        log.info("newFileName:{}", newFileName);

        //将临时文件转存到服务器的磁盘目录
        try {
            image.transferTo(new File("D:\\JavaTutorial\\Codes\\1-WebProject\\10-tlias-web-management\\image\\" + newFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.success();
    }*/

    private final AliOSSUtils aliOSSUtils;

    public UploadController(AliOSSUtils aliOSSUtils) {
        this.aliOSSUtils = aliOSSUtils;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());
        // 调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成, 文件访问的url: {}", url);
        // 将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }
}

package com.itheima.controller;

import com.itheima.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @Classname CommonController
 * @Description TODO
 * @Date 2022/5/28 16:22
 * @Created by luochao
 */
@Slf4j
@RestController
@RequestMapping("common")
public class CommonController {

    @Value("${reggit.path}")
    private String filePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    public R<String> upload(MultipartFile file) {
        //拼凑文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + suffix;
        //创建文件夹
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //将文件从临时文件夹移动到指定目录
        try {
            file.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);

    }

    @GetMapping("/download")
    public void download(@RequestParam("name") String fileName, HttpServletResponse response) {
        //创建流
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            try {
                fileInputStream = new FileInputStream(filePath + fileName);
            }catch (FileNotFoundException e){
                fileInputStream = new FileInputStream(filePath + "default.jpg");
            }
            //设置文件类型
            response.setContentType("image/jpeg");

            //读文件
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}

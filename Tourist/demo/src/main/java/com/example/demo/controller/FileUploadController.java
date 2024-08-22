package com.example.demo.controller;

import com.example.demo.pojo.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    //@PostMapping("/upload")
    //public Result<String> upload(MultipartFile file) throws IOException {
        //把文件内容存储到本地磁盘中
     //   String Filename = file.getOriginalFilename(); //自动获取文件名
        //保证文件名唯一 防止被覆盖
       // String filename = UUID.randomUUID().toString() + Filename.substring(Filename.lastIndexOf("."));
        //文件传输
        //file.transferTo( new File("E:\\demovue1\\vue-project\\src\\assets"+filename));
        //返回
        //return Result.success("E:\\demovue1\\vue-project\\src\\assets");
    //}
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "E:\\demovue1\\vue-project\\src\\assets"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/assets/" + fileName;
        model.addAttribute("filename",filename);
        System.out.println("http://localhost:8099/assets/"+fileName);
        return Result.success("http://localhost:8099/assets/"+fileName);
    }
}

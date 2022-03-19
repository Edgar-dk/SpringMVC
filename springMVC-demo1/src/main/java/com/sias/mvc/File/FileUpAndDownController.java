package com.sias.mvc.File;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Edgar
 * @create 2022-01-27 13:56
 */

/*
* 实现文件的上传和下载的*/


@Controller
public class FileUpAndDownController {
    @RequestMapping(value = "/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径,获取的是服务器的部署路径，就是这个部署到tomcat服务器上
        String realPath = servletContext.getRealPath("/static/img/01.png");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=01.png");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }


    @RequestMapping(value = "/testUp")
//    方法中的参数的作用是将获取的文件封装到这个对象中的，但是不可以直接去封装到这个对象中，那就需要用到配置文件的
//    作用去把文件封装到这个对象中，配置信息写好了，需要根据id来获取到这个配置文件，还是需要在配置文件上加上id的，来获取这个解析器
    public String testUp(MultipartFile photo ,HttpSession session) throws IOException {
        //获取的是文件名，获取了，还是没有上传到哪一个地方
        String filename = photo.getOriginalFilename();
        //解决文件名可能出现重复的问题，先过去文件的后缀名,包括点和后面的名字
        String suffixName=filename.substring(filename.lastIndexOf("."));
        //UUID作为文件名
        String uuid= UUID.randomUUID().toString().replaceAll("-"," ");
        //uuid文件名和后缀拼接成一个文件名
        filename=uuid+suffixName;
        //想要上传到哪一个地方，需要用session来获取服务器的路径,session 向去创建一个context对象，在去获取路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        //获取的路径存不存在还是不知道的，那就需要一个判断语句，来判断一下,
        //怎么去判断么，那就需要用文件对象去判断路径存在与否
        File file=new File(photoPath);
        if (!file.exists()){
            //若是路径不存在就去创建一个，路径，上面的条件不成立执行下面的语句
            file.mkdir();
        }
        //路径存在的话，直接在前面加上文件夹的名字，后面的一个是斜线，由于不知道是哪一种，就用文件的形式去条用方法，表示两种，最后把获取的内容写在最后
        String finalPath=photoPath + File.separator + filename;
        //根据这个路径写好之后，需要最后的一步，就是上传的那一步
        photo.transferTo(new File(finalPath));
        return "success";
    }
}

package com.chen.controller;

import com.chen.dao.Analysis_Dao;
import com.chen.dao.Log_Dao;
import com.chen.dao.Pollution_Dao;
import com.chen.entity.Log;
import com.chen.monitor_data.TimeSimple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class Download_Ctrl {

    @Resource
    Pollution_Dao pollution_dao;

    @Resource
    Analysis_Dao analysis_dao;

    @Resource
    Log_Dao log_dao;

    //返回下载的页面
    @RequestMapping("download_html")
    public String download(Model mv, String form_city, HttpSession session){
        System.out.println("session="+session.getAttribute("status"));
        System.out.println("city="+form_city);
        String city_02 = form_city == ""?"江门":form_city;
        File mkdir = null;//目录
        switch (form_city){
            case "江门" :
                mkdir = new File("D:/table/data/city/JiangMen");form_city="JiangMen";break;
            case "广州" :
                mkdir = new File("D:/table/data/city/GuangZhou");form_city="GuangZhou";break;
            case "珠海" :
                mkdir = new File("D:/table/data/city/FoShan");form_city="FoShan";break;
            case "佛山" :
                mkdir = new File("D:/table/data/city/ZhuHai");form_city="ZhuHai";break;
            default:
                mkdir = new File("D:/table/data/city/JiangMen");form_city="JiangMen";break;
        }
        File[]files = mkdir.listFiles();
        if(files != null){
            String []fileArr = new String[files.length];
            for(int i=0;i<files.length;i++){
                String name=files[i].toString();
                fileArr[i] = name.substring(name.indexOf(form_city)+form_city.length()+1);
                System.out.println(fileArr[i]);
            }
            mv.addAttribute("fileArr",fileArr);
        }else {
            System.out.println("没有数据");
        }
        mv.addAttribute("city",form_city);
        mv.addAttribute("city_02",city_02);

        return "download";
    }

    //提供下载
    @RequestMapping("/download")
    public void downloadFile(HttpServletResponse response, HttpSession session,String id, String city) throws IOException {
        String username = (String)session.getAttribute("username");
        if(session.getAttribute("username")!=null){
            try {
                System.out.println("id="+id);
                System.out.println("city="+city);
                File file = new File("D:\\table\\data\\city\\"+city+"/"+id);
                String filename = id;
                InputStream inputStream = new FileInputStream(file);
                //强制下载不打开
                response.setContentType("application/force-download");
                OutputStream out = response.getOutputStream();
                //使用URLEncoder来防止文件名乱码或者读取错误
                response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename, "UTF-8"));
                int b = 0;
                byte[] buffer = new byte[1000000];
                while (b != -1) {
                    b = inputStream.read(buffer);
                    if(b!=-1)
                        out.write(buffer, 0, b);
                }
                inputStream.close();
                out.close();
                out.flush();
                Log log = new Log(TimeSimple.timeSimple(),username,username+"用户下载了一次"+city+":"+id+"的表格数据");
                int i = log_dao.insert_log(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            response.sendRedirect("/login");
        }
    }

}

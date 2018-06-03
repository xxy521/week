package com.jk.controller;


import com.alibaba.fastjson.JSON;
import com.jk.entity.Prize;
import com.jk.entity.Prizes;
import com.jk.entity.User;
import com.jk.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prize")
public class PrizeController {

    @Resource
    private PrizeService prizeService;

    @RequestMapping("/queryXq")
    public void queryXq(HttpSession session, HttpServletResponse response, Integer curr, Integer limit){

        //System.out.println(curr+"    "+limit);
        //开始条数  limit每页查几条

            int start=(curr-1)*limit;
            List<Prizes> list=prizeService.queryXq(start,limit);

            Map map=new HashMap();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", list.size());
            map.put("data", list);

            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSON.toJSONString(map));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


    }



    @RequestMapping("/queryPrize")
    public String queryPrize(HttpSession session, HttpServletResponse response, Integer curr, Integer limit){

        //System.out.println(curr+"    "+limit);
        //开始条数  limit每页查几条

        User dbuser = (User) session.getAttribute("dbuser");

        if (dbuser !=null) {

            int start=(curr-1)*limit;
            List<Prize> list=prizeService.queryPrize(start,limit);

            for (int i=0;i<list.size();i++){
                Prize prize = list.get(i);
                prize.setAnniu("<button class='layui-btn' onclick='query("+prize.getPid()+")'> 兑换</button>");

            }

            Map map=new HashMap();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", list.size());
            map.put("data", list);

            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSON.toJSONString(map));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else {

         return "请登录后查询";



        }


        return null;
    }





}

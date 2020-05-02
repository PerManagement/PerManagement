package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.dao.AfficheMapper;
import com.newer.domain.Affiche;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("affiche")
public class AfficheController {

    @Autowired
    private AfficheMapper afficheMapper;

    @GetMapping("findAffichens")
    public CommonsResult findAffichens(Integer userid){

        Example example=new Example(Affiche.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userid",userid);

        List list=this.afficheMapper.selectByExample(example);
        return new CommonsResult(200, "跳转到查看公告页面",list);
    }



}

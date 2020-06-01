
        package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Affiche;
import com.newer.domain.User;
import com.newer.dto.AfficheDto;
import com.newer.service.AfficheService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 公告模块控制层
 * 2020-05-02
 * 陈良吉
 */
@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("affiche")
public class AfficheController {

    @Autowired
    private AfficheService afficheService;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("findAffiches")
    public CommonsResult findAffichens(Integer page,Integer pageSize,Integer userid, ModelMap modelMap) {
        AfficheDto afficheDto=new AfficheDto();
        afficheDto.setUserid(userid);
        afficheDto.setPage(page);
        afficheDto.setPageSize(pageSize);
        System.out.println(afficheDto.getUserid());
        PageInfo<Affiche> pageInfo=this.afficheService.findAffiches(afficheDto);
        if(pageInfo!=null)
            return new CommonsResult(200, "公告列表", pageInfo);
        return new CommonsResult(500, "今日暂无通告", pageInfo);
    }

    @PostMapping("saveAffiche")
    public CommonsResult saveAffiche(@RequestBody Affiche affiche) {
//        System.out.println("affiche="+affiche);
        affiche.setReleasetime(new Date());
        if (this.afficheService.saveAffiche(affiche))
            return new CommonsResult(200, "公告发布成功", affiche);
        return new CommonsResult(500, "公告发布失败", null);
    }

    @GetMapping("findAfficheByDate")
    public CommonsResult findAfficheByDate(){
        Affiche affiche= this.afficheService.findAfficheByDate();

        if(affiche!=null)
            return new CommonsResult(200, "登录通告显示", affiche);
        return new CommonsResult(500, "今日无通告", null);
    }

}


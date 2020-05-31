package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Evection;
import com.newer.domain.EvectionAccount;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.EvectionAccountService;
import com.newer.service.EvectionService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("evectionaccount")
public class EvectionAccountController {
    @Autowired
    private EvectionAccountService evectionAccountService;

    @GetMapping("findAal")
    public CommonsResult findAal(Integer page, Integer pageSize){
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageInfo pageInfo=this.evectionAccountService.findAal(dto);

        return new CommonsResult(200,"出差分页",pageInfo);
    }
    @PostMapping("save")
    public CommonsResult save(@RequestBody EvectionAccount evectionaccount, ModelMap modelMap){
        User user = (User) modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        System.out.println(user);
        user.setUserid(user.getUserid());
        Evection evection = (Evection) modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        evection.setEvectionid(evection.getEvectionid());
        int a=this.evectionAccountService.save(evectionaccount);
        if(a>0){
            return  new CommonsResult(200,"成功",evection);
        }
        return  new CommonsResult(200,"有误",null);
    }
}

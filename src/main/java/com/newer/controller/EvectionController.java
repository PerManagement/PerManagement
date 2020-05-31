package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Evection;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.EvectionService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 出差控制层
 */
@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("evection")
public class EvectionController {
    @Autowired
    private EvectionService evectionService;

    @GetMapping("findlhl")
    public CommonsResult findlhl(Integer page, Integer pageSize){
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageInfo pageInfo=this.evectionService.findlhl(dto);

        return new CommonsResult(200,"出差分页",pageInfo);
    }
    @GetMapping("findAal2")
    public CommonsResult findAal2(Integer page, Integer pageSize){
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        System.out.println(dto);
        PageInfo pageInfo=this.evectionService.findAal2(dto);
        return new CommonsResult(200,"出差分页",pageInfo);
    }
    @PostMapping("save")
    public CommonsResult save(@RequestBody Evection evection, ModelMap modelMap){
        User user = (User) modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        System.out.println(user);
        evection.setUserid(user.getUserid());
        evection.setApprover(user.getUpno());
        evection.setEvectionstate(null);
        evection.setRemark("出差");
        System.out.println(evection);
        int a=this.evectionService.save(evection);
        if(a>0){
            return  new CommonsResult(200,"成功",evection);
        }
        return  new CommonsResult(200,"有误",null);
    }
    @GetMapping("updatelhl")
    public CommonsResult updatelhl(@RequestParam Integer userid,Integer evectionid){
        System.out.println(userid);
        System.out.println(evectionid);
        int a=this.evectionService.updatelhl(userid,evectionid);
        if(a>0){
            System.out.println("成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState")
    public CommonsResult updateState(@RequestParam Integer evectionid){
        int a=this.evectionService.updateState(evectionid);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState2")
    public CommonsResult updateState2(@RequestParam Integer evectionid){
        int a=this.evectionService.updateState2(evectionid);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState3")
    public CommonsResult updateState3(@RequestParam Integer evectionid,Integer approver ,String remark){
        int a=this.evectionService.updateState3(evectionid,approver,remark);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updatelhl2")
    public CommonsResult updatelhl2(@RequestParam Integer evectionid, Date evectiondate, String evectionaddress, String evectionreason, Date stopevection){
        int a=this.evectionService.updatelhl2(evectionid,evectiondate,evectionaddress,evectionreason,stopevection);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"成功",a);
        }
        return  new CommonsResult(200,"有误",null);
    }
}
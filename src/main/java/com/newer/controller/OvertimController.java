package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Overtim;
import com.newer.dto.OvertimDto;
import com.newer.service.OvertimService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("overtim")
public class OvertimController {
    @Autowired
    private OvertimService overtimService;

    @PostMapping("saveOvertim")
    public CommonsResult saveOvertim(@RequestBody Overtim overtim) {
        overtim.setOvertimedated(new Date());
        overtim.setOvertimtype("待审批");
        if (this.overtimService.saveOvertim(overtim))
            return new CommonsResult(200, "请假申请成功", overtim);
        return new CommonsResult(500, "请假申请失败，请尝试再次申请", null);
    }

    @PostMapping("findOvertimByUpno")
    public CommonsResult findOvertimByUpno(@RequestBody OvertimDto overtimDto) {
        PageInfo<Overtim> pageInfo = this.overtimService.findOvertimByUpon(overtimDto);
        if(pageInfo!=null)
            return new CommonsResult(200, "请假申请成功", pageInfo );
        return new CommonsResult(500, "请假申请失败，请尝试再次申请", null);
    }

    @PostMapping("findOvertimByUserId")
    public CommonsResult findOvertimByUserId(@RequestBody OvertimDto overtimDto) {

        System.out.println("dto=="+overtimDto);

        PageInfo<Overtim> pageInfo = this.overtimService.findOvertimByUserId(overtimDto);
        if(pageInfo!=null)
            return new CommonsResult(200, "请假申请成功", pageInfo );
        return new CommonsResult(500, "请假申请失败，请尝试再次申请", null);
    }

    @PostMapping("updateOvertimByUpno")
    public CommonsResult updateOvertimByUpno(@RequestBody Overtim form) {
        System.out.println("overtim=="+form);
        switch (form.getOvertimstate()){
            case "同意":form.setOvertimtype("待确认");
                break;
            case "需修改":form.setOvertimtype("待修改");
                break;
            case "不同意":form.setOvertimtype("已完成");
                break;
        }

        if(this.overtimService.updateOvertimByUpno(form))
            return new CommonsResult(200, "审批成功", form );
        return new CommonsResult(500, "审批失败，请再次尝试", null);
    }
//    @GetMapping("findOvertimByKey")
//    public CommonsResult findOvertimByKey(Integer overtimid) {
//
//        System.out.println("overtimid=="+overtimid);
//        Overtim overtim=this.overtimService.findOvertimByKey(overtimid);
//
//        if(overtim!=null)
//            return new CommonsResult(200, "查看详情", overtimid );
//        return new CommonsResult(500, "查看详情失败，请再次尝试", null);
//    }
}

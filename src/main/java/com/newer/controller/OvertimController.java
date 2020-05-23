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
//        System.out.println("overtim=="+overtim);
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
}

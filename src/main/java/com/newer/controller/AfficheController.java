package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Affiche;
import com.newer.domain.User;
import com.newer.dto.AfficheDto;
import com.newer.service.AfficheService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 公告模块控制层
 * 2020-05-02
 * 陈良吉
 */
@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("affiche")
public class AfficheController {

    private AfficheService afficheService;

    @GetMapping("findAffichens")
    public CommonsResult findAffichens(AfficheDto afficheDto, ModelMap modelMap) {
        User user=(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        afficheDto.setUserid(user.getId());

        PageInfo<Affiche> pageInfo=this.afficheService.findAffiches(afficheDto);
        return new CommonsResult(200, "公告列表", pageInfo);
    }

    @GetMapping("saveAffiche")
    public CommonsResult saveAffiche(@RequestBody Affiche affiche) {
        if (this.afficheService.saveAffiche(affiche))
            return new CommonsResult(200, "公告发布成功", affiche);
        return new CommonsResult(500, "公告发布失败", null);
    }


}

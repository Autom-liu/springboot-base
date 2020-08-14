package com.edu.scnu.baseinfo.web.api;

import com.edu.scnu.baseinfo.query.AppQuery;
import com.edu.scnu.baseinfo.service.AppService;
import com.edu.scnu.baseinfo.vo.AppVO;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.common.vo.ResultList;
import com.edu.scnu.common.vo.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/page")
    public IResult queryPage(@RequestBody AppQuery appQuery) throws BizException {
        appQuery.setPageFlag(true);
        PageVO<AppVO> pageVO = appService.queryPage(appQuery);
        return ResultPage.success(pageVO);
    }

    @PostMapping("/list")
    public IResult queryList(@RequestBody AppQuery appQuery) throws BizException {
        List<AppVO> resultList = appService.queryList(appQuery);
        return ResultList.success(resultList);
    }

    @GetMapping("/one")
    public IResult getOne(AppQuery appQuery) throws BizException {
        AppVO appVO = appService.queryOneBySimpleName(appQuery.getSimpleName());
        return Result.success(appVO);
    }

}

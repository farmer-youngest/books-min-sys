package com.marubi.security.business.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.marubi.security.business.dto.BookEditParamDto;
import com.marubi.security.business.dto.BookPageParamDto;
import com.marubi.security.business.service.IBooksService;
import com.marubi.security.business.vo.ShowBookVo;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.utils.UploadCommonUtils;
import com.marubi.security.common.validatedinter.GroupAdd;
import com.marubi.security.common.validatedinter.GroupEdit;
import com.marubi.security.system.dto.LoginDto;
import com.marubi.security.system.utils.UserHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "图书管理")
public class BooksController {
    @Autowired
    @Qualifier("booksServiceImpl")
    private IBooksService booksService;


    @PutMapping("/add")
    @ApiOperation("新增图书")
    public Result<Boolean> add(@Validated({GroupAdd.class}) @RequestBody BookEditParamDto paramDto) throws IOException {
        return Result.build(booksService.add(paramDto));
    }

    @PostMapping("/edit")
    @ApiOperation("修改图书")
    public Result<Boolean> edit(@Validated({GroupEdit.class}) @RequestBody BookEditParamDto paramDto) throws IOException {
        return Result.build(booksService.edit(paramDto));
    }


    @DeleteMapping("/del")
    @ApiOperation("删除图书")
    public Result<Boolean> del(@RequestParam("id")Integer id) throws IOException {
        return Result.build(booksService.del(id));
    }


    @GetMapping("/page")
    @ApiOperation("图书分页列表查询")
    public Result<IPage<ShowBookVo>> page(BookPageParamDto pageParamDto) throws IOException {
        return Result.build(booksService.page(pageParamDto));
    }

}

package com.kelin.mall.controller;

import com.kelin.mall.common.api.CommonPage;
import com.kelin.mall.common.api.CommonResult;
import com.kelin.mall.mbg.model.PmsBrand;
import com.kelin.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBand(@RequestBody PmsBrand pmsBrand) {
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            LOGGER.debug("createBand success: {}", pmsBrand);
            return CommonResult.success(pmsBrand);
        }
        LOGGER.debug("createBand failed: {}", pmsBrand);
        return CommonResult.failed("创建失败！");
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id , @RequestBody PmsBrand pmsBrandDto) {
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            LOGGER.debug("updateBand success: {}", pmsBrandDto);
            return CommonResult.success(pmsBrandDto);
        }
        LOGGER.debug("updateBand failed: {}", pmsBrandDto);
        return CommonResult.failed("更新失败！");
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBand success: {}", id);
            return CommonResult.success(null);
        }
        LOGGER.debug("deleteBand failed: {}", id);
        return CommonResult.failed("删除失败！");
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<PmsBrand> pmsBrands = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }
}

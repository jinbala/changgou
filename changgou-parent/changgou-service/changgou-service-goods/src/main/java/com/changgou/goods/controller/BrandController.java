package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.changgou.util.Result;
import com.changgou.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brand")
//用于解决js跨域问题=微服务中,一般需要注解
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;


    /**
     * 查询所有品牌
     */
    @RequestMapping
    public Result<List<Brand>> findAll(){
        List<Brand> all = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询信息品牌成功",all);
    }

    /**
     * 根据id查询
     */
    @RequestMapping("{id}")
    public Result<Brand> getBrandById(@PathVariable Integer id){
        Brand brandById = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查询品牌成功",brandById);
    }
}

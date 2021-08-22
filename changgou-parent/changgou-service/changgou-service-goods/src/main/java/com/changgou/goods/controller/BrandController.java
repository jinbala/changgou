package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.changgou.util.Result;
import com.changgou.util.StatusCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 新增品牌数据
     *
     * @param brand
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"新增品牌成功");
    }
    /**
     * 修改品牌数据
     *
     * @param brand
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Brand brand,@PathVariable Integer id) {
        //设置id
        brand.setId(id);
        //修改数据
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改品牌成功");
    }
    /**
     * 删除品牌数据
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /**
     * 多品牌搜索品牌方法
     *
     * @param brand
     */
    @PostMapping(value = "search")
    public Result<List<Brand>> findBrand(@RequestBody(required = false) Brand brand) {
        List<Brand> selectList = brandService.findBrand(brand);
        return new Result<>(true,StatusCode.OK,"查询成功",selectList);
    }
    /**
     * 分页查询
     *
     * @param page
     * @param size
     */
    @GetMapping(value = "search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /**
     * 条件查询,分页查询
     *
     * @param brand
     * @param page
     * @param size
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> pageTerm(@RequestBody(required = false) Brand brand, @PathVariable int page,@PathVariable int size) {
        //执行搜索
        PageInfo<Brand> pageInfo = brandService.pageTerm(brand,page,size);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }
}

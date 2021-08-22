package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     */
    List<Brand> findAll();
    /**
     * 根据id查询
     */
    Brand findById(Integer id);
    /**
     * 新增品牌数据
     */
    void add(Brand brand);
    /**
     * 修改品牌数据
     */
    void update(Brand brand);
    /**
     * 删除品牌数据
     */
    void delete(Integer id);
    /**
     * 多品牌搜索品牌方法
     */
    List<Brand> findBrand(Brand brand);
    /**
     * 分页查询
     */
    PageInfo<Brand> findPage(int page,int size);
    /**
     * 条件查询,分页查询
     */
    PageInfo<Brand> pageTerm(Brand brand,int page,int size);
}

package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    /**
     * 禁止简单成员变量注入
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询品牌所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增品牌数据
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改品牌数据
     *
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 删除品牌数据
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 多品牌搜索品牌方法
     *
     * @param brand
     */
    @Override
    public List<Brand> findBrand(Brand brand) {
        //构建查询条件
        Example example = createExample(brand);
        //根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        //设置分页条件
        PageHelper.startPage(page,size);
        //查询数据
        List<Brand> brandList = brandMapper.selectAll();
        //封装并返回分页对象
        return new PageInfo<>(brandList);
    }

    /**
     * 条件查询,分页查询
     *
     * @param brand
     * @param page
     * @param size
     */
    @Override
    public PageInfo<Brand> pageTerm(Brand brand, int page, int size) {
        //设置分页条件
        PageHelper.startPage(page,size);
        //分页查询数据列表-方式一
        //构建查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand !=null){
            if (StringUtils.isNotBlank(brand.getName())){
                criteria.andLike("name","%"+brand.getName() +"%");
            }
        }
        List<Brand> brandList = brandMapper.selectByExample(example);
        //总记录数
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    /**
     * 条件查询组合方法
     */
    private Example createExample(Brand brand){
        //构建查询条件
        Example example = new Example(Brand.class);
        //创建条件构建器
        Example.Criteria criteria = example.createCriteria();
        if (brand != null){
            //品牌名称
            if(StringUtils.isNotEmpty(brand.getName())){
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            //品牌的首字母
            if (StringUtils.isNotEmpty(brand.getLetter())){
                criteria.andLike("letter",brand.getLetter());
            }
        }
        return example;
    }
}

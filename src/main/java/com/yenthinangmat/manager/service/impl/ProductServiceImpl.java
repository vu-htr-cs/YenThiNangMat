package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.mapper.ProductMapper;
import com.yenthinangmat.manager.repository.ProductRepository;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDisplayDTO> showAllProduct() {
        List<ProductEntity> list= productRepository.findAll(PageRequest.of(0,8)).get().toList();
        List<ProductDisplayDTO> res= new ArrayList<>();
        list.forEach(product->res.add(ProductMapper.toDisplay(product)));
        return res;
    }

    @Override
    @Transactional
    public ProductDisplayDTO saveA(ProductEntity productEntity) {
        return ProductMapper.toDisplay(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDisplayDTO findOne(Long id) {
        return ProductMapper.toDisplay(productRepository.findFirstById(id));
    }

    @Override
    public void update(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public ProductEntity findOneE(Long id) {
        return productRepository.findFirstById(id);
    }

    @Override
    public List<ProductDisplayDTO> getListProduct(Pageable pageable) {
       List<ProductEntity> pe= productRepository.findAll(pageable).stream().toList();
       List<ProductDisplayDTO> res=new ArrayList<>();
       pe.forEach(item->res.add(ProductMapper.toDisplay(item)));
       return res;
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public List<ProductDisplayDTO> getAll() {
        List<ProductDisplayDTO> res=new ArrayList<>();
        productRepository.findAll().forEach(item->res.add(ProductMapper.toDisplay(item)));
        return res;
    }

    @Override
    public List<ProductDisplayDTO> getListByCategoryId(Long categoryId, Pageable pageable) {
        List<ProductEntity> pe=productRepository.findByCategoryId(categoryId,pageable).stream().toList();
        List<ProductDisplayDTO> res=new ArrayList<>();
        pe.forEach(item->res.add(ProductMapper.toDisplay(item)));
        return res;
    }

    @Override
    public long countListByCId(Long id) {
        return productRepository.findAllByCategoryId(id).stream().count();
    }


}

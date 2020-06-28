package com.tutorial.crud.service.impl;

import com.tutorial.crud.entity.Size;
import com.tutorial.crud.entity.Size;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.SizeRepository;
import com.tutorial.crud.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Size getSizeById(Integer sizeId) {
        return sizeRepository.findById(sizeId).orElseThrow(()->new ResourceNotFoundException("Size","Id",sizeId));
    }

    @Override
    public Page<Size> getAllSizes(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Size createSize(Size size) {
        Size newSize=new Size();
        newSize.setName(size.getName());
        return sizeRepository.save(newSize);
    }

    @Override
    public Size editSize(Size sizeRequest, Integer sizeId) {
        if(!sizeRepository.existsById(sizeId))
            throw new ResourceNotFoundException("Size","Id",sizeId);
        return sizeRepository.findById(sizeId).map(size->
        {
            size.setName(sizeRequest.getName());
            return sizeRepository.save(size);
        }).orElseThrow(()->new ResourceNotFoundException("Size","Id",sizeId));
    }

    @Override
    public ResponseEntity<?> deleteSizeById(Integer sizeId) {
        return sizeRepository.findById(sizeId).map(size->{
            sizeRepository.delete(size);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Size"));
    }
}

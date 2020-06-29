package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Size;
import com.dopta.repository.SizeRepository;
import com.dopta.service.SizeService;
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
        return sizeRepository.findById(sizeId).orElseThrow(() -> new ResourceNotFoundException("Size", "Id", sizeId));
    }

    @Override
    public Page<Size> getAllSizes(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Size createSize(Size size) {
        Size newSize = new Size();
        newSize.setName(size.getName());
        return sizeRepository.save(newSize);
    }

    @Override
    public Size editSize(Size sizeRequest, Integer sizeId) {
        if (!sizeRepository.existsById(sizeId))
            throw new ResourceNotFoundException("Size", "Id", sizeId);
        return sizeRepository.findById(sizeId).map(size ->
        {
            size.setName(sizeRequest.getName());
            return sizeRepository.save(size);
        }).orElseThrow(() -> new ResourceNotFoundException("Size", "Id", sizeId));
    }

    @Override
    public ResponseEntity<?> deleteSizeById(Integer sizeId) {
        return sizeRepository.findById(sizeId).map(size -> {
            sizeRepository.delete(size);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Size"));
    }
}

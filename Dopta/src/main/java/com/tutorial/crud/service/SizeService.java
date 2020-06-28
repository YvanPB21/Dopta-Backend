package com.tutorial.crud.service;

import com.tutorial.crud.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SizeService {

        Size getSizeById(Integer sizeId);
        Page<Size> getAllSizes(Integer sizeId, Pageable pageable);
        Size createSize(Size size);
        Size editSize(Size sizeRequest, Integer sizeId);
        ResponseEntity<?> deleteSizeById(Integer sizeId);

}

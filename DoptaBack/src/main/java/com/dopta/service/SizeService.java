package com.dopta.service;

import com.dopta.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SizeService {
    Size getSizeById(Integer sizeId);

    Page<Size> getAllSizes(Pageable pageable);

    Size createSize(Size size);

    Size editSize(Size sizeRequest, Integer sizeId);

    ResponseEntity<?> deleteSizeById(Integer sizeId);
}

package com.emploi.service;

import com.emploi.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final AdminRepo adminRepo;
}

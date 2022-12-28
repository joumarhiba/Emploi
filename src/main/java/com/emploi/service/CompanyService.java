package com.emploi.service;

import com.emploi.helpers.FileUploadUtil;
import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements UserDetailsService{

    private final CompanyRepo companyRepo;

   /* public String saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid");
            }
            return fileName;
        } catch (Exception e) {
            throw new Exception("could not  save the file "+fileName);
        }
    }

    public Company addCompany(Company company,@RequestParam("image") MultipartFile image) throws Exception {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        company.setImage(fileName);
        String uploadDir = "Company-photos/" + company.getLogin();
        FileUploadUtil.saveFile(uploadDir, fileName, image);
        return companyRepo.save(company);
    }
        */

    public List<Company> findAllCompanies(){
        return companyRepo.findAll();
    }



    public Company loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Company> company = companyRepo.findByEmail(email);
        return companyRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Company not found ...."));
    }

    @Override
    public UserDetails loadUserByUsername(String emailAndRole) throws UsernameNotFoundException {
        return null;
    }

}

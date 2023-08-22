package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    Certificate findCertificateById(Integer id);

}

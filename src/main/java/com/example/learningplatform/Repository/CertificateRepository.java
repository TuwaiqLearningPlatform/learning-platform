package com.example.learningplatform.Repository;

import com.example.learningplatform.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    Certificate findCertificateById(Integer id);

    @Query("SELECT c FROM certificates c WHERE c.student = ?1")
    List<Certificate> findAllByStudentId(Integer id);


}

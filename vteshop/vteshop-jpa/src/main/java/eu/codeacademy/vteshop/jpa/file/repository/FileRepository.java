package eu.codeacademy.vteshop.jpa.file.repository;

import eu.codeacademy.vteshop.jpa.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

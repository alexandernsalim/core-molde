package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findRequestById(Integer id);

}

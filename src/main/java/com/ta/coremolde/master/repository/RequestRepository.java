package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findRequestById(Integer id);

}

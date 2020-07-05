package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findRequestById(Integer id);

    List<Request> findAllByStatusEquals(Integer status);

    Request findRequestByAccount_IdAndStatus(Integer accountId, Integer status);

}

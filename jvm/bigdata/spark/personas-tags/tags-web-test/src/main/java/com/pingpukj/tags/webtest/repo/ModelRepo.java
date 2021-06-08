package com.pingpukj.tags.webtest.repo;


import com.pingpukj.tags.webtest.entity.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelPo, Long> {

    ModelPo findByTagId(Long tagId);


}

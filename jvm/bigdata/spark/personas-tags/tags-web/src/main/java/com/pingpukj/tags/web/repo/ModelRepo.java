package com.pingpukj.tags.web.repo;


import com.pingpukj.tags.web.entity.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelPo, Long> {

    ModelPo findByTagId(Long tagId);


}

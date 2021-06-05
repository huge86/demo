package com.pingpukj.tags.webend.repo;

import com.pingpukj.tags.webend.entity.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelPo, Long> {

    ModelPo findByTagId(Long tagId);


}

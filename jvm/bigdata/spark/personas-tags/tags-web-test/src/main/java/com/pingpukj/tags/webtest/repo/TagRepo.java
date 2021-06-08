package com.pingpukj.tags.webtest.repo;


import com.pingpukj.tags.webtest.entity.po.TagPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<TagPo, Long> {
    /**
     * 根据名称和等级查找
     *
     * @param name
     * @param level
     * @return
     */
    List<TagPo> findByNameAndLevelAndPid(String name, Integer level, Long pid);

    /**
     * 根据PID查找
     *
     * @param pid
     * @return
     */
    List<TagPo> findByPid(Long pid);

    /**
     * 根据等级查找
     *
     * @param level
     * @return
     */
    List<TagPo> findByLevel(Integer level);
}

package com.pingpukj.tags.web.service;


import com.pingpukj.tags.web.entity.dto.ModelDto;

public interface Engine {

    String startModel(ModelDto modelDto);
    void stopModel(ModelDto modelDto);
}

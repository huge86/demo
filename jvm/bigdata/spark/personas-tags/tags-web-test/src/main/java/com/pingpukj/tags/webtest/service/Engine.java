package com.pingpukj.tags.webtest.service;


import com.pingpukj.tags.webtest.entity.dto.ModelDto;

public interface Engine {

    String startModel(ModelDto modelDto);

    void stopModel(ModelDto modelDto);
}

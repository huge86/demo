package com.pingpukj.tags.webend.service;

import com.pingpukj.tags.webend.entity.dto.ModelDto;

public interface Engine {

    void startModel(ModelDto modelDto);
    void stopModel(ModelDto modelDto);
}

package com.pingpukj.tags.webend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagModelDto {
    private TagDto tag;
    private ModelDto model;
}

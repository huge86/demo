package com.pingpukj.tag.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * Author: HuZunJie
 * Date: 2021-07-09 11:57
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:
 */


public class PersonasModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 模型id */
    private Long modelId;

    /** 标签id */
    @Excel(name = "标签id")
    private Long tagId;

    /** 模型名称 */
    @Excel(name = "模型名称")
    private String modelName;

    /** 更新周期 */
    @Excel(name = "更新周期")
    private String scheTime;

    /** 程序入口 */
    @Excel(name = "程序入口")
    private String modelMain;

    /** 算法类型 */
    @Excel(name = "算法类型")
    private String type;

    /** 算法引擎 */
    @Excel(name = "算法引擎")
    private String modelPath;


    /** 模型参数 */
    @Excel(name = "模型参数")
    private String args;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getScheTime() {
        return scheTime;
    }

    public void setScheTime(String scheTime) {
        this.scheTime = scheTime;
    }

    public String getModelMain() {
        return modelMain;
    }

    public void setModelMain(String modelMain) {
        this.modelMain = modelMain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "PersonasModel{" +
                "modelId=" + modelId +
                ", tagId=" + tagId +
                ", modelName='" + modelName + '\'' +
                ", scheTime='" + scheTime + '\'' +
                ", modelMain='" + modelMain + '\'' +
                ", type='" + type + '\'' +
                ", modelPath='" + modelPath + '\'' +
                ", args='" + args + '\'' +
                '}';
    }
}

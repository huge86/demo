package com.pingpukj.tags.web.controller;



import com.pingpukj.tags.up.HDFSUtils;
import com.pingpukj.tags.web.entity.Codes;
import com.pingpukj.tags.web.entity.HttpResult;
import com.pingpukj.tags.web.entity.dto.ModelDto;
import com.pingpukj.tags.web.entity.dto.TagDto;
import com.pingpukj.tags.web.entity.dto.TagModelDto;
import com.pingpukj.tags.web.service.TagAndModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class TagAndModelController {

    @Autowired
    private TagAndModelService service;

    /**
     * 添加标签
     */
    @PutMapping("tags/relation")
    public void putTags(@RequestBody List<TagDto> tags){
        service.addTagsByRelation(tags);
        System.out.println("success..");
    }


    @GetMapping("tags")
    public HttpResult<List<TagDto>> getTagByLevelOrId(@RequestParam(required = false) Integer level,
                                                      @RequestParam(required = false)  Long pid){

        List<TagDto> list = null;

        if (level == null && pid != null) {
            //根据ID查找
            list = service.findByPid(pid);
        }
        if (level != null && pid == null) {
            //根据等级查找查找
            list = service.findByLevel(level);
        }

        return new HttpResult<List<TagDto>>(1,"查询成功", list);
    }

    /**
     * 四级界面新增标签模型
     * @param tagModelDto
     * @return
     */
    @PutMapping("tags/model1")
    public HttpResult putModel(@RequestBody TagModelDto tagModelDto){
        System.out.println("tagModelDto1:" + tagModelDto);
        service.addTagModel(tagModelDto.getTag(), tagModelDto.getModel());
        return new HttpResult(Codes.SUCCESS, "成功", null);
    }

    /**
     * 基于 pid task or tag
     *   如果pid是三级标签 显示task
     *   如果pid是四级标签 显示tag
     * @param pid
     * @return
     */
    @GetMapping("tags/model2")
    public HttpResult getModel(Long pid){
        System.out.println("model2: pid = " + pid);

        List<TagModelDto> dto = service.findModelByPid(pid);
        return new HttpResult(Codes.SUCCESS, "查询成功", dto);
    }

    @PutMapping("tags/data")
    /**
     * 添加五级标签
     */
    public HttpResult putData(@RequestBody TagDto tagDto){
        service.addDataTag(tagDto);
        return new HttpResult(Codes.SUCCESS, "添加成功", null);
    }

    @PostMapping("tags/{id}/model")
    public HttpResult changeModelState(@PathVariable Long id, @RequestBody ModelDto modelDto){
        System.out.println(id + "==>>" + modelDto.getState());
        service.updateModelState(id, modelDto.getState());
        return new HttpResult(Codes.SUCCESS, "执行成功", null);
    }


    @PostMapping("/tags/upload")
    public HttpResult uploadFile(MultipartFile file){
        String path = HDFSUtils.getInstance().getDefaultFs() +  "/tmp/jars/" + UUID.randomUUID().toString() + ".jar";
        String contentType = file.getContentType();
        System.out.println(contentType);
        long size = file.getSize();

        System.out.println(size);
        try {
            HDFSUtils.getInstance().copyFromInput(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResult(Codes.ERROR_UPLOAD, "上传失败", null);
        }
        return new HttpResult(Codes.SUCCESS, "上传成功", path);
    }


}

<template>
<div class="container"
    :native="false"
    :noresize="true"
>
  <el-scrollbar class="scrollbar">
    <el-menu
      class="menu-container"
      :unique-opened="true"
      :collapse-transition="true"
      mode="vertical"
    >
      <tag-item-group pid="-1"></tag-item-group>
    </el-menu>
  </el-scrollbar>
 <div class="tag-add">
   <a @click="dialogFormVisible = true"><i class="el-icon-plus"></i>      新建主分类标签</a>

   <el-dialog title="创建分类标签" :visible.sync="dialogFormVisible">
    <el-form :model1="formData">
      <el-form-item v-for="data in formData" :key="data.level" :label=data.label :label-width="formLabelWidth">
        <el-input v-model="data.name"  placeholder="最多可输入10个字符" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="createTag">确 定</el-button>
    </div>
  </el-dialog>
 </div>
</div>
</template>

<script>
import axios from "axios"
import config from "../../Config"
import TagItemGroup from "./TagItemGroup"

export default {
  name: "TagView",
  components: {
    TagItemGroup
  },
  computed: {
    variables() {
      console.log(variables)
      return variables
    }
  },
  data() {
    return {
      tags: null,
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        oneTag: '',
        towTag: '',
        threeTag: ''
      },
      formData: [
          {
            "label": "一级标签名称",
            "name": "",
            "level": "1"
          },
          {
            "label": "二级标签名称",
            "name": "",
            "level": "2"
          },
          {
            "label": "三级标签名称",
            "name": "",
            "level": "3"
          }
      ],
        formLabelWidth: '120px'

    };
  },
  methods: {
    createTag: function (event) {
      //将三级标签信息发送后台
    const params =this.formData;
    console.log(params)
    axios
      .put(config.baseApi + "tags/relation", params)
      .then(response => {
        this.tags = response.data;
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
        this.formData = [
          {
            "label": "一级标签名称",
            "name": "",
            "level": "1"
          },
          {
            "label": "二级标签名称",
            "name": "",
            "level": "2"
          },
          {
            "label": "三级标签名称",
            "name": "",
            "level": "3"
          }
      ]
      //关闭输入框
      this.dialogFormVisible = false
      location.reload()
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 100%;
  border-right: solid 1px #e6e6e6;
}

.scrollbar {
  width: 100%;
  height: 90%;
}

// .scrollbar ::v-deep .el-scrollbar__bar .is-horizontal {
//     display: none;
//   overflow-x: hidden;

// }

.menu-container {
  width: 100%;
  }

.el-menu {
  border-right: none;
}

.tag-add {
  height: 10%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<template>
  <div class="container">
  <div class="tag-list-titile">
    <el-button  @click="openDialog" class="tag-create-btn" type="success" icon="el-icon-plus" round>新建业务标签</el-button>
  </div>
  <div class="tag-list">
    <div class="tag-list-item" v-for="data in datas" :key="data.tag.id">
      <div class="item-name">{{data.tag.name}}</div>
      <div class="item-status">
        <div v-if="data.model.state == 3" style="
          border-radius: 25px;
          background: #73c2e6;
          padding: 5px;
          width: 80px;
          height: 30px;
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items:center;
        ">运行中</div>
        <div v-if="data.model.state == 4" style="
          border-radius: 25px;
          background: #79cfd9;
          padding: 5px;
          width: 80px;
          height: 30px;
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items:center;
          ">未运行</div>
      </div>
      <div class="item-date">
        <div><i class="el-icon-refresh" style="color:#409eff"></i></div>
        <div>{{data.model.schedule.startTime}}</div>
      </div>
      <div class="item-info">
        <div><i v-if="false" class="el-icon-warning-outline" style="color:#409eff"></i></div>
        <div>{{data.tag.business}}</div>
      </div>
      <div class="item-option">
        <div>
          <el-button  @click="startTag(data, 3)" class="item-option-btn" v-if="data.model.state == 4" type="success" size="mini">启动</el-button>
          <el-button  @click="startTag(data, 4)" class="item-option-btn" v-if="data.model.state == 3" type="warning" size="mini">停止</el-button>
        </div>
        <div><el-button v-if="false" @click="editTag" class="item-option-btn" type="primary" size="mini">编辑</el-button></div>
        <div><el-button v-if="false" @click="deleteTag" class="item-option-btn" type="danger" size="mini">删除</el-button></div>
      </div>
    </div>
  </div>
  
<el-dialog title="新建四级标签" :visible.sync="dialogFormVisible">
  <div class="div-dialog-body1">
  <div class="div-dialog-body2">
    <el-form ref="modelTag" :model="modelTag">
      <el-form-item label="标签名称" :label-width="formLabelWidth">
        <el-input v-model="modelTag.tag.name"  placeholder="最多可输入10个字符" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="标签分类" :label-width="formLabelWidth">
        <el-select @change="getTags2" style="width:32%" v-model="modelTag.tag.parentId3" placeholder="一级标签">
          <el-option
            v-for="item in levelTags1"
            :key="item.id"
            :label="item.name"
            :value="item.id"
            >
          </el-option>
        </el-select>
        <el-select :disabled="categoryStatus2" @change="getTags3" style="width:32%;margin-left:2%;margin-right:2%" v-model="modelTag.tag.parentId2" placeholder="二级标签">
          <el-option
            v-for="item in levelTags2"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-select :disabled="categoryStatus3" style="width:32%" v-model="modelTag.tag.pid" placeholder="三级标签">
          <el-option
            v-for="item in levelTags3"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>

      </el-form-item>
      <el-form-item label="更新周期" :label-width="formLabelWidth">
        <el-select style="width:20%" v-model="modelTag.model.schedule.frequency" clearable placeholder="请选择">
          <el-option
            v-for="item in dateOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-date-picker
         style="width:78%;margin-left:2%"
          v-model="modelTag.model.schedule.dateRange"
          @change="dateChange"
          value-format="yyyy-MM-dd HH:mm"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="业务含义" :label-width="formLabelWidth">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 3}"
          placeholder="最多可输入400个字符"
          v-model="modelTag.tag.business"></el-input>
      </el-form-item>
      <el-form-item label="规则标签" :label-width="formLabelWidth">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 3}"
          placeholder="key=value,例如:type=hive or hdfs"
          v-model="modelTag.tag.rule"></el-input>
      </el-form-item>
      <el-form-item label="程序入口" :label-width="formLabelWidth">
        <el-input v-model="modelTag.model.mainClass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="算法名称" :label-width="formLabelWidth">
        <el-input v-model="modelTag.model.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="算法引擎" :label-width="formLabelWidth">
        <el-upload
          ref="upload"
          class="upload-demo"
          drag
          action="http://localhost:8081/tags/upload"
          :on-success="handleSuccess"
          :limit="1"
          :on-exceed="handleExceed"
          >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将<font style="color:red">JAR</font>文件拖到此处，或<em>点击上传</em></div>
          <!-- <div slot="tip" class="el-upload__tip">{{}}</div> -->
        </el-upload>
      </el-form-item>
      <el-form-item label="模型参数" :label-width="formLabelWidth">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 3}"
          placeholder="最多可输入1000个字符"
          v-model="modelTag.model.args"></el-input>
      </el-form-item>
    </el-form>
  </div>
    <div class="div-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="createTag">确 定</el-button>
    </div>
      </div>
  </el-dialog>




  </div>
</template>

<script>
import axios from "axios"
import config from "../../Config"
import TagFourPage from "./TagFourPage";

export default {
  components: {
    TagFourPage
  },
  props: {
    pid: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      datas:[],
      dialogTableVisible: false,
      dialogFormVisible: false,
      fileList: [],
      modelTag: {
        tag: {
          name: "",
          business: "",
          industry: "",
          rule: "",
          level: 4,
        },
        model: {
          name: "",
          path: "",
          mainClass: "",
          args: "",
          schedule: {
            dateRange: [],
            startTime: "",
            endTime: ""
          }
        }
      },
        formLabelWidth: '120px',
        levelTags1:[],
        levelTags2:[],
        levelTags3:[],
        categoryStatus2: true,
        categoryStatus3: true,
        //0无、1每天、2每周、3每月、4每年
        dateOptions: [{
          value: '0',
          label: '仅一次'
        },{
          value: '1',
          label: '每天'
        }, {
          value: '2',
          label: '每周'
        }, {
          value: '3',
          label: '每月'
        }, {
          value: '4',
          label: '每年'
        }],
        value: '',
        value1: '',
        textarea2:'',
        tmp:""
    }
  },
  watch: {
      $route: function (to, from) {
        // 对路由变化作出响应...
        axios
        .get(config.baseApi + "tags/model?pid="+this.$route.params.pid)
        .then(response => {
          this.datas = response.data.data;
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
      }
    },
   mounted() {
     axios
        .get(config.baseApi + "tags/model?pid="+this.$route.params.pid)
        .then(response => {
          this.datas = response.data.data;
         console.log("四级界面mounted")
         console.log(this.datas)
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
  },
  methods: {
    startTag: function(data,state) {
      axios
      .post(config.baseApi + "tags/"+ data.tag.id +"/model", {state: state})
      .then(response => {
        this.$message.success("操作成功");
        // 将界面状态信息更改
        if (state == 3) {
          this.state = 4
        }
        if (state == 4) {
          this.state = 3
        }
        //刷新界面
        this.reloadData()
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
    },
    editTag: function(event) {
      console.log("编辑")
    },
    deleteTag: function(event) {
      console.log("删除")
      console.log("启动")
      axios
      .post(config.baseApi + "tags/"+ data.tag.id +"/model")
      .then(response => {
        this.$message.success("启动成功");
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
    },
    openDialog: function(){
      this.getTags(1, -1)
      this.dialogFormVisible = true
    },
    getTags: function(level,pid) {
      axios
        .get(config.baseApi + "tags?pid=" + pid)
        .then(response => {
          if (level == 1) {
            this.levelTags1 = response.data.data;
          } else if (level == 2) {
            this.levelTags2 = response.data.data;
          } else if (level == 3) {
            this.levelTags3 = response.data.data;
          }
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
        
    },    
    getTags2: function(pid) {
      let obj = {};
      obj = this.levelTags1.find((item)=>{//这里的userList就是上面遍历的数据源
          return item.id === pid;//筛选出匹配数据
      });
      axios
        .get(config.baseApi + "tags?pid=" + pid)
        .then(response => {
          this.levelTags2 = response.data.data;
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
        this.categoryStatus2 = false
        this.categoryStatus3 = true
    },
    getTags3: function(pid) {
      let obj = {};
      obj = this.levelTags2.find((item)=>{//这里的userList就是上面遍历的数据源
          return item.id === pid;//筛选出匹配数据
      });
      axios
        .get(config.baseApi + "tags?pid=" + pid)
        .then(response => {
          this.levelTags3 = response.data.data;
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
        this.categoryStatus3 = false
    },
    dateChange: function(time) {
      console.log(time);
        // this.form.birthdayName = time;
    },
    handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件`);
    },
    handleSuccess(response, file, fileList){
      this.modelTag.model.path = response.data
    },
    reloadData: function(){
      axios
        .get(config.baseApi + "tags/model?pid="+this.$route.params.pid)
        .then(response => {
          this.datas = response.data.data;
         console.log("四级界面mounted")
         console.log(this.datas)
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
    },
    createTag: function(event) {
      //TODO 数据校验
      //日期转换
      let dataRange = this.modelTag.model.schedule.dateRange
      this.modelTag.model.schedule.startTime = dataRange[0]
      this.modelTag.model.schedule.endTime = dataRange[1]
      //设置初始状态为未启动
      this.modelTag.model.state = 4
      this.modelTag.tag.level = 4
      axios
      .put(config.baseApi + "tags/model", this.modelTag)
      .then(response => {
        let data = response.data;
        if (data.code == 0) {
          this.$message.success("四级标签创建成功");
          this.reloadData()
        }
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
      //关闭输入框
      this.dialogFormVisible = false
      //数据置为空
        this.$refs.upload.clearFiles();
        this.modelTag= {
        tag: {
          level: 4
        },
        model: {
          schedule: {
          }
        }
      }
    }
    }
  
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.tag-list-titile {
  width: 100%;
  height: 8%;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items:center;
}
.tag-create-btn {
  width: 150px;
  height: 40px;
  margin-right: 20px
}

.tag-list {
  width: 100%;
  height: 92%;
  display: flex;
  flex-direction: column;
  margin: 10px
}
.tag-list-item {
  width: 95%;
  height: 45px;
  display: flex;
  flex-direction: row;
  align-items:center;
  border-bottom: solid 1px #e4e4e4;
}
.item-name {
  width: 15%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
  margin-left: 20px;
}
.item-status {
  width: 15%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
}
.item-date {
  width: 25%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
}
.item-info {
  width: 15%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
}
.item-option {
  width: 30%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-end;
  margin-right: 20px
}
.item-option-btn {
  margin-right: 15px
}
.div-dialog-body1 {
  height: 65vh;
}
.div-dialog-body2 {
  height: 60vh;
  overflow: auto;
}
.div-footer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 10px;
  margin-right: 15px
}
</style>
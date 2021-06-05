<template>
  <div class="container">
  <div class="tag-list-titile">
    <el-button  @click="dialogFormVisible = true" class="tag-create-btn" type="success" icon="el-icon-plus" round>新建业务标签</el-button>
  </div>
  <div class="tag-list">
    <!--<div class="tag-item-title">
       <div class="item-name">{{datas.tag.name}}</div>
      <div class="item-status">
        <div v-if="datas.status" style="
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
        <div v-if="!datas.status" style="
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
        <div><i class="el-icon-refresh" style="color:#ffffff"></i></div>
        <div>{{datas.title.dateRange}}</div>
      </div>
      <div class="item-info">
        <div><i class="el-icon-warning-outline" style="color:#ffffff"></i></div>
        <div>{{mockData.title.info}}</div>
      </div>
      <div class="item-user-num">
        <div></div>
      </div>
      <div class="item-option">
        <div>
          <el-button  @click="startTag" class="item-option-btn" v-if="!mockData.title.status" type="success" size="mini">启动</el-button>
          <el-button  @click="startTag" class="item-option-btn" v-if="mockData.title.status" type="warning" size="mini">停止</el-button>
        </div>
        <div><el-button @click="editTag" class="item-option-btn" type="primary" size="mini">编辑</el-button></div>
        <div><el-button @click="deleteTag" class="item-option-btn" type="danger" size="mini">删除</el-button></div>
      </div>
    </div> -->
    <!-- 五级标签详细列表 -->
    <div class="tag-list-item" v-for="data in datas" :key="data.id">
      <div class="item-name">{{data.tag.name}}</div>
      <div class="item-status">
      </div>
      <div class="item-date">
        <div></div>
      </div>
      <div class="item-info">
        <div><i v-if="false" class="el-icon-warning-outline" style="color:#409eff"></i></div>
        <div><span style="color:#cfd9eb">{{data.tag.business}}</span></div>
      </div>
      <div class="item-user-num">
        <div>25<span style="color:#26b7e7">    用户拥有该标签</span></div>
      </div>
      <div class="item-option">
        <div>
        </div>
        <div><el-button v-if="true" @click="editTag" class="item-option-btn" type="primary" size="mini">编辑</el-button></div>
        <div><el-button v-if="true" @click="deleteTag" class="item-option-btn" type="danger" size="mini">删除</el-button></div>
      </div>
    </div>
  </div>
    <el-dialog title="新建五级标签" :visible.sync="dialogFormVisible">
    <el-form :model="formData">
      <el-form-item label="标签名称" :label-width="formLabelWidth">
        <el-input v-model="formData.name"  placeholder="最多可输入10个字符" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="业务含义" :label-width="formLabelWidth">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 3}"
          placeholder="最多可输入400个字符"
          v-model="formData.business"></el-input>
      </el-form-item>
      <el-form-item label="标签规则" :label-width="formLabelWidth">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 3}"
          placeholder="最多可输入50个字符"
          v-model="formData.rule"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="createTag">确 定</el-button>
    </div>
  </el-dialog>
  




  </div>
</template>

<script>
import axios from "axios"
import config from "../../Config"
import TagFivePage from "./TagFivePage";

export default {
  components: {
    TagFivePage
  },
  data() {
    return {
      mockData:{},
      datas:[],
      dialogTableVisible: false,
      dialogFormVisible: false,
      formData: {
        "name": "",
        "business": "",
        "industry": "",
        "rule": "",
        "level": 5,
        "pid": -1
      },
      form: {
        name: '',
        category1: '',
        category2: '',
        category3: '',
        dateType: '',
        dateRange: '',
        bussiness: '',
        ruleTag: '',
        appMain: '',
        algorithmName: '',
        modelArgs: ''
      },
        formLabelWidth: '120px',
        options: [{
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
        textarea2:''
    }
  },
   watch: {
      $route: function (to, from) {
        // 对路由变化作出响应...
        axios
        .get(config.baseApi + "tags/model?pid="+this.$route.params.pid)
        .then(response => {
          this.datas = response.data.data;
          console.log(this.datas)
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
          console.log(this.datas)
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
  },
  methods: {
    startTag: function(event) {
      console.log("启动")
    },
    editTag: function(event) {
      console.log("编辑")
    },
    deleteTag: function(event) {
      console.log("删除")
    },
    //创建5级标签
    createTag: function(event) {
      this.formData.pid = this.$route.params.pid
      axios
      .put(config.baseApi + "tags/data", this.formData)
      .then(response => {
        this.tags = response.data;
        this.reloadData();
        this.formData.name = ''
        this.formData.bussiness = ''
        this.formData.rule = ''
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
        
      //关闭输入框
      this.dialogFormVisible = false
    },
    //重新加载数据
    reloadData: function(){
          axios
        .get(config.baseApi + "tags/model?pid="+this.$route.params.pid)
        .then(response => {
          this.datas = response.data.data;
          console.log(this.datas)
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
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
.tag-item-title {
  width: 95%;
  height: 45px;
  display: flex;
  flex-direction: row;
  align-items:center;
  border-bottom: solid 1px #e4e4e4;
  background: #2fa4ff
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
  width: 10%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
  margin-left: 20px;
}
.item-status {
  width: 10%;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: flex-start;
}
.item-date {
  width: 20%;
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
.item-user-num {
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
  margin-right: 20px;
}
.item-option-btn {
  margin-right: 15px
}

</style>
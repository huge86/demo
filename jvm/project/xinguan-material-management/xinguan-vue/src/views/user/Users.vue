<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!--用户列表卡片-->
    <el-card class="box-card">
      <el-form :inline="true" :model="userVo" class="demo-form-inline">
        <el-form-item label="部门" label-width="70px">
        <el-select clearable v-model="userVo.departmentId" placeholder="请选择">
          <el-option
            v-for="item in departments"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">
              <span class="el-tag el-tag--success el-tag--mini el-tag--plain">{{ item.deptCount }}</span>
            </span>
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="用户名" label-width="70px">
          <el-input clearable v-model="userVo.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="70px">
          <el-input clearable v-model="userVo.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label-width="70px" style="margin-left: 25px">
          <el-radio v-model="userVo.sex" label="0">帅哥</el-radio>
          <el-radio v-model="userVo.sex" label="1">美女</el-radio>
          <el-radio v-model="userVo.sex" label="">全部</el-radio>
        </el-form-item>
        <el-form-item label="昵称" label-width="70px">
          <el-input clearable v-model="userVo.nickname" placeholder="请输入昵称"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="resetUserVo">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getUserList">查询</el-button>
          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>
          <el-button type="warning" icon="el-icon-download">导出</el-button>
        </el-form-item>
      </el-form>
      <!--表格内容显示区域-->
      <el-table
        :data="userList"
        border
        max-height="400"
        style="width: 100%;">
        <el-table-column
          prop="id"
          label="#"
          width="50">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="110">
        </el-table-column>
        <el-table-column
          prop="sex"
          label="性别"
          width="60">
          <template slot-scope="scope">
            <span class="el-tag el-tag--success el-tag--mini el-tag--light">{{scope.row.sex==0?'帅哥':(scope.row.sex==1?'美女':'保密')}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          sortable
          label="所属部门"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="birth"
          label="生日"
          width="180">
          <template slot-scope="scope">
            {{scope.row.birth}}
          </template>
        </el-table-column>
        <el-table-column
          sortable
          prop="email"
          label="邮箱"
          width="180">
        </el-table-column>
        <el-table-column
          prop="phoneNumber"
          label="电话"
          width="150">
        </el-table-column>
        <el-table-column
          prop="status"
          label="是否禁用"
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status==1"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
          <el-button type="warning" size="mini" icon="el-icon-s-tools"></el-button>
        </el-table-column>
      </el-table>
      <el-pagination
        style="padding-top: 15px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[6, 10, 20, 30]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <UserAdd :addOrUpdateVisible="addOrUpdateVisible" :departments="departments" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></UserAdd>
    </el-card>
  </div>


</template>

<script>
  import { findUserList } from '../../api/users'
  import { findDeptAndCount } from '../../api/dept'

  import UserAdd from './UserAdd'


  export default {
    name: 'Users',
    data() {
      return {
        userVo: {
          departmentId: '',
          username: '',
          email: '',
          sex: '',
          nickname: '',
        },
        // 控制新增编辑弹窗的显示与隐藏
        addOrUpdateVisible: false,
        //用户集合
        userList: [],
        //部门集合
        deptList: [],
        //每页显示的条数
        size: 6,
        //总条数
        total: 100,
        //当前页码
        current: 1,
        departments: []
      }
    },
    components: {
      UserAdd,
    },
    created(){
      //创建组件的时候调用查询所有用户的方法
      this.getUserList()
      this.getDeptAndCount()

    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      //当每页条数改变的时候
      handleSizeChange(val) {
        //将val赋值给size
        this.size = val;
        //重新去后台查询数据
        this.getUserList();
      },
      //当前页码改变的时候
      handleCurrentChange(val) {
        this.current = val;
        this.getUserList();
      },
      async getUserList(){
        const {data} = await findUserList(this.current,this.size,this.userVo)
        this.userList = data.data.records;
        this.total = data.data.total;
      },
      async getDeptAndCount(){
        const {data} = await findDeptAndCount()
        this.departments = data.data.departments;
      },
      //重置表单
      resetUserVo(){
        this.userVo.username = ''
        this.userVo.departmentId = ''
        this.userVo.email = ''
        this.userVo.nickname = ''
        this.userVo.sex= ''
        this.getUserList()
      },
      // 按钮点击事件 显示新增编辑弹窗组件
      show(){
        this.addOrUpdateVisible = true
      },
      // 监听 子组件弹窗关闭后触发，有子组件调用
      showAddOrUpdate(data){
        if(data === 'false'){
          this.addOrUpdateVisible = false
        }else{
          this.addOrUpdateVisible = true
        }
      }
    }
  }
</script>

<style scoped>

</style>

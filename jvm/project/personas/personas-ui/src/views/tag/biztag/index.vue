<template>
  <div class="app-container">
    <!-- 搜索（查询）form表单 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标签名称" prop="tagName">
        <el-input
          v-model="queryParams.tagName"
          placeholder="请输入标签名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签规则" prop="rule">
        <el-input
          v-model="queryParams.rule"
          placeholder="请输入标签规则"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择标签状态" clearable size="small">
          <el-option
            v-for="dict in stateOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 新增按钮所在行 row -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['tag:biztag:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table
      v-loading="loading"
      :data="biztagList"
      row-key="tagId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="标签名称" align="center" prop="tagName" />
      <el-table-column label="标签规则" align="center" prop="rule" />
      <el-table-column label="业务描述" align="center" prop="bizDesc" />
      <el-table-column label="标签等级" align="center" prop="level" :formatter="levelFormat" />
      <el-table-column label="业务类型" align="center" prop="industry" />
      <el-table-column label="标签状态" align="center" prop="state" :formatter="stateFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tag:attrtag:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['tag:attrtag:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tag:attrtag:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改属性标签对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父标签id" prop="parentId">
          <treeselect v-model="form.parentId" :options="attrtagOptions" :normalizer="normalizer" placeholder="请选择父标签id" />
        </el-form-item>
        <el-form-item label="标签名称" prop="tagName">
          <el-input v-model="form.tagName" placeholder="请输入标签名称" />
        </el-form-item>
        <el-form-item label="标签规则" prop="rule">
          <el-input v-model="form.rule" placeholder="请输入标签规则" />
        </el-form-item>
        <el-form-item label="业务描述" prop="bizDesc">
          <el-input v-model="form.bizDesc" placeholder="请输入业务描述" />
        </el-form-item>
        <el-form-item label="标签等级" prop="level">
          <el-select v-model="form.level" placeholder="请选择标签等级">
            <el-option
              v-for="dict in levelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务类型" prop="industry">
          <el-input v-model="form.industry" placeholder="请输入业务类型" />
        </el-form-item>
        <el-form-item label="标签状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择标签状态">
            <el-option
              v-for="dict in stateOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBiztag, getBiztag, delBiztag, addBiztag, updateBiztag, exportBiztag } from "@/api/tag/biztag";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Biztag",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 属性标签表格数据
      biztagList: [],
      // 属性标签树选项
      biztagOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 标签等级字典
      levelOptions: [],
      // 标签状态字典
      stateOptions: [],
      // 查询参数
      queryParams: {
        tagName: null,
        rule: null,
        state: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("personas_category_layer").then(response => {
      this.levelOptions = response.data;
    });
    this.getDicts("personas_category_state").then(response => {
      this.stateOptions = response.data;
    });
  },
  methods: {
    /** 查询业务标签列表 */
    getList() {
      this.loading = true;
      listBiztag(this.queryParams).then(response => {
        this.biztagList = this.handleTree(response.data, "tagId", "parentId");
        this.loading = false;
      });
    },
    /** 转换属性标签数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.tagId,
        label: node.tagName,
        children: node.children
      };
    },
    /** 查询属性标签下拉树结构 */
    getTreeselect() {
      listBiztag().then(response => {
        this.biztagOptions = [];
        const data = { tagId: 0, tagName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "tagId", "parentId");
        this.biztagOptions.push(data);
      });
    },
    // 标签等级字典翻译
    levelFormat(row, column) {
      return this.selectDictLabel(this.levelOptions, row.level);
    },
    // 标签状态字典翻译
    stateFormat(row, column) {
      return this.selectDictLabel(this.stateOptions, row.state);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        tagId: null,
        parentId: null,
        ancestors: null,
        tagName: null,
        rule: null,
        bizDesc: null,
        level: null,
        industry: null,
        orderNum: null,
        delFlag: null,
        state: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.tagId) {
        this.form.parentId = row.tagId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加业务标签";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.tagId;
      }
      getBiztag(row.tagId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改业务标签";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tagId != null) {
            updateBiztag(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAttrtag(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除属性标签编号为"' + row.tagId + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delBiztag(row.tagId);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>

<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 14px">
          重任在肩 砥砺前行——平普科技荣获“河北省数字科技创新发展示范企业”称号！
          <br />
          <el-link
            href="http://www.pingpukj.com/#/details?id=1401734976176910338&category=corp"
            type="primary"
            target="_blank"
            >http://www.pingpukj.com/#/details?id=1401734976176910338&category=corp</el-link
          >
          <br />
          内蒙古宁城县农牧局与供销联社领导莅临平普科技调研交流
          <br />
          <el-link
            href="http://www.pingpukj.com/#/details?id=1401733627502653442&category=corp"
            type="primary"
            target="_blank"
            >http://www.pingpukj.com/#/details?id=1401733627502653442&category=corp</el-link
          >
          <br />
          河北省资本研究会领导一行莅临平普科技指导工作
          <el-link href="http://www.moa.gov.cn/" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          >
          &nbsp;&nbsp;&nbsp; 农业农村部
          <el-link href="http://www.moa.gov.cn/" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          ><br />
          <h4 class="text-danger">河北省农业农村厅</h4>
        </blockquote>

        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>数字乡村大脑系列产品</h2>
        <p>
          建设以省、市、县域为基础的数字乡村大脑平台，构建包含全区域农业农村相关数据资源的数字乡村大数据平台，为各区域层面的农业数据汇总及决策指导提供支撑。数字乡村大脑带给涉农产业更大的影响是推动产业系统重组，升级产业链条，提高农业产业的能效。以数字基数创新为核心驱动力，具有天然的渗透性、融合性和赋能性。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">敬请关注</el-tag>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('http://www.pingpukj.com/#/aboutUs')"
            >关于我们</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://www.pingpukj.com/#/product')"
            >产品与服务</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i>
              官网：<el-link href="http://www.pingpukj.com" target="_blank"
                >http://www.pingpukj.com
              </el-link>
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ群：
              <s></s>
              <a href="" target="_blank"> </a>
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：<a
                href="javascript:;"
              ></a>
            </p>
            <p>
              <i class="el-icon-money"></i> 支付宝：<a
                href="javascript:;"
                class="支付宝信息"
              ></a>
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v3.5.0 - 2021-05-25">
              <ol>
                <li>
                  新增菜单导航显示风格TopNav（false为左侧导航菜单，true为顶部导航菜单）
                </li>
                <li>布局设置支持保存&重置配置</li>
                <li>修复树表数据显示不全&加载慢问题</li>
                <li>新增IE浏览器版本过低提示页面</li>
                <li>用户登录后记录最后登录IP&时间</li>
                <li>页面导出按钮点击之后添加遮罩</li>
                <li>富文本编辑器支持自定义上传地址</li>
                <li>富文本编辑组件新增readOnly属性</li>
                <li>页签TagsView新增关闭右侧功能</li>
                <li>显隐列组件加载初始默认隐藏列</li>
                <li>关闭头像上传窗口还原默认图片</li>
                <li>个人信息添加手机&邮箱重复验证</li>
                <li>代码生成模板导出按钮点击后添加遮罩</li>
                <li>代码生成模板树表操作列添加新增按钮</li>
                <li>代码生成模板修复主子表字段重名问题</li>
                <li>升级fastjson到最新版1.2.76</li>
                <li>升级druid到最新版本v1.2.6</li>
                <li>升级mybatis到最新版3.5.6 阻止远程代码执行漏洞</li>
                <li>升级oshi到最新版本v5.6.0</li>
                <li>
                  velocity剔除commons-collections版本，防止3.2.1版本的反序列化漏洞
                </li>
                <li>数据监控页默认账户密码防止越权访问</li>
                <li>修复firefox下表单构建拖拽会新打卡一个选项卡</li>
                <li>修正后端导入表权限标识</li>
                <li>修正前端操作日志&登录日志权限标识</li>
                <li>设置Redis配置HashKey序列化</li>
                <li>删除操作日志记录信息</li>
                <li>上传媒体类型添加视频格式</li>
                <li>修复请求形参未传值记录日志异常问题</li>
                <li>优化xss校验json请求条件</li>
                <li>树级结构更新子节点使用replaceFirst</li>
                <li>优化ExcelUtil空值处理</li>
                <li>日志记录过滤BindingResult对象，防止异常</li>
                <li>修改主题后mini类型按钮无效问题</li>
                <li>优化通用下载完成后删除节点</li>
                <li>通用Controller添加响应返回消息</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.4.0 - 2021-02-22">
              <ol>
                <li>代码生成模板支持主子表</li>
                <li>表格右侧工具栏组件支持显隐列</li>
                <li>图片组件添加预览&移除功能</li>
                <li>Excel注解支持Image图片导出</li>
                <li>操作按钮组调整为朴素按钮样式</li>
                <li>代码生成支持文件上传组件</li>
                <li>代码生成日期控件区分范围</li>
                <li>代码生成数据库文本类型生成表单文本域</li>
                <li>用户手机邮箱&菜单组件修改允许空字符串</li>
                <li>升级SpringBoot到最新版本2.2.13 提升启动速度</li>
                <li>升级druid到最新版本v1.2.4</li>
                <li>升级fastjson到最新版1.2.75</li>
                <li>升级element-ui到最新版本2.15.0</li>
                <li>修复IE11浏览器报错问题</li>
                <li>优化多级菜单之间切换无法缓存的问题</li>
                <li>修复四级菜单无法显示问题</li>
                <li>修正侧边栏静态路由丢失问题</li>
                <li>修复角色管理-编辑角色-功能权限显示异常</li>
                <li>配置文件新增redis数据库索引属性</li>
                <li>权限工具类增加admin判断</li>
                <li>角色非自定义权限范围清空选择值</li>
                <li>修复导入数据为负浮点数时丢失精度问题</li>
                <li>移除path-to-regexp正则匹配插件</li>
                <li>修复生成树表代码异常</li>
                <li>修改ip字段长度防止ipv6地址长度不够</li>
                <li>防止get请求参数值为false或0等特殊值会导致无法正确的传参</li>
                <li>登录后push添加catch防止出现检查错误</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.3.0 - 2020-12-14">
              <ol>
                <li>新增缓存监控功能</li>
                <li>支持主题风格配置</li>
                <li>修复多级菜单之间切换无法缓存的问题</li>
                <li>多级菜单自动配置组件</li>
                <li>代码生成预览支持高亮显示</li>
                <li>支持Get请求映射Params参数</li>
                <li>删除用户和角色解绑关联</li>
                <li>去除用户手机邮箱部门必填验证</li>
                <li>Excel支持注解align对齐方式</li>
                <li>Excel支持导入Boolean型数据</li>
                <li>优化头像样式，鼠标移入悬停遮罩</li>
                <li>代码生成预览提供滚动机制</li>
                <li>代码生成删除多余的数字float类型</li>
                <li>修正转换字符串的目标字符集属性</li>
                <li>回显数据字典防止空值报错</li>
                <li>日志记录增加过滤多文件场景</li>
                <li>修改缓存Set方法可能导致嵌套的问题</li>
                <li>移除前端一些多余的依赖</li>
                <li>防止安全扫描YUI出现的风险提示</li>
                <li>修改node-sass为dart-sass</li>
                <li>升级SpringBoot到最新版本2.1.18</li>
                <li>升级poi到最新版本4.1.2</li>
                <li>升级oshi到最新版本v5.3.6</li>
                <li>升级bitwalker到最新版本1.21</li>
                <li>升级axios到最新版本0.21.0</li>
                <li>升级element-ui到最新版本2.14.1</li>
                <li>升级vue到最新版本2.6.12</li>
                <li>升级vuex到最新版本3.6.0</li>
                <li>升级vue-cli到版本4.5.9</li>
                <li>升级vue-router到最新版本3.4.9</li>
                <li>升级vue-cli到最新版本4.4.6</li>
                <li>升级vue-cropper到最新版本0.5.5</li>
                <li>升级clipboard到最新版本2.0.6</li>
                <li>升级core-js到最新版本3.8.1</li>
                <li>升级echarts到最新版本4.9.0</li>
                <li>升级file-saver到最新版本2.0.4</li>
                <li>升级fuse.js到最新版本6.4.3</li>
                <li>升级js-beautify到最新版本1.13.0</li>
                <li>升级js-cookie到最新版本2.2.1</li>
                <li>升级path-to-regexp到最新版本6.2.0</li>
                <li>升级quill到最新版本1.3.7</li>
                <li>升级screenfull到最新版本5.0.2</li>
                <li>升级sortablejs到最新版本1.10.2</li>
                <li>升级vuedraggable到最新版本2.24.3</li>
                <li>升级chalk到最新版本4.1.0</li>
                <li>升级eslint到最新版本7.15.0</li>
                <li>升级eslint-plugin-vue到最新版本7.2.0</li>
                <li>升级lint-staged到最新版本10.5.3</li>
                <li>升级runjs到最新版本4.4.2</li>
                <li>升级sass-loader到最新版本10.1.0</li>
                <li>升级script-ext-html-webpack-plugin到最新版本2.1.5</li>
                <li>升级svg-sprite-loader到最新版本5.1.1</li>
                <li>升级vue-template-compiler到最新版本2.6.12</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.0 - 2019-10-08">
              <ol>
                <li>平普用户画像前后端分离系统正式发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>改进建议</span>
          </div>
          <div class="body">
            <img
              src="https://oscimg.oschina.net/oscnet/up-d6695f82666e5018f715c41cb7ee60d3b73.png"
              alt=""
              width="100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >改进建议发送到826992656@qq.com</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      // 版本号
      version: "3.5.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>


-- select * from personas_basic_tag;
-- select * from personas_model
--参考 用户画像.pdf45页
SELECT
	tag.tag_name, #标签名称,文本
  tag.ancestors, #标签分类,下拉
	model.sche_time, #更新周期,下拉
  tag.biz_desc, #业务含义,文本域
	tag.rule, #标签规则,文本域
	model.model_main, #程序入口,文本
	model.type, #算法类型,文本
	model.model_path, #算法引擎,上传文件
	model.args #模型参数,文本域
FROM
	personas_basic_tag tag
	INNER JOIN personas_model model ON tag.tag_id = model.tag_id;


-- 五级标签
--
-- 标签名称：tag_name
-- 业务含义：biz_desc
-- 标签规则：rule

-- 参考：
SELECT tb.id AS tagId, 
tb.`name` AS tagName, 
tb.business, 
tb.industry, 
tb.`level`, 
tb.rule, 
tb.model_main AS modelMain, 
tb.model_name AS modelName, 
tb.model_path AS modelPath, 
tb.sche_time AS schetime, 
tb.args FROM (
  SELECTtb1.id, 
  tb1.`name`, 
  tb1.business, 
  tb1.industry, 
  tb1.`level`, 
  tb1.pid, 
  tb1.state, 
  tb2.model_main, 
  tb2.model_name, 
  tb2.model_path, 
  tb2.sche_time, 
  tb3.rule, 
  tb2.args 
  FROM tbl_basic_tag tb1 
  INNER JOIN tbl_model tb2 
  ON tb1.id = tb2.tag_id 
  INNER JOIN tbl_rule tb3 
  ON tb1.id = tb3.tag_id ) tb 
  WHERE 1 = 1 
  AND tb.state != -1 
  AND tb.id = 8 ;

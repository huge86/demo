import request from '@/utils/request'

// 查询分类管理列表
export function listCategory(query) {
  return request({
    url: '/tag/category/list',
    method: 'get',
    params: query
  })
}

// 查询分类管理详细
export function getCategory(tagId) {
  return request({
    url: '/tag/category/' + tagId,
    method: 'get'
  })
}

// 新增分类管理
export function addCategory(data) {
  return request({
    url: '/tag/category',
    method: 'post',
    data: data
  })
}

// 修改分类管理
export function updateCategory(data) {
  return request({
    url: '/tag/category',
    method: 'put',
    data: data
  })
}

// 删除分类管理
export function delCategory(tagId) {
  return request({
    url: '/tag/category/' + tagId,
    method: 'delete'
  })
}

// 导出分类管理
export function exportCategory(query) {
  return request({
    url: '/tag/category/export',
    method: 'get',
    params: query
  })
}

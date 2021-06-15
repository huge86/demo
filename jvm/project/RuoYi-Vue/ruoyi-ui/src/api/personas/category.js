import request from '@/utils/request'

// 查询标签分类列表
export function listCategory(query) {
  return request({
    url: '/personas/category/list',
    method: 'get',
    params: query
  })
}

// 查询标签分类详细
export function getCategory(tagId) {
  return request({
    url: '/personas/category/' + tagId,
    method: 'get'
  })
}

// 新增标签分类
export function addCategory(data) {
  return request({
    url: '/personas/category',
    method: 'post',
    data: data
  })
}

// 修改标签分类
export function updateCategory(data) {
  return request({
    url: '/personas/category',
    method: 'put',
    data: data
  })
}

// 删除标签分类
export function delCategory(tagId) {
  return request({
    url: '/personas/category/' + tagId,
    method: 'delete'
  })
}

// 导出标签分类
export function exportCategory(query) {
  return request({
    url: '/personas/category/export',
    method: 'get',
    params: query
  })
}
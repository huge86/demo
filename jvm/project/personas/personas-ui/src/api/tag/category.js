import request from '@/utils/request'

// 查询标签分类列表
export function listCategory(query) {
  return request({
    url: '/tag/category/list',
    method: 'get',
    params: query
  })
}

// 查询标签分类详细
export function getCategory(catId) {
  return request({
    url: '/tag/category/' + catId,
    method: 'get'
  })
}

// 新增标签分类
export function addCategory(data) {
  return request({
    url: '/tag/category',
    method: 'post',
    data: data
  })
}

// 修改标签分类
export function updateCategory(data) {
  return request({
    url: '/tag/category',
    method: 'put',
    data: data
  })
}

// 删除标签分类
export function delCategory(catId) {
  return request({
    url: '/tag/category/' + catId,
    method: 'delete'
  })
}

// 导出标签分类
export function exportCategory(query) {
  return request({
    url: '/tag/category/export',
    method: 'get',
    params: query
  })
}
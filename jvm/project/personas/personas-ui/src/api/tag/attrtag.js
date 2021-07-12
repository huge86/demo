import request from '@/utils/request'

// 查询属性标签列表
export function listAttrtag(query) {
  return request({
    url: '/tag/attrtag/list',
    method: 'get',
    params: query
  })
}

// 查询属性标签详细
export function getAttrtag(tagId) {
  return request({
    url: '/tag/attrtag/' + tagId,
    method: 'get'
  })
}

// 新增属性标签
export function addAttrtag(data) {
  return request({
    url: '/tag/attrtag',
    method: 'post',
    data: data
  })
}

// 修改属性标签
export function updateAttrtag(data) {
  return request({
    url: '/tag/attrtag',
    method: 'put',
    data: data
  })
}

// 删除属性标签
export function delAttrtag(tagId) {
  return request({
    url: '/tag/attrtag/' + tagId,
    method: 'delete'
  })
}

// 导出属性标签
export function exportAttrtag(query) {
  return request({
    url: '/tag/attrtag/export',
    method: 'get',
    params: query
  })
}
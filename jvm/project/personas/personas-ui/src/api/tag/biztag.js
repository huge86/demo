import request from '@/utils/request'

// 查询业务标签列表
export function listBiztag(query) {
  return request({
    url: '/tag/biztag/list',
    method: 'get',
    params: query
  })
}

// 查询业务标签详细
export function getBiztag(tagId) {
  return request({
    url: '/tag/biztag/' + tagId,
    method: 'get'
  })
}

// 新增业务标签
export function addBiztag(data) {
  return request({
    url: '/tag/biztag',
    method: 'post',
    data: data
  })
}

// 修改业务标签
export function updateBiztag(data) {
  return request({
    url: '/tag/biztag',
    method: 'put',
    data: data
  })
}

// 删除业务标签
export function delBiztag(tagId) {
  return request({
    url: '/tag/biztag/' + tagId,
    method: 'delete'
  })
}

// 导出业务标签
export function exportBiztag(query) {
  return request({
    url: '/tag/biztag/export',
    method: 'get',
    params: query
  })
}

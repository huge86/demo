import request from '@/utils/request'

// 查询基础标签列表
export function listBasic(query) {
  return request({
    url: '/tag/basic/list',
    method: 'get',
    params: query
  })
}

// 查询基础标签详细
export function getBasic(tagId) {
  return request({
    url: '/tag/basic/' + tagId,
    method: 'get'
  })
}

// 新增基础标签
export function addBasic(data) {
  return request({
    url: '/tag/basic',
    method: 'post',
    data: data
  })
}

// 修改基础标签
export function updateBasic(data) {
  return request({
    url: '/tag/basic',
    method: 'put',
    data: data
  })
}

// 删除基础标签
export function delBasic(tagId) {
  return request({
    url: '/tag/basic/' + tagId,
    method: 'delete'
  })
}

// 导出基础标签
export function exportBasic(query) {
  return request({
    url: '/tag/basic/export',
    method: 'get',
    params: query
  })
}
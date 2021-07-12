import request from '@/utils/request'

// 查询客户列表
export function listCustomer(query) {
  return request({
    url: '/tag/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户详细
export function getCustomer(customerId) {
  return request({
    url: '/tag/customer/' + customerId,
    method: 'get'
  })
}

// 新增客户
export function addCustomer(data) {
  return request({
    url: '/tag/customer',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updateCustomer(data) {
  return request({
    url: '/tag/customer',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delCustomer(customerId) {
  return request({
    url: '/tag/customer/' + customerId,
    method: 'delete'
  })
}

// 导出客户
export function exportCustomer(query) {
  return request({
    url: '/tag/customer/export',
    method: 'get',
    params: query
  })
}
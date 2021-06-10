import request from '../utils/request'

export const findDeptAndCount= () => {
  return request({
    url: "/department/findDeptAndCount",
    method: 'get'
  })
}

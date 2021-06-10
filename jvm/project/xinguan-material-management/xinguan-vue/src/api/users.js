import request from '../utils/request'

/**
 * 后面的每次请求都是需要携带token的
 */
export const findUserList= (current,size,userVO) => {
  return request({
    url: "/user/findUserPage",
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  })
}

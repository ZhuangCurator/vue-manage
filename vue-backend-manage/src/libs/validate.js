/**
 * @description: 通用正则校验方法
 */
export default {
  // 正整数类型验证
  checkNumber (nullMsg, errorMsg) {
    return (rule, value, callback) => {
      if (value === null || value === '') {
        return callback(new Error(nullMsg ? nullMsg : '数据不能为空'))
      }
      let pattern = /^[0-9]+$/
      if (pattern.test(value) === false) {
        return callback(new Error(errorMsg ? errorMsg : '请输入数字常量'))
      } else {
        callback()
      }
    }
  },
  // 验证邮箱
  checkEmail (nullMsg, errorMsg) {
    return (rule, value, callback) => {
      if (value === null || value === '') {
        return callback(new Error(nullMsg ? nullMsg : '邮箱账号不能为空'))
      }
      let pattern = new RegExp('^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$')
      if (pattern.test(value) === false) {
        return callback(new Error(errorMsg ? errorMsg : '邮箱格式不正确'))
      }else{
        callback()
      }
    }
  },
}

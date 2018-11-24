const install = function (Vue) {
  let _this = Vue.prototype
  Vue.prototype.$confirm = function (options) {
      // 逻辑...
      const config = options === undefined ? {} : options
      const title = config.title || '删除确认'
      const tips = config.tips || '您确认要删除吗'
      const ok = config.ok || function () {
        _this.$Modal.remove()
        _this.$Message.success('删除成功')
      }
      const cancel = config.cancel || function () {
        _this.$Modal.remove()
      }

    _this.$Modal.confirm({
        render: (h) => {
          return h('div', [
            h('p', {
              style: {
                'color': '#F60',
                'text-align': 'center'
              },
              slot: 'header'
            }, [
              h('Icon', {
                props: {
                  type: 'ios-information-circle'
                }
              }),
              h('span', title),
              h('Divider', {
                class: {
                  'my-divider': true
                }
              })
            ]),
            h('div', {
              style: {
                'text-align': 'center'
              }
            }, tips),
            h('Divider', {
              class: {
                'my-divider': true
              }
            }),
            h('div', {
              slot: 'footer',
              style: {
                'text-align': 'center'
              }
            }, [
              h('Button', {
                props: {
                  size: 'large',
                },
                on: {
                  click: () => {
                    cancel()
                  }
                }
              }, '取消'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'large',
                },
                on: {
                  click: () => {
                    ok()
                  }
                },
                style: {
                  'margin-left': '5px'
                }
              }, '继续'),
            ])
          ])
        }
      })
    }

  Vue.prototype.$dateFormat = function (date, format) {
    //如果是时间戳的话那么转换成Date类型
    if (typeof date === 'number') {
      date = new Date(date)
    } else if (typeof date === 'string') {
      date = new Date(parseInt(date))
    }


    let o = {
      // 月份
      'M+': date.getMonth() + 1,
      // 日
      'd+': date.getDate(),
      // 小时
      'h+': date.getHours(),
      // 分
      'm+': date.getMinutes(),
      // 秒
      's+': date.getSeconds(),
      // 季度
      'q+': Math.floor((date.getMonth() + 3) / 3),
      // 毫秒
      'S': date.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    for (let k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(("" + o[k]).length)))
      }
    }
    return format
  }
}
export default install




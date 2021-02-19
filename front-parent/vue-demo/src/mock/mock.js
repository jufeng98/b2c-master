import Mock from 'mockjs'

const Random = Mock.Random

Mock.mock('//localhost:9999/b2c/mockjs/getBannerList', '', () => {
  let urls = []
  for (let i = 0; i < 3; i++) {
    let obj = {
      webUrl: Random.url()
    }
    urls.push(obj)
  }
  return {bannerList: urls}
})

Mock.mock('//localhost:9999/b2c/mockjs/getUserList', 'post', (reqContext) => {
  let urls = []
  for (let i = 0; i < 10; i++) {
    let obj = {
      name: Random.cname(),
      gender: Random.pick(['M', 'F', null]),
      province: Random.province(),
      city: Random.city(),
      address: Random.csentence(),
      zip: Random.integer(200000, 300000),
      date: Random.pick([new Date(), null]),
      servicePrice: Random.pick([0, 15858, 12350]),
      pic: 'https://tmallapi.bluemoon.com.cn/img/group1/M00/04/BA/wKjwDl-Q5_eEXRdSAAAAACWlGe8924.jpg',
      video: 'http://tmallapi.bluemoon.com.cn/img/group1/M00/04/BA/wKjwDl-P64iEPmMvAAAAALI1Izs586.mp4',
    }
    urls.push(obj)
  }
  return {tableData3: urls}
})

import axios from 'axios'
import config from '../config'
import $ from 'jquery'

const baseAxios = axios.create({
  timeout: 6000,
  headers: {
    'Content-Type': 'application/json',
    'X-Requested-With': 'XMLHttpRequest'
  }
})
// Add a request interceptor
baseAxios.interceptors.request.use(axiosConfig => {
  axiosConfig.url = config.BASE_PATH + config.APP_CONTEXT + axiosConfig.url
  if (axiosConfig.contentType === 'form') {
    axiosConfig.headers['Content-Type'] = 'application/x-www-form-urlencoded';
    axiosConfig.data = $.param(axiosConfig.data);
  } else {
    axiosConfig.data = JSON.stringify(axiosConfig.data || {});
  }
  return axiosConfig
}, error => {
  return Promise.reject(error)
})
// Add a response interceptor
baseAxios.interceptors.response.use(response => {
  if (response.status === 302) {
    // 跳回登录页面
    top.location = response.Location;
    return
  }
  return response.data
}, error => {
  return Promise.reject(error)
})
export default baseAxios

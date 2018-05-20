import axios from 'axios'

import { SLOW_REQUEST_HEADER,
  showProgressDialog,
  closeProgressDialog } from '../util/operationProgressUtil'

const onRejected = err => {
  if (err.config.headers[SLOW_REQUEST_HEADER]) {
    closeProgressDialog()
  }
  return Promise.reject(err)
}

axios.interceptors.request.use(
  config => {
    if (config.headers[SLOW_REQUEST_HEADER]) {
      showProgressDialog()
    }
    return config
  },
  onRejected
)

axios.interceptors.response.use(
  response => {
    if (response.config.headers[SLOW_REQUEST_HEADER]) {
      closeProgressDialog()
    }
    return response
  },
  onRejected
)

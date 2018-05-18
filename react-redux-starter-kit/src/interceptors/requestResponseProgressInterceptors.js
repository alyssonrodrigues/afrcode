import axios from 'axios'
import { showProgressDialog, closeProgressDialog } from '../util/operationProgressUtil'

const onRejected = err => {
  closeProgressDialog()
  Promise.reject(err)
}

axios.interceptors.request.use(
  config => {
    showProgressDialog()
    return config
  },
  onRejected
)

axios.interceptors.response.use(
  response => {
    closeProgressDialog()
    return response
  },
  onRejected
)

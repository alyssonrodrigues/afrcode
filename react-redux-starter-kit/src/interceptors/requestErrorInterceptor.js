import axios from 'axios'
import _ from 'lodash'

import { showErrorMsg } from '../util/messagesUtil'

const onFulfilled = response => response

const onRejected = err => {
  let msgText = ''
  if (_.isUndefined(err.response)) {
    // TODO rever
    msgText = err.message
    // TODO log err
    console.log(msgText)
    console.log(err.stack)
  } else {
    // TODO rever
    msgText = `${err.response.data} ${err.response.status} ${err.response.statusText}`
  }
  // TODO rever
  showErrorMsg(msgText)
  return Promise.reject(err)
}

axios.interceptors.response.use(onFulfilled, onRejected)

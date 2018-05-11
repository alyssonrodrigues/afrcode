import axios from 'axios'
import _ from 'lodash'

import { store } from '../Root'
import { showMessage } from '../actions/messagesActions'
import { ERROR } from '../util/messagesUtil'

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
  store.dispatch(showMessage({ type: ERROR, msgText }))
  return Promise.reject(err)
}

axios.interceptors.response.use(onFulfilled, onRejected)

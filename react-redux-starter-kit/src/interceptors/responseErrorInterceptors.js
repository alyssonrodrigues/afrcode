import axios from 'axios'
import _ from 'lodash'

import { showErrorMsg } from '../util/messagesUtil'
import { getBrowserHistory } from '../util/applicationContext'
import store from '../store/configureStore'
import { logoutUser } from '../actions/authenticationJwtActions'

const errorHandlers = {
  401: {
    handleError: () => {
      store.dispatch(logoutUser())
      getBrowserHistory().push('/login')
      return 'Usuário não autenticado, faça o login primeiro, por favor.'
    }
  },
  403: {
    handleError: () => {
      getBrowserHistory().push('/forbidden')
      return 'Você não tem permissão para acessar este recurso, solicite ao responsável, por favor.'
    }
  },
  'default': {
    handleError: statusText => {
      getBrowserHistory().push('/httperror')
      return statusText
    }
  }
}

const onFulfilled = response => response

const onRejected = err => {
  let msgText = ''
  if (_.isUndefined(err.response)) {
    msgText = err.message
    console.log(msgText)
    console.log(err.stack)
    errorHandlers.default.handleError(msgText)
  } else {
    const code = err.response.status
    const statusText = err.response.statusText
    const errorHandler = errorHandlers[code] || code > 400 ? errorHandlers.default : null
    if (errorHandler) {
      msgText = errorHandler.handleError(statusText)
    } else {
      msgText = `${code} ${statusText}`
    }
  }
  showErrorMsg(msgText)
  return Promise.reject(err)
}

axios.interceptors.response.use(onFulfilled, onRejected)

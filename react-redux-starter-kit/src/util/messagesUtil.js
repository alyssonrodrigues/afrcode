import { store } from '../Root'
import { showMessage } from '../actions/messagesActions'

export const ERROR = 'error'
export const INFO = 'info'
export const ALERT = 'alert'
export const SUCCESS = 'success'

const showMsg = (type, msgText) => {
  store.dispatch(showMessage({ type, msgText }))
}

export const showErrorMsg = message => {
  showMsg(ERROR, message)
}

export const showInfoMsg = message => {
  showMsg(INFO, message)
}

export const showAlertMsg = message => {
  showMsg(ALERT, message)
}

export const showSuccessMsg = message => {
  showMsg(SUCCESS, message)
}

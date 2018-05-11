export const SHOW_MESSAGE = '@messagesActions/SHOW_MESSAGE'
export const DISMISS_MESSAGE = '@messagesActions/DISMISS_MESSAGE'

export const showMessage = message => {
  return {
    type: SHOW_MESSAGE,
    payload: {
      message
    }
  }
}

export const dismissMessage = () => {
  return {
    type: DISMISS_MESSAGE,
    payload: {
      message: null
    }
  }
}

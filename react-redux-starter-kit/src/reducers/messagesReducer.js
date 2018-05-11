import { SHOW_MESSAGE, DISMISS_MESSAGE } from '../actions/messagesActions'

const INITIAL_STATE = { message: null }

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case SHOW_MESSAGE:
      return action.payload
    case DISMISS_MESSAGE:
      return action.payload
    default:
      return state
  }
}

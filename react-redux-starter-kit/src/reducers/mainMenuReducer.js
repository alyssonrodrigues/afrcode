import { CLOSE_MENU, OPEN_MENU } from '../actions/mainMenuActions'
import { USER_LOGOUT } from '../actions/authenticationJwtActions'

const INITIAL_STATE = {
  mainMenuOpen: false
}

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case OPEN_MENU:
      return { ...state, mainMenuOpen: true }
    case CLOSE_MENU:
      return INITIAL_STATE
    case USER_LOGOUT:
      return INITIAL_STATE
    default:
      return state
  }
}

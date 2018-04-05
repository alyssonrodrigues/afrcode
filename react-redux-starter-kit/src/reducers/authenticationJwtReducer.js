import { AUTHENTICATION_FAILED,
  USER_AUTHENTICATED,
  USER_LOGOUT } from '../actions/authenticationJwtActions';
import { getAuthentication } from '../security/securityContext';

const INITIAL_STATE = getAuthentication() || { isUserAuthenticated: false };

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case AUTHENTICATION_FAILED:
      return action.payload;
    case USER_AUTHENTICATED:
      return action.payload;
    case USER_LOGOUT:
      return action.payload;
    default:
      return state;
  }
};
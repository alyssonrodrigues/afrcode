import axios from 'axios';

import { saveSecurityToken, removeSecurityToken } from '../security/securityContext';
import { authenticationUrl } from '../util/applicationContext';

export const AUTHENTICATION_FAILED = '@authentication-jwt-actions/authentication_failed';
export const USER_AUTHENTICATED = '@authentication-jwt-actions/user_authenticated';
export const USER_LOGOUT = '@authentication-jwt-actions/user_logout';

export const axiosWrapper = axios;

export const authenticateUser = (data) => {
  const request = axios.post(authenticationUrl, data);

  return dispatch =>
    request.then(response => {
      const authentication = {
        tokenJwt: response.data.tokenJwt,
        username: data.username,
        isUserAuthenticated: true
      };
      saveSecurityToken(authentication);

      return dispatch({
        type: USER_AUTHENTICATED,
        payload: authentication
      });
    }).catch(err =>
      dispatch({
        type: AUTHENTICATION_FAILED,
        payload: err
      })
    );
};

export const logoutUser = () => {
  removeSecurityToken();
  return {
    type: USER_LOGOUT,
    payload: {
      tokenJwt: null,
      isUserAuthenticated: false,
      username: null
    }
  };
};
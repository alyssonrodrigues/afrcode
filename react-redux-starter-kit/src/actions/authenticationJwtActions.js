import axios from 'axios'

import { saveSecurityToken, removeSecurityToken } from '../security/securityContext'
import { authenticationUrl } from '../util/applicationContext'
import { SLOW_REQUEST_HEADER } from '../util/operationProgressUtil'
import { BYPASS_AUTH_INTERCEPTOR_HEADER } from '../interceptors/authenticationJwtInterceptors'

export const AUTHENTICATION_FAILED = '@authenticationJwtActions/AUTHENTICATION_FAILED'
export const USER_AUTHENTICATED = '@authenticationJwtActions/USER_AUTHENTICATED'
export const USER_LOGOUT = '@authenticationJwtActions/USER_LOGOUT'

export const axiosWrapper = axios

const AUTH_CONFIG_HEADERS = { headers: { ...SLOW_REQUEST_HEADER, ...BYPASS_AUTH_INTERCEPTOR_HEADER } }

export const authenticateUser = data => {
  return dispatch =>
    axios.post(authenticationUrl, data, AUTH_CONFIG_HEADERS).then(response => {
      const authentication = {
        tokenJwt: response.data.tokenJwt,
        username: data.username,
        isUserAuthenticated: true
      }
      saveSecurityToken(authentication)

      return dispatch({
        type: USER_AUTHENTICATED,
        payload: authentication
      })
    }).catch(err =>
      dispatch({
        type: AUTHENTICATION_FAILED,
        payload: { err }
      })
    )
}

export const logoutUser = () => {
  removeSecurityToken()
  return {
    type: USER_LOGOUT,
    payload: {
      tokenJwt: null,
      isUserAuthenticated: false,
      username: null
    }
  }
}

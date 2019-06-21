import axios from 'axios'
import { getAuthentication } from '../security/securityContext'

export const BYPASS_AUTH_INTERCEPTOR_HEADER = 'X-Bypass-Auth-Interceptor'

export const BYPASS_AUTH_INTERCEPTOR_CONFIG = {
  headers: {
    [BYPASS_AUTH_INTERCEPTOR_HEADER]: true
  }
}

const onFulfilled = config => {
  let auth = getAuthentication()
  if (!config.headers[BYPASS_AUTH_INTERCEPTOR_HEADER] && auth && auth.isUserAuthenticated) {
    let { tokenJwt } = auth
    config.headers['Authorization'] = `Bearer ${tokenJwt}`
  }
  return config
}

const onRejected = err => Promise.reject(err)

axios.interceptors.request.use(onFulfilled, onRejected)

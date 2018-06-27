export const authenticationUrl = 'http://localhost:8080/auth/token-jwt'

export const createDataToAuthentication = (username, password) => ({
  username,
  password
})

export const getAppTitle = () => 'App'

export const getMenuItems = () => [
  { label: 'Menu item 1', path: '/' },
  { label: 'Menu item 2', path: '/' },
  { label: 'Menu item N', path: '/' }
]

let browserHistory

export const setBrowserHistory = history => { browserHistory = history }

export const getBrowserHistory = () => browserHistory

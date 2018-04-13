export const authenticationUrl = 'http://localhost:8080/auth/token-jwt'

export const createDataToAuthentication = (username, password) => ({
  username,
  password
})

export const getMenuItems = () => [
  { label: 'Menu item 1', path: '/' },
  { label: 'Menu item 2', path: '/' },
  { label: 'Menu item N', path: '/' }
]

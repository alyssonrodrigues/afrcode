export const authenticationUrl = 'http://localhost:8080/auth/token-jwt'

export const createDataToAuthentication = (username, password) => ({
  username,
  password
})

export const getMenuItems = () => ['Menu item 1', 'Menu item 2', 'Menu item N']

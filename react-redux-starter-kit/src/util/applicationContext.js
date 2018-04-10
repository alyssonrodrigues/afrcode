export const authenticationUrl = 'http://localhost:8080/auth/token-jwt'

export const createDataToAuthentication = (username, password) => ({
  username,
  password
})

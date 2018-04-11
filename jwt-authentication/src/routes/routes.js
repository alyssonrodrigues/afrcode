module.exports = (app) => {
  const jwtAuthentication = new app.services.JwtAuthentication(app)
  app.post('/auth/token-jwt', jwtAuthentication.authenticate.bind(jwtAuthentication))
}

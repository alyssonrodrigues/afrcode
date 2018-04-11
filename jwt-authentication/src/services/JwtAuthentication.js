class JwtAuthentication {
  constructor (app) {
    this._app = app
  }

  authenticate (request, response, next) {
    let data = request.body
    const tokenJwt = { 'tokenJwt': data }
    response.format({
      json: () => response.json(tokenJwt)
    })
  }
}

module.exports = () => JwtAuthentication

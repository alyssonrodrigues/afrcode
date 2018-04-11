const resourceNotFoundHandler = (request, response, next) => {
  response.status(404).render('errors/404', {url: request.originalUrl})
  next()
}

module.exports = () => resourceNotFoundHandler

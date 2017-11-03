const resourceNotFoundHandler = (request, response, next) => {
    response.status(404).json({
        'urlNotFound': request.originalUrl
    });
    next();
};

module.exports = () => resourceNotFoundHandler;
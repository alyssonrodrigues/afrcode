const errorHandler = (error, request, response, next) => {
    console.log(error);
    response.status(500).render('errors/500', {error: JSON.stringify(error)});
    next(error);
};

module.exports = () => errorHandler;
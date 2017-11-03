const errorHandler = (error, request, response, next) => {
    console.log(error);
    response.status(500).json(error);
    next(error);
};

module.exports = () => errorHandler;
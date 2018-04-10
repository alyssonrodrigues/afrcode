import axios from 'axios';
import _ from 'lodash';

const onFulfilled = response => response;

const onRejected = err => {
  if (_.isUndefined(err.response)) {
    // TODO log err
    console.log(err.stack);
  }
  return Promise.reject(err);
};

axios.interceptors.response.use(onFulfilled, onRejected);
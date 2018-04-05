import React from 'react';
import { Provider } from 'react-redux';

import ErrorBoundary from './components/ErrorBoundary';
import AppRoutes from './routes/AppRoutes';
import configureStore from './store/configureStore';

const store = configureStore();

const Root = () => {
  return (
    <ErrorBoundary>
      <Provider store={store}>
        <AppRoutes />
      </Provider>
    </ErrorBoundary>
  );
}

export default Root;
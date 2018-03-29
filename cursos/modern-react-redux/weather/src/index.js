import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { createStore, applyMiddleware } from "redux";
import promise from "redux-promise";

import App from "./components/app";
import rootReducer from "./reducers/index";

// Creates a Redux store that holds the complete state tree of your app.
// There should only be a single store in your app.
const store = createStore(
  rootReducer,
  applyMiddleware(promise),
  window.devToolsExtension ? window.devToolsExtension() : f => f
);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.querySelector(".container")
);

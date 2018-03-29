import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { createStore } from "redux";

import App from "./components/app";
import rootReducer from "./reducers/index";

// Creates a Redux store that holds the complete state tree of your app.
// There should only be a single store in your app.
const store = createStore(
  rootReducer,
  window.devToolsExtension ? window.devToolsExtension() : f => f
);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.querySelector(".container")
);

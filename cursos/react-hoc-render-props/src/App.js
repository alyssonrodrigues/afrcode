import React, { Component } from 'react';

import Mouse from './components/Mouse';
import MouseTracker from './components/MouseTracker';
import ErrorBoundary from './components/ErrorBoundary';

/*
 * https://reactjs.org/docs/higher-order-components.html
 * https://reactjs.org/docs/render-props.html
 */

class App extends Component {
  render() {
    return (
      <ErrorBoundary>
        <h1>Move the mouse around!</h1>
        <Mouse render={mouse => (<MouseTracker mouse={mouse} />)} />
      </ErrorBoundary>
    );
  }
}

export default App;

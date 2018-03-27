import React, { Component } from 'react';

import Mouse from './components/Mouse';
import MouseTracker from './components/MouseTracker';

import withLogging from './components/withLogging';

/*
 * https://reactjs.org/docs/higher-order-components.html
 * https://reactjs.org/docs/render-props.html
 */

const MouseWithLogging = withLogging(Mouse);
const MouseTrackerWithLogging = withLogging(MouseTracker);

class App extends Component {
  render() {
    return (
      <div>
        <h1>Move the mouse around!</h1>
        <MouseWithLogging render={(mouse) => (<MouseTrackerWithLogging mouse={mouse} />)} />
      </div>
    );
  }
}

export default App;

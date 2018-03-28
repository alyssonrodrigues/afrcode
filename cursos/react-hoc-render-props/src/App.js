import React, { Component } from 'react';

import Mouse from './components/Mouse';
import MouseTracker from './components/MouseTracker';

/*
 * https://reactjs.org/docs/higher-order-components.html
 * https://reactjs.org/docs/render-props.html
 */

class App extends Component {
  render() {
    return (
      <div>
        <h1>Move the mouse around!</h1>
        <Mouse render={mouse => (<MouseTracker mouse={mouse} />)} />
      </div>
    );
  }
}

export default App;

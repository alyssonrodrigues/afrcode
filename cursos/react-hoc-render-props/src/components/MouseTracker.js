import React, { Component } from 'react';

import withLogging from './withLogging';

class MouseTracker extends Component {
  render() {
    const mouse = this.props.mouse;
    return (
      <p>The current mouse position is ({mouse.x}, {mouse.y})</p>
    );
  }
}

export default withLogging(MouseTracker);
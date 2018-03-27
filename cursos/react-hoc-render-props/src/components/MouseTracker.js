import React, { Component } from 'react';

class MouseTracker extends Component {
  render() {
    const mouse = this.props.mouse;
    return (
      <p>The current mouse position is ({mouse.x}, {mouse.y})</p>
    );
  }
}

export default MouseTracker;
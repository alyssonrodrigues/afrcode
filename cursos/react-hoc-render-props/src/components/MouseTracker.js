import React, { Component } from 'react';

import withLogging from './withLogging';

class MouseTracker extends Component {
  constructor(props) {
    super(props);
    this.state = { crash: false};
  }

  crash() {
    this.setState({ crash: true});
  }

  render() {
    if (this.state.crash) {
      throw new Error('I crashed!');
    }
    const mouse = this.props.mouse;
    return (
      <div>
        <p>The current mouse position is ({mouse.x}, {mouse.y})</p>
        <button
          onClick={() => this.crash()}
          style={{textAlign: 'center', position: 'relative'}}>
          Crash me
        </button>
      </div>
    );
  }
}

export default withLogging(MouseTracker);
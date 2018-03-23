import React, { Component } from 'react';

class HelloWorld extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  setName(name) {
    this.setState({
      name
    })
  }

  render() {
    return (
      <div id="helloworld">
        <input type="text" 
          placeholder="input your name in here..."
          value={this.state.name}
          onChange={event => this.setName(event.target.value)} />
        <p>Hello "{this.state.name}"!</p>
      </div>
    );
  }
}

export default HelloWorld;
import React, { Component } from 'react';

class HelloWorld extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  setName(event) {
    this.setState({
      name: event.target.value
    })
  }

  render() {
    return (
      <div id="helloworld">
        <input type="text" 
          placeholder="input your name in here..."
          value={this.state.name}
          onChange={this.setName.bind(this)} />
        <p>Hello "{this.state.name}"!</p>
      </div>
    );
  }
}

export default HelloWorld;
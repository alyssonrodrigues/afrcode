import React, { Component } from 'react';

/*
 * HOC for cross-cutting concerns...
 */
export default (WrappedComponent) => {
  return class extends Component {
    componentDidMount() {
      console.log('WrappedComponent did mount...');
    }
    render() {
      return <WrappedComponent {...this.props} />;
    }
  }
}
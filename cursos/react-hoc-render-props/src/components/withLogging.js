import React, { Component } from 'react';

/*
 * HOC for cross-cutting concerns...
 */
export default (WrappedComponent) => {
  class WithLogging  extends Component {
    componentDidMount() {
      console.log(`${getComponentDisplayName(WrappedComponent)} did mount...`);
    }
    render() {
      return <WrappedComponent {...this.props} />;
    }
  }
  WithLogging.displayName = `WithLogging(${getComponentDisplayName(WrappedComponent)})`;
  return WithLogging;
}

function getComponentDisplayName(WrappedComponent) {
    return WrappedComponent.displayName || WrappedComponent.name || 'Component';
}
import React, { Component } from 'react';

import withLogging from './withLogging';

/*
 * Note:
 * Error boundaries catch errors during rendering, in lifecycle methods,
 * and in constructors of the whole tree below them.
 *
 * Error boundaries do not catch errors for:
 *
 * Event handlers (if you need to catch an error inside event handler,
 * use the regular JavaScript try / catch statement)
 * Asynchronous code (e.g. setTimeout or requestAnimationFrame callbacks)
 * Server side rendering
 * Errors thrown in the error boundary itself (rather than its children)
 */
class ErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { error: null, errorInfo: null };
  }

  componentDidCatch(error, errorInfo) {
    // Catch errors in any components below and re-render with error message
    this.setState({
      error: error,
      errorInfo: errorInfo
    })
    // You can also log error messages to an error reporting service here
  }

  render() {
    if (this.state.errorInfo) {
      // Error path
      return (
        <div>
          <h2>Something went wrong.</h2>
          <details style={{ whiteSpace: 'pre-wrap' }}>
            {this.state.error && this.state.error.toString()}
            <br />
            {this.state.errorInfo.componentStack}
          </details>
        </div>
      );
    }
    // Normally, just render children
    return this.props.children;
  }
}

export default withLogging(ErrorBoundary);
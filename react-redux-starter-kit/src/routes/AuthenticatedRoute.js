import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import { connect } from 'react-redux'

class AuthenticatedRoute extends Component {
  renderComponent (routeProps, Component) {
    const { isUserAuthenticated } = this.props
    return isUserAuthenticated
      ? <Component {...routeProps} />
      : (
        <Redirect
          to={{
            pathname: '/login',
            state: { from: routeProps.location }
          }} />
      )
  }

  render () {
    const { component: Component, ...rest } = this.props
    return (<Route {...rest} render={routeProps => this.renderComponent(routeProps, Component)} />)
  }
}

const mapStateToProps = ({ authentication: { isUserAuthenticated } }) => ({ isUserAuthenticated })

export default connect(mapStateToProps)(AuthenticatedRoute)

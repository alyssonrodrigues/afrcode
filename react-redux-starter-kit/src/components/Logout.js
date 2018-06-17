import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'

import { logoutUser } from '../actions/authenticationJwtActions'
import { getAuthentication } from '../security/securityContext'
import { showSuccessMsg } from '../util/messagesUtil'
import App from './App'

export class Logout extends Component {
  componentWillMount () {
    const authentication = getAuthentication()
    if (authentication && authentication.isUserAuthenticated) {
      showSuccessMsg(`Até breve ${authentication.username}!`)
    }
    this.props.logoutUser()
  }

  render () {
    return (
      <App>
        <Redirect to='/' />
      </App>
    )
  }
}

export default connect(null, { logoutUser })(Logout)

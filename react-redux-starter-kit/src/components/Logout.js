import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'

import { logoutUser } from '../actions/authenticationJwtActions'
import { getAuthentication } from '../security/securityContext'
import { showSuccessMsg } from '../util/messagesUtil'

export class Logout extends Component {
  componentWillMount () {
    const authentication = getAuthentication()
    if (authentication && authentication.isUserAuthenticated) {
      showSuccessMsg(`Até breve ${authentication.username}!`)
    }
  }

  render () {
    this.props.logoutUser()
    return (<Redirect to='/' />)
  }
}

export default connect(null, { logoutUser })(Logout)

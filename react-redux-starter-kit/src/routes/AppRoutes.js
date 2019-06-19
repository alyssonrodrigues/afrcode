import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter, Route, Switch } from 'react-router-dom'

import { setBrowserHistory } from '../util/applicationContext'
import ForbiddenAccess from '../components/ForbiddenAccess'
import HttpErrorView from '../components/HttpErrorView'
import AuthenticatedRoute from './AuthenticatedRoute'
import Login from '../components/Login'
import Logout from '../components/Logout'
import AppIndex from '../components/AppIndex'
import NotFoundPage from '../components/NotFoundPage'

class AppRoutes extends Component {
  constructor (props) {
    super(props)
    setBrowserHistory(this.props.history)
  }

  render () {
    return (
      <Switch>
        {/* Defina as rotas por ordem de especificidade, das mais específicas para as mais genéricas... */}
        <Route path='/login' component={Login} />
        <Route path='/logout' component={Logout} />
        <Route path='/forbidden' component={ForbiddenAccess} />
        <Route path='/httperror' component={HttpErrorView} />
        <AuthenticatedRoute exact path='/' component={AppIndex} />
        <Route component={NotFoundPage} />
      </Switch>
    )
  }
}

const mapStateToProps = ({ authentication }) => ({ authentication })

const reduxEnhanced = connect(mapStateToProps)(AppRoutes)

export default withRouter(reduxEnhanced)

import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'
import { Grid, Button } from 'material-ui'
import { TextField } from 'redux-form-material-ui'
import { withStyles } from 'material-ui/styles'

import { authenticateUser } from '../actions/authenticationJwtActions'
import { createDataToAuthentication } from '../util/applicationContext'
import { required, alphaNumeric } from '../util/fieldLevelValidations'

const styles = {}

class Login extends Component {
  onSubmit (values) {
    this.props.authenticateUser(createDataToAuthentication(values.username, values.password))
  }

  render () {
    const { from } = this.props.location.state || { from: { pathname: '/' } }
    const { isUserAuthenticated, err } = this.props.authentication

    if (isUserAuthenticated) {
      return (<Redirect to={from} />)
    }

    const { handleSubmit, submitting } = this.props

    const msgClassName = `form-group ${err ? 'text-danger' : ''}`

    return (
      <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
        <Grid container direction='column' alignItems='center' spacing={16}>
          <Grid item xs>
            <Field
              label='Login'
              name='username'
              type='text'
              autoFocus
              component={TextField}
              validate={[required, alphaNumeric]}
            />
          </Grid>
          <Grid item xs>
            <Field
              label='Senha'
              name='password'
              type='password'
              component={TextField}
              validate={[required]}
            />
          </Grid>
          <Grid item xs>
            <div className={msgClassName}>
              <div className='text-help'>
                {/* TODO rever err */}
                {err ? (err.response
                  ? `${err.response.data} ${err.response.status} ${err.response.statusText}`
                  : 'Erro ao efetuar login, tente novamente.') : ''}
              </div>
            </div>
          </Grid>
          <Grid item xs>
            <Button variant='raised' color='primary' type='submit' disabled={submitting}>Entrar</Button>
          </Grid>
        </Grid>
      </form>
    )
  }
}

const materialUIEnhance = withStyles(styles)(Login)

const mapStateToProps = ({ authentication }) => ({ authentication })

const reduxEnhance = connect(mapStateToProps, { authenticateUser })(materialUIEnhance)

export default reduxForm({ form: 'LoginForm' })(reduxEnhance)

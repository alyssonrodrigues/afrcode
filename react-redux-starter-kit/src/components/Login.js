import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'

import { authenticateUser } from '../actions/authenticationJwtActions'
import { createDataToAuthentication } from '../util/applicationContext'
import { required, alphaNumeric } from '../util/fieldLevelValidations'

class Login extends Component {
  onSubmit (values) {
    this.props.authenticateUser(createDataToAuthentication(values.username, values.password))
  }

  renderField ({
    input,
    label,
    type,
    meta: { touched, error, warning }
  }) {
    const className = `form-group ${touched ? error ? 'text-danger' : warning ? 'text-warning' : '' : ''}`

    return (
      <div className={className}>
        <label>{label}</label>
        <input className='form-control' type={type} placeholder={label} {...input} />
        <div>
          {touched &&
          ((error && <span>{error}</span>) ||
            (warning && <span>{warning}</span>))}
        </div>
      </div>
    )
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
      <form className='container' onSubmit={handleSubmit(this.onSubmit.bind(this))}>
        <Field
          label='Login'
          name='username'
          type='text'
          component={this.renderField}
          validate={[required]}
          warn={alphaNumeric}
        />
        <Field
          label='Senha'
          name='password'
          type='password'
          component={this.renderField}
          validate={[required]}
        />
        <div className={msgClassName}>
          <div className='text-help'>
            {/* TODO rever err */}
            {err ? (err.response
              ? `${err.response.data} ${err.response.status} ${err.response.statusText}`
              : 'Erro ao efetuar login, tente novamente.') : ''}
          </div>
        </div>
        <button type='submit' className='btn btn-primary' disabled={submitting}>Entrar</button>
      </form>
    )
  }
}

const mapStateToProps = ({ authentication }) => ({ authentication })

const reduxConnectEnhance = connect(mapStateToProps, { authenticateUser })(Login)

export default reduxForm({ form: 'LoginForm' })(reduxConnectEnhance)

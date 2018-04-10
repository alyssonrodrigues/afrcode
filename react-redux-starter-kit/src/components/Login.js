import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'

import { authenticateUser } from '../actions/authenticationJwtActions'
import { createDataToAuthentication } from '../util/applicationContext'

class Login extends Component {
  onSubmit (values) {
    this.props.authenticateUser(createDataToAuthentication(values.username, values.password))
  }

  renderField (field) {
    const { meta: { touched, error } } = field
    const className = `form-group ${touched && error ? 'text-danger' : ''}`

    return (
      <div className={className}>
        <label>{field.label}</label>
        <input className='form-control' type={field.type} {...field.input} />
        <div>
          {touched ? error : ''}
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

    const { handleSubmit } = this.props

    const msgClassName = `form-group ${err ? 'text-danger' : ''}`

    return (
      <form className='container' onSubmit={handleSubmit(this.onSubmit.bind(this))}>
        <Field
          label='Login'
          name='username'
          type='text'
          component={this.renderField}
        />
        <Field
          label='Senha'
          name='password'
          type='password'
          component={this.renderField}
        />
        <div className={msgClassName}>
          <div className='text-help'>
            {/* TODO rever err */}
            {err ? (err.response
              ? `${err.response.data} ${err.response.status} ${err.response.statusText}`
              : 'Erro ao efetuar login, tente novamente.') : ''}
          </div>
        </div>
        <button type='submit' className='btn btn-primary'>Entrar</button>
      </form>
    )
  }
}

function validate (values) {
  const errors = {}

  if (!values.username) {
    errors.username = 'Informe o login'
  }

  if (!values.password) {
    errors.password = 'Informe a senha'
  }

  return errors
}

const mapStateToProps = ({ authentication }) => ({ authentication })

const reduxConnectEnhance = connect(mapStateToProps, { authenticateUser })(Login)

export default reduxForm({ validate, form: 'LoginForm' })(reduxConnectEnhance)

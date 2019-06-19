import React, { Component } from 'react'
import { Form, Field } from 'react-final-form'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'
import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'
import { TextField } from 'final-form-material-ui'
import { withStyles } from '@material-ui/core/styles'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Icon from '@material-ui/core/Icon'

import { authenticateUser } from '../actions/authenticationJwtActions'
import { createDataToAuthentication } from '../util/applicationContext'
import { required } from '../util/fieldLevelValidations'
import styles from './LoginStyles'
import App from './App'

class Login extends Component {
  onSubmit (values) {
    this.props.authenticateUser(createDataToAuthentication(values.username, values.password))
  }

  renderForm ({ handleSubmit, submitting }) {
    const { classes } = this.props
    return (
      <form onSubmit={handleSubmit}>
        <Grid container direction='column' alignItems='center' spacing={2}>
          <Card className={classes.card}>
            <Grid className={classes.titleBar} container justify='center' >
              <Icon className={classes.icon}>account_circle</Icon>
            </Grid>
            <CardContent>
              <Field
                name='username'
                validate={required}
                component={TextField}
                className={classes.loginForm}
                label='Usuário'
                type='text'
                autoFocus
                fullWidth />
              <Field
                name='password'
                validate={required}
                component={TextField}
                className={classes.loginForm}
                label='Senha'
                type='password'
                fullWidth />
            </CardContent>
            <CardActions className={classes.rowLogIn}>
              <Button variant='contained' color='primary' type='submit' disabled={submitting}>Entrar</Button>
            </CardActions>
          </Card>
        </Grid>
      </form>
    )
  }

  render () {
    const { from } = this.props.location.state || { from: { pathname: '/' } }
    const { isUserAuthenticated } = this.props.authentication

    if (isUserAuthenticated) {
      return (<Redirect to={from} />)
    }

    return (
      <App>
        <Form onSubmit={this.onSubmit.bind(this)} render={this.renderForm.bind(this)} />
      </App>
    )
  }
}

const materialUIEnhance = withStyles(styles)(Login)

const mapStateToProps = ({ authentication }) => ({ authentication })

export default connect(mapStateToProps, { authenticateUser })(materialUIEnhance)

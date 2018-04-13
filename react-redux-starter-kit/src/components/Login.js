import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'
import { Grid, Button } from 'material-ui'
import { TextField } from 'redux-form-material-ui'
import { withStyles } from 'material-ui/styles'
import Card, { CardActions, CardContent } from 'material-ui/Card'
import Typography from 'material-ui/Typography'
import indigo from 'material-ui/colors/indigo'
import Icon from 'material-ui/Icon'
import red from 'material-ui/colors/red'

import { authenticateUser } from '../actions/authenticationJwtActions'
import { createDataToAuthentication } from '../util/applicationContext'
import { required, alphaNumeric } from '../util/fieldLevelValidations'

const styles = {
  card: {
    minWidth: 500,
    position: 'absolute',
    top: '50%',
    transform: 'translateY(-50%)'
  },
  title: {
    marginBottom: 16,
    fontSize: 14
  },
  loginForm: {
    marginBottom: 12,
    marginTop: 20
  },
  titleBar: {
    backgroundColor: indigo[500],
    color: 'white',
    padding: 10
  },
  icon: {
    fontFamily: 'Material Icons',
    fontWeight: 'normal',
    fontStyle: 'normal',
    fontSize: 60,
    lineHeight: 1,
    letterSpacing: 'normal',
    textTransform: 'none',
    display: 'inline-block',
    whiteSpace: 'nowrap',
    wordWrap: 'normal'
  },
  loginError: {
    color: red[500]
  }
}

class Login extends Component {
  onSubmit (values) {
    this.props.authenticateUser(createDataToAuthentication(values.username, values.password))
  }

  render () {
    const { from } = this.props.location.state || { from: { pathname: '/' } }
    const { isUserAuthenticated, err } = this.props.authentication
    const { classes } = this.props

    if (isUserAuthenticated) {
      return (<Redirect to={from} />)
    }

    const { handleSubmit, submitting } = this.props

    return (
      <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>
        <Grid container direction='column' alignItems='center' spacing={16}>
          <Grid item xs>
            <Typography variant='body2' className={classes.loginError}>
              {/* TODO rever err */}
              {err ? (err.response
                ? `${err.response.data} ${err.response.status} ${err.response.statusText}`
                : 'Erro ao efetuar login, tente novamente.') : ''}
            </Typography>
          </Grid>
          <Card className={classes.card}>
            <Grid className={classes.titleBar} container justify='center' >
              <Icon className={classes.icon}>account_circle</Icon>
            </Grid>
            <CardContent>
              <Field
                className={classes.loginForm}
                label='Login'
                name='username'
                type='text'
                autoFocus
                component={TextField}
                fullWidth
                validate={[required, alphaNumeric]}
              />
              <Field
                className={classes.loginForm}
                label='Senha'
                name='password'
                type='password'
                component={TextField}
                validate={[required]}
                fullWidth
              />
            </CardContent>
            <CardActions>
              <Button fullWidth color='primary' type='submit' disabled={submitting}>Entrar</Button>
            </CardActions>
          </Card>
        </Grid>
      </form>
    )
  }
}

const materialUIEnhance = withStyles(styles)(Login)

const mapStateToProps = ({ authentication }) => ({ authentication })

const reduxEnhance = connect(mapStateToProps, { authenticateUser })(materialUIEnhance)

export default reduxForm({ form: 'LoginForm' })(reduxEnhance)

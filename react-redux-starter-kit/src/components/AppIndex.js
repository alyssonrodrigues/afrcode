import React, { Component } from 'react'
import { withStyles } from '@material-ui/core/styles'
import Grid from '@material-ui/core/Grid'
import Paper from '@material-ui/core/Paper'
import Button from '@material-ui/core/Button'
import _ from 'lodash'

import { getAuthentication } from '../security/securityContext'
import { showSuccessMsg } from '../util/messagesUtil'
import { showProgressDialog, closeProgressDialog } from '../util/operationProgressUtil'

const styles = theme => ({
  root: {
    flexGrow: 1
  },
  paper: {
    padding: theme.spacing.unit * 2,
    textAlign: 'left',
    height: '100%'
  }
})

const devTips = (
  <div>
    <p>
      Para iniciar, edite o <code>src/AppIndex.js</code> e salve para recarregar.
    </p>
    <p>
      Veja também o <code>src/routes/AppRoutes.js</code> para definições de "routes".
    </p>
    <p>
      Encontre em <code>src/util/applicationContext.js</code> configurações de contexto da aplicação.
    </p>
    <p>
      Encontre em <code>src/interceptors</code> "interceptors" para aspectos comuns da aplicação.
    </p>
  </div>
)

const devStatus = (
  <div>
    <p>
      O que temos para hoje:
    </p>
    <ol>
      <li>standard e babel-eslint p/ JavaScript Standard Style,</li>
      <li>componentes react material-ui p/ Google's Material Design,</li>
      <li>login, login redirect p/ authenticated routes,</li>
      <li>routes, authenticated routes, not found page route,</li>
      <li>axios p/ requests,</li>
      <li>request/response error/progress interceptors,</li>
      <li>authentication JWT, authentication JWT request interceptor,</li>
      <li>central error boundary,</li>
      <li>app toolbar com app menu e authenticated user menu,</li>
      <li>messages bar e messages util p/ exibição de mensagens,</li>
      <li>
        <Button size='small' onClick={() => {
          showProgressDialog()
          _.delay(closeProgressDialog, 3000)
        }}>progress dialog</Button> e operations util para operações demoradas,
      </li>
      <li>logout,</li>
      <li>application context config,</li>
      <li>security context holder/config,</li>
      <li>redux store config, redux-form reducer config,</li>
      <li>jest p/ testes,</li>
      <li>axios-mock-adapter p/ testes,</li>
      <li>enzyme p/ shallow testing,</li>
      <li>redux-mock-store p/ testes,</li>
      <li>jest, react-test-renderer p/ snapshot testing,</li>
      <li>TODO...</li>
    </ol>
  </div>
)

class AppIndex extends Component {
  componentDidMount () {
    const { username } = getAuthentication()
    showSuccessMsg(`Seja bem-vindo(a) ${username}!`)
  }

  render () {
    const { classes } = this.props
    return process.env.NODE_ENV !== 'production' && (
      <div className={classes.root}>
        <Grid container spacing={16}>
          <Grid item xs={12} sm={6}>
            <Paper className={classes.paper}>{devTips}</Paper>
          </Grid>
          <Grid item xs={12} sm={6}>
            <Paper className={classes.paper}>{devStatus}</Paper>
          </Grid>
        </Grid>
      </div>
    )
  }
}

export default withStyles(styles)(AppIndex)

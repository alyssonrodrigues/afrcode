import React from 'react'
import { Grid } from 'material-ui'

const devTips = process.env.NODE_ENV === 'production' ? '' : (
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
    <p>
      O que temos para hoje:
    </p>
    <ul>
      <li>standard e babel-eslint p/ JavaScript Standard Style,</li>
      <li>componentes react material-ui p/ Google's Material Design,</li>
      <li>login, login redirect p/ authenticated routes,</li>
      <li>routes, authenticated routes, not found page route,</li>
      <li>axios p/ requests, request error interceptor,</li>
      <li>authentication JWT, authentication JWT request interceptor,</li>
      <li>central error boundary,</li>
      <li>app toolbar com app menu e authenticated user menu,</li>
      <li>messages bar e messages util p/ exibição de mensagens,</li>
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
    </ul>
  </div>)

const AppIndex = () => (
  <Grid container direction='column' alignItems='stretch' spacing={16}>
    <Grid item xs>
      {devTips}
    </Grid>
  </Grid>
)

export default AppIndex

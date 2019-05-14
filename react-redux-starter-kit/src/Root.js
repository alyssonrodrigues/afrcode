import React from 'react'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import CssBaseline from '@material-ui/core/CssBaseline'
import { MuiThemeProvider, withStyles } from '@material-ui/core/styles'

import AppRoutes from './routes/AppRoutes'
import ErrorBoundary from './components/ErrorBoundary'
import store from './store/configureStore'
import styles, { configTheme } from './RootStyles'

export const Root = props => (
  <MuiThemeProvider theme={configTheme(props.theme)}>
    <div className={props.classes.root}>
      <CssBaseline />
      <ErrorBoundary>
        <Provider store={store}>
          <BrowserRouter>
            <AppRoutes />
          </BrowserRouter>
        </Provider>
      </ErrorBoundary>
    </div>
  </MuiThemeProvider>
)

export default withStyles(styles, { withTheme: true })(Root)

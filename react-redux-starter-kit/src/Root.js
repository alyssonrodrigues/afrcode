import React from 'react'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import CssBaseline from '@material-ui/core/CssBaseline'
import { MuiThemeProvider, withStyles } from '@material-ui/core/styles'

import AppRoutes from './routes/AppRoutes'
import ErrorBoundary from './components/ErrorBoundary'
import store from './store/configureStore'
import styles, { theme } from './RootStyles'

export const Root = props => (
  <MuiThemeProvider theme={theme}>
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

export default withStyles(styles)(Root)

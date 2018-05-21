import React from 'react'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import CssBaseline from 'material-ui/CssBaseline'
import { MuiThemeProvider, withStyles } from 'material-ui/styles'

import ErrorBoundary from './components/ErrorBoundary'
import App from './components/App'
import store from './store/configureStore'
import styles, { theme } from './RootStyles'

const Root = props => (
  <MuiThemeProvider theme={theme}>
    <div className={props.classes.root}>
      <CssBaseline />
      <ErrorBoundary>
        <Provider store={store}>
          <BrowserRouter>
            <App />
          </BrowserRouter>
        </Provider>
      </ErrorBoundary>
    </div>
  </MuiThemeProvider>
)

export default withStyles(styles)(Root)
